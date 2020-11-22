package com.github.zhuyizhuo.generator.mybatis.generator;

import com.github.zhuyizhuo.generator.annotation.NotNull;
import com.github.zhuyizhuo.generator.annotation.Nullable;
import com.github.zhuyizhuo.generator.constants.ConfigConstants;
import com.github.zhuyizhuo.generator.constants.LinkConstants;
import com.github.zhuyizhuo.generator.enums.ErrorTypeEnums;
import com.github.zhuyizhuo.generator.enums.LogLevelEnums;
import com.github.zhuyizhuo.generator.enums.MethodEnums;
import com.github.zhuyizhuo.generator.enums.ModuleTypeEnums;
import com.github.zhuyizhuo.generator.exception.GeneratorException;
import com.github.zhuyizhuo.generator.mybatis.convention.FileOutPathInfo;
import com.github.zhuyizhuo.generator.mybatis.generator.extension.CustomizeModuleInfo;
import com.github.zhuyizhuo.generator.mybatis.generator.extension.FormatService;
import com.github.zhuyizhuo.generator.mybatis.generator.extension.JavaModuleInfo;
import com.github.zhuyizhuo.generator.mybatis.generator.factory.GenerateServiceFactory;
import com.github.zhuyizhuo.generator.mybatis.generator.service.GenerateService;
import com.github.zhuyizhuo.generator.mybatis.generator.support.ContextHolder;
import com.github.zhuyizhuo.generator.mybatis.generator.support.MethodInfo;
import com.github.zhuyizhuo.generator.utils.CheckUtils;
import com.github.zhuyizhuo.generator.utils.LogUtils;
import com.github.zhuyizhuo.generator.utils.PropertiesUtils;
import com.github.zhuyizhuo.generator.utils.TypeConversion;
import org.apache.ibatis.type.JdbcType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Builds {@link Generator} instances. <br>
 *
 * @author zhuo <br>
 * @version 1.2.0
 */
public class GeneratorBuilder {
    /***
     * key 数据库字段类型
     * value java 数据类型
     */
    private Map<String, Class<?>> typeMapper;
    /**
     * 指定方法名格式化 service map , 优先级高于 格式化全部方法
     */
    private Map<MethodEnums,FormatService> methodNameFormatServiceMap;
    /**
     * 格式化全部方法 service 优先级低于指定方法格式化 service
     */
    private FormatService commonMethodFormatService;
    /** 模块名格式化 Service MAP */
    private Map<String, FormatService> moduleNameFormatServiceMap;
    /** 扩展 自定义模板 */
    private List<JavaModuleInfo> javaModuleInfos;
    /** 扩展 自定义模板 */
    private List<CustomizeModuleInfo> customizeModuleInfos;
    /** 自定义生成器 */
    private GenerateService generateService;
    /** 配置信息 */
    private Properties proInfo;
    /** 模板类型及路径 用以替换系统中已定义的模板 */
    private Map<ModuleTypeEnums, String> moduleTypeTemplatePathMap;

    public GeneratorBuilder() {
    }

    /**
     * 设置 properties 参数, 优先级高于配置文件
     * 相同参数此处设置的将会覆盖配置文件
     * 格式 {@code key=value} or {@code key:value}.
     * @param defaultProperties the properties to set.
     * @return the current builder
     */
    public GeneratorBuilder properties(String... defaultProperties) {
        return properties(getMapFromKeyValuePairs(defaultProperties));
    }

    /**
     * 设置 properties 参数, 优先级高于配置文件
     * 相同参数此处设置的将会覆盖配置文件
     * @param defaults  the default properties
     * @return the current builder
     */
    public GeneratorBuilder properties(Map<String, String> defaults) {
        if (this.proInfo == null){
            this.proInfo = new Properties();
        }
        this.proInfo.putAll(defaults);
        return this;
    }

    /**
     * 自定义指定模块生成名称
     * @param moduleType 模块类型
     * @param moduleNameFormatService 模块名格式化 Service
     * @since 1.4.0
     * @return the current builder
     */
    public GeneratorBuilder addModuleNameFormat(@NotNull ModuleTypeEnums moduleType, @NotNull FormatService moduleNameFormatService) {
        CheckUtils.assertNotNull(moduleType,"addModuleNameFormat 参数 moduleType 不能为空!");
        this.addModuleNameFormat(moduleType.toString(), moduleNameFormatService);
        return this;
    }

    /**
     * 自定义指定模块生成名称 此方法可用来扩展自定义模块
     * @param moduleType 模块类型
     * @param moduleNameFormatService 模块名格式化 Service
     * @since 1.4.0
     * @return the current builder
     */
    public GeneratorBuilder addModuleNameFormat(@NotNull String moduleType, @NotNull FormatService moduleNameFormatService) {
        CheckUtils.assertNotNull(moduleType,"addModuleNameFormat 参数 moduleType 不能为空!");
        CheckUtils.assertNotNull(moduleNameFormatService,"addModuleNameFormat 参数 moduleNameFormatService 不能为空!");
        if (this.moduleNameFormatServiceMap == null){
            this.moduleNameFormatServiceMap = new HashMap<>();
        }
        this.moduleNameFormatServiceMap.put(moduleType, moduleNameFormatService);
        return this;
    }

