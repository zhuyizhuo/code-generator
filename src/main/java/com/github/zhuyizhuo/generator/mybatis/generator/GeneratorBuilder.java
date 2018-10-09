package com.github.zhuyizhuo.generator.mybatis.generator;

import com.github.zhuyizhuo.generator.mybatis.constants.ConfigConstants;
import com.github.zhuyizhuo.generator.mybatis.convention.*;
import com.github.zhuyizhuo.generator.mybatis.extension.service.GeneratorService;
import com.github.zhuyizhuo.generator.mybatis.extension.service.impl.FreemarkerGenerator;
import com.github.zhuyizhuo.generator.mybatis.factory.DbServiceFactory;
import com.github.zhuyizhuo.generator.mybatis.extension.service.FormatService;
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
    /** 类注释 */
    private ClassCommentInfo classCommentInfo;
    /** 方法注释 */
    private MethodCommentInfo methodCommentInfo;
    /** 方法信息 */
    private MethodInfo methodInfo;
    /** 分层信息 */
    private StratificationInfo stratificationInfo;
    /** 文件输出路径信息 */
    private FileOutPathInfo fileOutPathInfo;
    /** 自定义生成service */
    private GeneratorService generatorService;
    /***
     * key 数据库字段类型
     * value java 数据类型
     */
    private final Map<String,Class<?>> typeMapper = new HashMap<String,Class<?>>();

    public GeneratorBuilder() {
        this(new ClassCommentInfo());
    }

    public GeneratorBuilder(ClassCommentInfo classCommentInfo) {
        this(classCommentInfo, new MethodInfo());
    }

    public GeneratorBuilder(ClassCommentInfo classCommentInfo, MethodInfo methodInfo) {
        this(classCommentInfo,methodInfo,new StratificationInfo());
    }

    public GeneratorBuilder(ClassCommentInfo classCommentInfo, MethodInfo methodInfo, StratificationInfo stratificationInfo) {
        this(classCommentInfo,methodInfo,stratificationInfo,new FileOutPathInfo());
    }

    public GeneratorBuilder(ClassCommentInfo classCommentInfo, MethodInfo methodInfo, StratificationInfo stratificationInfo, FileOutPathInfo fileOutPathInfo) {
        this(classCommentInfo,methodInfo,stratificationInfo,fileOutPathInfo,new MethodCommentInfo());
    }

    public GeneratorBuilder(ClassCommentInfo classCommentInfo, MethodInfo methodInfo, StratificationInfo stratificationInfo, FileOutPathInfo fileOutPathInfo, MethodCommentInfo methodCommentInfo) {
        this.classCommentInfo = classCommentInfo;
        this.methodCommentInfo = methodCommentInfo;
        this.methodInfo = methodInfo;
        this.stratificationInfo = stratificationInfo;
        this.fileOutPathInfo = fileOutPathInfo;
    }

    /**
     * 自定义xml生成名称
     * @param formatService
     * @return
     */
    public GeneratorBuilder addXmlNameFormat(FormatService formatService){
        this.stratificationInfo.addXmlNameFormat(formatService);
        return this;
    }

    /**
     * 自定义pojo生成名称
     * @param formatService
     * @return
     */
    public GeneratorBuilder addBeanNameFormat(FormatService formatService){
        this.stratificationInfo.addBeanNameFormat(formatService);
        return this;
    }

    /**
     * 自定义mapper生成名称
     * @param formatService
     * @return
     */
    public GeneratorBuilder addMapperNameFormat(FormatService formatService){
        this.stratificationInfo.addDaoNameFormat(formatService);
        return this;
    }

    /**
     * 自定义数据库分隔符 默认为下划线
     * @param tableRegex
     * @return
     */
    public GeneratorBuilder addTableRegex(String tableRegex){
        if (GeneratorStringUtils.isNotBlank(tableRegex)){
            ConfigConstants.tableRegex = tableRegex;
        }
        return this;
    }

    /**
     * 自定义数据库与java类型映射
     * 例如数据库类型为NUMBER 生成器默认映射为Integer 可自定义映射 将生成类型指定为String
     *  use like this :
     *      new GeneratorBuilder().addTypeMapper("NUMBER",JdbcType.VARCHAR,String.class);
     * @param dataBaseType 数据类型
     * @param jdbcType mybatis 配置文件中类型 如 #{id,jdbcType=VARCHAR}
     * @see
     * @param javaTypeClass
     * @return
     */
    public GeneratorBuilder addTypeMapper(String dataBaseType,JdbcType jdbcType,Class<?> javaTypeClass){
        if (GeneratorStringUtils.isNotBlank(dataBaseType) && javaTypeClass != null){
            this.typeMapper.put(dataBaseType,javaTypeClass);
        }
        if (GeneratorStringUtils.isNotBlank(dataBaseType) && jdbcType != null) {
            TypeConversion.addType2JdbcType(dataBaseType, jdbcType.toString());
        }
        return this;
    }

    /**
     * 自定义方法注释
     * @param commentInfo 方法注释对象
     * @return
     */
    public GeneratorBuilder addMethodComment(MethodCommentInfo commentInfo){
        this.methodCommentInfo = commentInfo;
        return this;
    }

    /**
     * 自定义生成器service
     * @param generatorService
     * @return
     */
    public GeneratorBuilder addGeneratorService(GeneratorService generatorService){
        this.generatorService = generatorService;
        return this;
    }

    public Generator build(InputStream inputStream){
        try {
            PropertiesUtils.loadProperties(inputStream);
        } catch (Exception e) {
            LogUtils.printErrInfo("加载配置文件失败.");
        }
        TypeConversion.init(typeMapper);
        stratificationInfo.init();
        classCommentInfo.init();
        GenerateInfo generateInfo = new GenerateInfo();
        generateInfo.setClassCommentInfo(classCommentInfo);
        generateInfo.setMethodCommentInfo(methodCommentInfo);
        generateInfo.setMethodInfo(methodInfo);
        generateInfo.setStratificationInfo(stratificationInfo);
        fileOutPathInfo.init(stratificationInfo);
        if (generatorService == null){
            generatorService = new FreemarkerGenerator();
        }
        return new Generator(DbServiceFactory.getDbService(), generateInfo, generatorService, fileOutPathInfo);
    }

}