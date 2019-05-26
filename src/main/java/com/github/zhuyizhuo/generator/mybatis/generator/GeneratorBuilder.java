package com.github.zhuyizhuo.generator.mybatis.generator;

import com.github.zhuyizhuo.generator.mybatis.annotation.Nullable;
import com.github.zhuyizhuo.generator.mybatis.constants.ConfigConstants;
import com.github.zhuyizhuo.generator.mybatis.convention.FileOutPathInfo;
import com.github.zhuyizhuo.generator.mybatis.dto.MethodInfo;
import com.github.zhuyizhuo.generator.mybatis.enums.MethodEnums;
import com.github.zhuyizhuo.generator.mybatis.enums.ModuleTypeEnums;
import com.github.zhuyizhuo.generator.mybatis.extension.service.FormatService;
import com.github.zhuyizhuo.generator.mybatis.service.ContextHolder;
import com.github.zhuyizhuo.generator.utils.GeneratorStringUtils;
import com.github.zhuyizhuo.generator.utils.LogUtils;
import com.github.zhuyizhuo.generator.utils.PropertiesUtils;
import com.github.zhuyizhuo.generator.utils.TypeConversion;
import org.apache.ibatis.type.JdbcType;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * class: GeneratorBuilder <br>
 * description: Builds {@link Generator} instances. <br>
 *
 * @author yizhuo <br>
 * @version 1.2.0
 */
public class GeneratorBuilder {
    /***
     * key 数据库字段类型
     * value java 数据类型
     */
    private final Map<String, Class<?>> typeMapper = new HashMap<String, Class<?>>();
    /**
     * 方法名格式化 service map
     */
    private Map<MethodEnums,FormatService> methodNameFormatServiceMap = new ConcurrentHashMap<>();
    /**
     * 格式化全部方法 service 优先级低于指定方法格式化 service
     */
    private FormatService commonMethodFormatService;
    /***
     *  模块名格式化 Service MAP
     */
    private Map<ModuleTypeEnums, FormatService> moduleNameFormatServiceMap = new HashMap<>();

    public GeneratorBuilder() {
    }

    /**
     * 自定义指定模块生成名称
     * @since 1.4.0
     */
    public GeneratorBuilder addModuleNameFormat(ModuleTypeEnums moduleType, FormatService formatService) {
        this.moduleNameFormatServiceMap.put(moduleType, formatService);
        return this;
    }

    /**
     * 自定义方法生成名称
     * @param methodType 方法类型
     * @param formatService 格式化service
     * @since 1.4.0
     */
    public GeneratorBuilder addMethodFormat(@Nullable MethodEnums methodType, FormatService formatService) {
        if (methodType == null) {
            this.commonMethodFormatService = formatService;
        } else {
            this.methodNameFormatServiceMap.put(methodType, formatService);
        }
        return this;
    }

    /**
     * 自定义数据库与java类型映射
     * 例如数据库类型为NUMBER 生成器默认映射为Integer 可自定义映射 将生成类型指定为String
     * use like this :
     * new GeneratorBuilder().addTypeMapper("NUMBER",JdbcType.VARCHAR,String.class);
     *
     * @param dataBaseType  数据类型
     * @param jdbcType      mybatis 配置文件中类型 如 #{id,jdbcType=VARCHAR}
     * @param javaTypeClass java 类
     */
    public GeneratorBuilder addTypeMapper(String dataBaseType, @Nullable JdbcType jdbcType, @Nullable Class<?> javaTypeClass) {
        if (GeneratorStringUtils.isNotBlank(dataBaseType) && javaTypeClass != null) {
            this.typeMapper.put(dataBaseType, javaTypeClass);
        }
        if (GeneratorStringUtils.isNotBlank(dataBaseType) && jdbcType != null) {
            TypeConversion.addType2JdbcType(dataBaseType, jdbcType.toString());
        }
        return this;
    }

    public Generator build(InputStream inputStream) {
        try {
            PropertiesUtils.loadProperties(new BufferedReader(new InputStreamReader(inputStream,"UTF-8")));
        } catch (Exception e) {
            LogUtils.printErrInfo("加载配置文件失败.");
        }

        ContextHolder context = new ContextHolder();
        try {
            context.init();
        } catch (Exception e){
            LogUtils.printErrInfo("加载配置文件失败.");
            LogUtils.printException(e);
        }

        // 初始化常量
        ConfigConstants.tableRegex = PropertiesUtils.getConfig(ConfigConstants.TABLE_SEPARATOR);
        TypeConversion.init(typeMapper);

        FileOutPathInfo fileOutPathInfo = context.getBean("FileOutPathInfo");
        fileOutPathInfo.init(moduleNameFormatServiceMap);

        return new Generator(fileOutPathInfo, new MethodInfo(methodNameFormatServiceMap,commonMethodFormatService));
    }

}