    /**
     * 自定义方法生成名称
     * @param methodType 方法类型
     *       如果自定义全局方法生成格式化 则 传入 MethodEnums.ALL_METHOD
     *         全局方法名格式化需配合配置  generate.java.method.{methodType}.name-format 使用
     * @param methodNameFormatService 格式化service
     * @since 1.4.0
     * @return the current builder
     */
    public GeneratorBuilder addMethodNameFormat(@NotNull MethodEnums methodType,@NotNull FormatService methodNameFormatService) {
        CheckUtils.assertNotNull(methodType,"addMethodNameFormat 参数 methodType 不能为空!");
        CheckUtils.assertNotNull(methodNameFormatService,"addMethodNameFormat 参数 methodNameFormatService 不能为空!");
        if (MethodEnums.ALL_METHOD.equals(methodType)) {
            this.commonMethodFormatService = methodNameFormatService;
        } else {
            if (this.methodNameFormatServiceMap == null){
                this.methodNameFormatServiceMap = new ConcurrentHashMap<>();
            }
            this.methodNameFormatServiceMap.put(methodType, methodNameFormatService);
        }
        return this;
    }

    /**
     * 自定义数据库与 java 实体类型映射
     *
     * 生成器仅收录了常用类型的转换 如果生成时发现数据库类型未转换成对应 java 数据类型
     * 或者转换的 java 数据类型不是自己想要的类型 可使用此方法新增或修改类型转换
     *
     * 例如数据库类型为 NUMBER, 如果想映射到实体类中对应类型为 String ,设置如下：
     * new GeneratorBuilder().fieldType2JavaType("NUMBER",String.class);
     *
     * @param dataBaseType  数据类型
     * @param javaTypeClass java 类
     * @return the current builder
     */
    public GeneratorBuilder fieldType2JavaType(@NotNull String dataBaseType, @NotNull Class<?> javaTypeClass) {
        CheckUtils.assertNotNull(dataBaseType,"fieldType2JavaType 请指定 dataBaseType, 即数据库字段类型.");
        CheckUtils.assertNotNull(javaTypeClass,"fieldType2JavaType 请指定 javaTypeClass, 即 JAVA 字段类型.");

        if (this.typeMapper == null){
            this.typeMapper = new HashMap<>();
        }
        this.typeMapper.put(dataBaseType.toUpperCase(), javaTypeClass);
        return this;
    }

    /**
     * 自定义数据库与 mybatis xml 中 jdbcType 类型映射
     *
     * 生成器仅收录了常用类型的转换 如果生成时发现数据库类型未转换成对应 jdbcType 数据类型
     * 或者转换的 jdbcType 数据类型有误 可使用此方法新增或修改类型转换
     *
     * 例如字段 createTime 为时间类型 DATE, 如果将 XML 生成类型改为 DATE ,可如下设置
     * new GeneratorBuilder().fieldType2JavaType("DATE", JdbcType.DATE);
     *    即 mybatis xml 中 #{createTime,jdbcType=DATE}
     * @param dataBaseType  数据类型
     * @param jdbcType      mybatis 配置文件中类型 如 #{id,jdbcType=VARCHAR}
     * @return the current builder
     */
    public GeneratorBuilder fieldType2JdbcType(@NotNull String dataBaseType, @NotNull JdbcType jdbcType) {
        CheckUtils.assertNotNull(dataBaseType,"fieldType2JavaType 请指定 dataBaseType, 即数据库字段类型.");
        CheckUtils.assertNotNull(dataBaseType,"fieldType2JavaType 请指定 jdbcType, 即 mybatis xml 中 jdbcType.");

        TypeConversion.addType2JdbcType(dataBaseType, jdbcType);
        return this;
    }

    /***
     * 替换指定模块的代码模板  暂只支持 freemarker 模板
     * @since 1.5.0
     * @param moduleTypeEnums 模块类型
     * @param templatePath 对应的 freemarker 模板路径
     * @return the current builder
     */
    public GeneratorBuilder replaceDefaultTemplate(ModuleTypeEnums moduleTypeEnums, String templatePath){
        CheckUtils.assertNotNull(moduleTypeEnums,"模块类型不能为空!");
        CheckUtils.assertNotNull(templatePath,"模板路径不能为空!");

        if (moduleTypeTemplatePathMap == null){
            moduleTypeTemplatePathMap = new HashMap<>();
        }
        moduleTypeTemplatePathMap.put(moduleTypeEnums, templatePath);
        return this;
    }

