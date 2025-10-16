package com.github.zhuyizhuo.generator.mybatis.generator;

import com.github.zhuyizhuo.generator.annotation.NotNull;
import com.github.zhuyizhuo.generator.annotation.Nullable;
import com.github.zhuyizhuo.generator.constants.ConfigConstants;
import com.github.zhuyizhuo.generator.constants.LinkConstants;
import com.github.zhuyizhuo.generator.enums.ErrorTypeEnums;
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
import com.github.zhuyizhuo.generator.utils.GeneratorStringUtils;
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
    /*** 数据库字段类型到Java类型的映射 */
    private Map<String, Class<?>> typeMapper;
    /** 指定方法名格式化 service map, 优先级高于格式化全部方法 */
    private Map<MethodEnums, FormatService> methodNameFormatServiceMap;
    /** 格式化全部方法 service, 优先级低于指定方法格式化 service */
    private FormatService commonMethodFormatService;
    /** 模块名格式化 Service MAP */
    private Map<String, FormatService> moduleNameFormatServiceMap;
    /** 扩展自定义Java模板 */
    private List<JavaModuleInfo> javaModuleInfos;
    /** 扩展自定义通用模板 */
    private List<CustomizeModuleInfo> customizeModuleInfos;
    /** 自定义生成器 */
    private GenerateService generateService;
    /** 配置信息 */
    private Properties proInfo;
    /** 模板类型及路径 用以替换系统中已定义的模板 */
    private Map<ModuleTypeEnums, String> moduleTypeTemplatePathMap;

    /**
     * 创建GeneratorBuilder实例，初始化所有集合对象
     */
    public GeneratorBuilder() {
        // 初始化所有集合对象，避免在setter方法中重复检查null
        this.typeMapper = new HashMap<>(16); // 默认初始容量，减少扩容
        this.methodNameFormatServiceMap = new ConcurrentHashMap<>(8); // 线程安全
        this.moduleNameFormatServiceMap = new ConcurrentHashMap<>(8); // 线程安全
        this.javaModuleInfos = new ArrayList<>(4);
        this.customizeModuleInfos = new ArrayList<>(4);
        this.moduleTypeTemplatePathMap = new HashMap<>(8);
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
     * @return 当前构建器实例
     */
    public GeneratorBuilder addModuleNameFormat(@NotNull ModuleTypeEnums moduleType, @NotNull FormatService moduleNameFormatService) {
        CheckUtils.assertNotNull(moduleType, "addModuleNameFormat 参数 moduleType 不能为空!");
        this.addModuleNameFormat(moduleType.toString(), moduleNameFormatService);
        return this;
    }

    /**
     * 自定义指定模块生成名称，此方法可用来扩展自定义模块
     * @param moduleType 模块类型
     * @param moduleNameFormatService 模块名格式化 Service
     * @since 1.4.0
     * @return 当前构建器实例
     */
    public GeneratorBuilder addModuleNameFormat(@NotNull String moduleType, @NotNull FormatService moduleNameFormatService) {
        CheckUtils.assertNotNull(moduleType, "addModuleNameFormat 参数 moduleType 不能为空!");
        CheckUtils.assertNotNull(moduleNameFormatService, "addModuleNameFormat 参数 moduleNameFormatService 不能为空!");
        this.moduleNameFormatServiceMap.put(moduleType, moduleNameFormatService);
        return this;
    }

    /**
     * 自定义方法生成名称
     * @param methodType 方法类型
     *       如果自定义全局方法生成格式化 则传入 MethodEnums.ALL_METHOD
     *         全局方法名格式化需配合配置 generate.java.method.{methodType}.name-format 使用
     * @param methodNameFormatService 格式化service
     * @since 1.4.0
     * @return 当前构建器实例
     */
    public GeneratorBuilder addMethodNameFormat(@NotNull MethodEnums methodType, @NotNull FormatService methodNameFormatService) {
        CheckUtils.assertNotNull(methodType, "addMethodNameFormat 参数 methodType 不能为空!");
        CheckUtils.assertNotNull(methodNameFormatService, "addMethodNameFormat 参数 methodNameFormatService 不能为空!");
        if (MethodEnums.ALL_METHOD.equals(methodType)) {
            this.commonMethodFormatService = methodNameFormatService;
        } else {
            this.methodNameFormatServiceMap.put(methodType, methodNameFormatService);
        }
        return this;
    }

    /**
     * 自定义数据库与java实体类型映射
     *
     * 生成器仅收录了常用类型的转换，如果生成时发现数据库类型未转换成对应java数据类型
     * 或者转换的java数据类型不是自己想要的类型，可使用此方法新增或修改类型转换
     *
     * 例如数据库类型为NUMBER，如果想映射到实体类中对应类型为String，设置如下：
     * new GeneratorBuilder().fieldType2JavaType("NUMBER",String.class);
     *
     * @param dataBaseType 数据库字段类型
     * @param javaTypeClass java类型
     * @return 当前构建器实例
     */
    public GeneratorBuilder fieldType2JavaType(@NotNull String dataBaseType, @NotNull Class<?> javaTypeClass) {
        CheckUtils.assertNotNull(dataBaseType, "fieldType2JavaType 请指定 dataBaseType, 即数据库字段类型.");
        CheckUtils.assertNotNull(javaTypeClass, "fieldType2JavaType 请指定 javaTypeClass, 即 JAVA 字段类型.");

        this.typeMapper.put(dataBaseType.toUpperCase(), javaTypeClass);
        return this;
    }

    /**
     * 自定义数据库与mybatis xml中jdbcType类型映射
     *
     * 生成器仅收录了常用类型的转换，如果生成时发现数据库类型未转换成对应jdbcType数据类型
     * 或者转换的jdbcType数据类型有误，可使用此方法新增或修改类型转换
     *
     * 例如字段createTime为时间类型DATE，如果将XML生成类型改为DATE，可如下设置
     * new GeneratorBuilder().fieldType2JdbcType("DATE", JdbcType.DATE);
     *    即mybatis xml中#{createTime,jdbcType=DATE}
     * @param dataBaseType 数据库字段类型
     * @param jdbcType mybatis配置文件中类型 如#{id,jdbcType=VARCHAR}
     * @return 当前构建器实例
     */
    public GeneratorBuilder fieldType2JdbcType(@NotNull String dataBaseType, @NotNull JdbcType jdbcType) {
        CheckUtils.assertNotNull(dataBaseType, "fieldType2JdbcType 请指定 dataBaseType, 即数据库字段类型.");
        CheckUtils.assertNotNull(jdbcType, "fieldType2JdbcType 请指定 jdbcType, 即 mybatis xml 中 jdbcType.");

        TypeConversion.addType2JdbcType(dataBaseType, jdbcType);
        return this;
    }

    /**
     * 替换指定模块的代码模板，暂只支持freemarker模板
     * @since 1.5.0
     * @param moduleTypeEnums 模块类型
     * @param templatePath 对应的freemarker模板路径
     * @return 当前构建器实例
     */
    public GeneratorBuilder replaceDefaultTemplate(ModuleTypeEnums moduleTypeEnums, String templatePath){
        CheckUtils.assertNotNull(moduleTypeEnums, "模块类型不能为空!");
        CheckUtils.assertNotNull(templatePath, "模板路径不能为空!");

        moduleTypeTemplatePathMap.put(moduleTypeEnums, templatePath);
        return this;
    }

    /**
     * 添加自定义java模板
     * @param fileInfo java模板信息
     * @return 当前构建器实例
     */
    public GeneratorBuilder addJavaTemplate(@NotNull JavaModuleInfo fileInfo){
        CheckUtils.assertNotNull(fileInfo, "添加模板不能为空!");
        this.javaModuleInfos.add(fileInfo);
        return this;
    }

    /**
     * 批量添加自定义java模板，提高多个模板添加时的效率
     * @param fileInfos java模板信息列表
     * @return 当前构建器实例
     */
    public GeneratorBuilder addJavaTemplates(@NotNull List<JavaModuleInfo> fileInfos){
        CheckUtils.assertNotNull(fileInfos, "添加模板列表不能为空!");
        if (!fileInfos.isEmpty()) {
            this.javaModuleInfos.addAll(fileInfos);
        }
        return this;
    }

    /**
     * 添加自定义通用模板
     * @param customizeModuleInfo 自定义模板信息
     * @return 当前构建器实例
     */
    public GeneratorBuilder addCustomizeModuleTemplate(@NotNull CustomizeModuleInfo customizeModuleInfo){
        CheckUtils.assertNotNull(customizeModuleInfo, "添加模板不能为空!");
        this.customizeModuleInfos.add(customizeModuleInfo);
        return this;
    }
    
    /**
     * 批量添加自定义通用模板，提高多个模板添加时的效率
     * @param moduleInfos 自定义模板信息列表
     * @return 当前构建器实例
     */
    public GeneratorBuilder addCustomizeModuleTemplates(@NotNull List<CustomizeModuleInfo> moduleInfos){
        CheckUtils.assertNotNull(moduleInfos, "添加模板列表不能为空!");
        if (!moduleInfos.isEmpty()) {
            this.customizeModuleInfos.addAll(moduleInfos);
        }
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
     * 重置构建器状态，可用于重用构建器实例
     * @return 当前构建器实例
     */
    public GeneratorBuilder reset() {
        this.typeMapper.clear();
        this.methodNameFormatServiceMap.clear();
        this.commonMethodFormatService = null;
        this.moduleNameFormatServiceMap.clear();
        this.javaModuleInfos.clear();
        this.customizeModuleInfos.clear();
        this.generateService = null;
        this.proInfo = null;
        this.moduleTypeTemplatePathMap.clear();
        return this;
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
            LogUtils.info("生成器文档地址: " + LinkConstants.DOC_URL);

            Properties properties = PropertiesUtils.loadProperties(configPath);
            if (this.proInfo != null){
                properties.putAll(proInfo);
            }
            String logLevel = properties.getProperty(ConfigConstants.LOG_LEVEL);

            if(GeneratorStringUtils.isNotBlank(logLevel)){
                LogUtils.setLevel(logLevel);
            }

            // 校验配置信息
            CheckUtils.checkDatabaseConfig(properties);

            ContextHolder.newInstance(properties);

            // 获取生成 service
            if (generateService == null){
                generateService = GenerateServiceFactory.getGenerateService();
            }
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

        DefaultGenerator generator = new DefaultGenerator(fileOutPathInfo, new MethodInfo(methodNameFormatServiceMap, commonMethodFormatService), generateService);
        
        // 优化集合遍历，使用增强型for循环
        if (!javaModuleInfos.isEmpty()){
            for (JavaModuleInfo javaModuleInfo : javaModuleInfos) {
                generator.addJavaTemplate(javaModuleInfo);
            }
        }
        if (!customizeModuleInfos.isEmpty()){
            for (CustomizeModuleInfo moduleInfo : customizeModuleInfos) {
                generator.addCustomizeModuleInfo(moduleInfo);
            }
        }
        if (!moduleTypeTemplatePathMap.isEmpty()){
            for (Map.Entry<ModuleTypeEnums, String> entry : moduleTypeTemplatePathMap.entrySet()) {
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