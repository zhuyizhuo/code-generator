package com.github.zhuyizhuo.generator.mybatis.generator;

import com.github.zhuyizhuo.generator.mybatis.annotation.Nullable;
import com.github.zhuyizhuo.generator.mybatis.constants.ConfigConstants;
import com.github.zhuyizhuo.generator.mybatis.convention.ClassCommentInfo;
import com.github.zhuyizhuo.generator.mybatis.convention.FileOutPathInfo;
import com.github.zhuyizhuo.generator.mybatis.convention.MethodCommentInfo;
import com.github.zhuyizhuo.generator.mybatis.convention.MethodInfo;
import com.github.zhuyizhuo.generator.mybatis.convention.StratificationInfo;
import com.github.zhuyizhuo.generator.mybatis.enums.MethodEnums;
import com.github.zhuyizhuo.generator.mybatis.enums.ModuleTypeEnums;
import com.github.zhuyizhuo.generator.mybatis.extension.service.FormatService;
import com.github.zhuyizhuo.generator.mybatis.service.ContextHolder;
import com.github.zhuyizhuo.generator.mybatis.vo.GenerateInfo;
import com.github.zhuyizhuo.generator.utils.GeneratorStringUtils;
import com.github.zhuyizhuo.generator.utils.LogUtils;
import com.github.zhuyizhuo.generator.utils.PropertiesUtils;
import com.github.zhuyizhuo.generator.utils.TypeConversion;
import org.apache.ibatis.type.JdbcType;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * class: GeneratorBuilder <br>
 * description: Builds {@link Generator} instances. <br>
 *
 * @author yizhuo <br>
 * @version 1.2.0
 */
public class GeneratorBuilder {
    /**
     * 方法信息
     */
    private MethodInfo methodInfo = new MethodInfo();
    /**
     * 文件输出路径信息
     */
    private FileOutPathInfo fileOutPathInfo = new FileOutPathInfo();
    /***
     * key 数据库字段类型
     * value java 数据类型
     */
    private final Map<String, Class<?>> typeMapper = new HashMap<String, Class<?>>();

    private Map<ModuleTypeEnums, FormatService> nameFormatMap = new HashMap<>();

    public GeneratorBuilder() {
    }

    /**
     * 自定义xml生成名称
     */
    public GeneratorBuilder addXmlNameFormat(FormatService formatService) {
        this.fileOutPathInfo.addXmlNameFormat(formatService);
        return this;
    }

    /**
     * 自定义pojo生成名称
     */
    public GeneratorBuilder addBeanNameFormat(FormatService formatService) {
        this.nameFormatMap.put(ModuleTypeEnums.POJO, formatService);
        return this;
    }

    /**
     * 自定义mapper生成名称
     */
    public GeneratorBuilder addMapperNameFormat(FormatService formatService) {
        this.nameFormatMap.put(ModuleTypeEnums.MAPPER, formatService);
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
    public GeneratorBuilder addTypeMapper(String dataBaseType, JdbcType jdbcType, Class<?> javaTypeClass) {
        if (GeneratorStringUtils.isNotBlank(dataBaseType) && javaTypeClass != null) {
            this.typeMapper.put(dataBaseType, javaTypeClass);
        }
        if (GeneratorStringUtils.isNotBlank(dataBaseType) && jdbcType != null) {
            TypeConversion.addType2JdbcType(dataBaseType, jdbcType.toString());
        }
        return this;
    }

    public GeneratorBuilder addMethodFormat(@Nullable MethodEnums method, FormatService formatService) {
        if (method == null) {
            this.methodInfo.addAllFormat(formatService);
        } else {
            this.methodInfo.addMethodFormat(method, formatService);
        }
        return this;
    }

    public Generator build(InputStream inputStream) {
        try {
            PropertiesUtils.loadProperties(inputStream);
        } catch (Exception e) {
            LogUtils.printErrInfo("加载配置文件失败.");
        }

        // 初始化常量
        if (GeneratorStringUtils.isBlank(ConfigConstants.tableRegex)) {
            String separator = PropertiesUtils.getProperties(ConfigConstants.TABLE_SEPARATOR);
            if (GeneratorStringUtils.isBlank(separator)) {
                ConfigConstants.tableRegex = "_";
            } else {
                ConfigConstants.tableRegex = separator;
            }
        }

        ContextHolder configScanner = new ContextHolder();
        try {
            configScanner.init();
        } catch (Exception e){
            e.printStackTrace();
        }

        ClassCommentInfo classCommentInfo = configScanner.getBean("ClassCommentInfo");
        StratificationInfo stratificationInfo = configScanner.getBean("stratificationInfo");
        MethodCommentInfo methodCommentInfo = configScanner.getBean("methodCommentInfo");
        TypeConversion.init(typeMapper);
        stratificationInfo.init(nameFormatMap);
        GenerateInfo generateInfo = new GenerateInfo();
        generateInfo.setClassCommentInfo(classCommentInfo);
        generateInfo.setMethodCommentInfo(methodCommentInfo);
        generateInfo.setMethodInfo(methodInfo);
        fileOutPathInfo.init(stratificationInfo);

        return new Generator(generateInfo, fileOutPathInfo, stratificationInfo);
    }

}