    /**
     * 添加自定义 java 模板
     * @param fileInfo java 模板信息
     * @return the current builder
     */
    public GeneratorBuilder addJavaTemplate(@NotNull JavaModuleInfo fileInfo){
        CheckUtils.assertNotNull(fileInfo,"添加模板不能为空!");

        if (this.javaModuleInfos == null){
            this.javaModuleInfos = new ArrayList<>();
        }
        this.javaModuleInfos.add(fileInfo);
        return this;
    }

    /**
     * 添加自定义模板
     * @param customizeModuleInfo 自定义模板信息
     * @return the current builder
     */
    public GeneratorBuilder addCustomizeModuleTemplate(@NotNull CustomizeModuleInfo customizeModuleInfo){
        CheckUtils.assertNotNull(customizeModuleInfo,"添加模板不能为空!");

        if (this.customizeModuleInfos == null){
            this.customizeModuleInfos = new ArrayList<>();
        }
        this.customizeModuleInfos.add(customizeModuleInfo);
        return this;
    }

    /**
     * 可自定义 生成器
     * @param generateService  生成器 service
     * @return the current builder
     */
    public GeneratorBuilder addGenerateService(@NotNull GenerateService generateService){
        CheckUtils.assertNotNull(generateService,"generateService 不能为空!");
        this.generateService = generateService;
        return this;
    }

    public Generator build(){
        return build("");
    }

    /**
     * <p>Builds {@link Generator} instances.</p>
     *
     * 配置文件使用
     * <pre><code>
     *
     * {@link org.apache.ibatis.io.Resources#getResourceAsStream(java.lang.String)}
     * </code></pre>
     * 加载, 配置文件路径需符合 {@link org.apache.ibatis.io.Resources#getResourceAsStream(java.lang.String)} 参数规则
     *
     * @param configPath 配置文件路径
     * @return Generator 生成器
     */
    public Generator build(@Nullable String configPath) {
        try {
            LogUtils.setLevel(LogLevelEnums.DEBUG);

            LogUtils.info("生成器文档地址: " + LinkConstants.DOC_URL);

            Properties properties = PropertiesUtils.loadProperties(configPath);
            if (this.proInfo != null){
                properties.putAll(proInfo);
            }
            // 校验配置信息
            CheckUtils.checkDatabaseConfig(properties);
            // 获取
            GenerateService generateService = GenerateServiceFactory.getGenerateService();

            ContextHolder.newInstance(properties);

            LogUtils.setLevel(ContextHolder.getConfig(ConfigConstants.LOG_LEVEL));
        } catch (GeneratorException ie){
            LogUtils.error(ie.getMessage());
            return new EmptyGenerator();
        } catch (Exception e){
            LogUtils.error(ErrorTypeEnums.INIT_CONFIG_ERROR.getMessage());
            LogUtils.printException(e);
            return new EmptyGenerator();
        }
        TypeConversion.init(typeMapper);

        FileOutPathInfo fileOutPathInfo = ContextHolder.getBean("FileOutPathInfo");
        // 需先设置格式化 service
        fileOutPathInfo.setClassNameFormatServiceMap(moduleNameFormatServiceMap);
        fileOutPathInfo.init();

        DefaultGenerator generator = new DefaultGenerator(fileOutPathInfo, new MethodInfo(methodNameFormatServiceMap, commonMethodFormatService));
        generator.initGenerateService(generateService);
        if (javaModuleInfos != null && javaModuleInfos.size() > 0){
            for (int i = 0; i < javaModuleInfos.size(); i++) {
                generator.addJavaTemplate(javaModuleInfos.get(i));
            }
        }
        if (customizeModuleInfos != null && customizeModuleInfos.size() > 0){
            for (int i = 0; i < customizeModuleInfos.size(); i++) {
                generator.addCustomizeModuleInfo(customizeModuleInfos.get(i));
            }
        }
        if (moduleTypeTemplatePathMap != null && !moduleTypeTemplatePathMap.isEmpty()){
            for (Map.Entry<ModuleTypeEnums, String> entry: moduleTypeTemplatePathMap.entrySet()) {
                generator.replaceDefaultTemplate(entry.getKey(), entry.getValue());
            }
        }
        return generator;
    }

    private Map<String, String> getMapFromKeyValuePairs(String[] properties) {
        Map<String, String> map = new HashMap<>();
        for (String property : properties) {
            int index = lowestIndexOf(property, ":", "=");
            String key = (index > 0) ? property.substring(0, index) : property;
            String value = (index > 0) ? property.substring(index + 1) : "";
            map.put(key, value);
        }
        return map;
    }

    private int lowestIndexOf(String property, String... candidates) {
        int index = -1;
        for (String candidate : candidates) {
            int candidateIndex = property.indexOf(candidate);
            if (candidateIndex > 0) {
                index = (index != -1) ? Math.min(index, candidateIndex) : candidateIndex;
            }
        }
        return index;
    }

}