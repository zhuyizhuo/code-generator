package com.github.zhuyizhuo.generator.mybatis.generator;

import com.github.zhuyizhuo.generator.mybatis.constants.ConfigConstants;
import com.github.zhuyizhuo.generator.mybatis.convention.*;
import com.github.zhuyizhuo.generator.mybatis.enums.DbTypeEnums;
import com.github.zhuyizhuo.generator.mybatis.factory.DbServiceFactory;
import com.github.zhuyizhuo.generator.mybatis.service.DbService;
import com.github.zhuyizhuo.generator.mybatis.service.FormatService;
import com.github.zhuyizhuo.generator.mybatis.vo.Ftl;
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

    private CommentInfo commentInfo;
    private MethodInfo methodInfo;
    private StratificationInfo stratificationInfo;
    private FileOutPathInfo fileOutPathInfo;

    /***
     * key 数据库字段类型
     * value java 数据类型
     */
    private final Map<String,Class<?>> typeMapper = new HashMap<String,Class<?>>();

    public GeneratorBuilder() {
        this(new CommentInfo());
    }

    public GeneratorBuilder(CommentInfo commentInfo) {
        this(commentInfo, new MethodInfo());
    }

    public GeneratorBuilder(CommentInfo commentInfo, MethodInfo methodInfo) {
        this(commentInfo,methodInfo,new StratificationInfo());
    }

    public GeneratorBuilder(CommentInfo commentInfo, MethodInfo methodInfo, StratificationInfo stratificationInfo) {
        this(commentInfo,methodInfo,stratificationInfo,new FileOutPathInfo());
    }

    public GeneratorBuilder(CommentInfo commentInfo, MethodInfo methodInfo, StratificationInfo stratificationInfo, FileOutPathInfo fileOutPathInfo) {
        this.commentInfo = commentInfo;
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

    public MethodCommentInfo getMethodComment(){
        return this.methodInfo;
    }

    public Generator build(InputStream inputStream){
        try {
            PropertiesUtils.loadProperties(inputStream);
        } catch (Exception e) {
            LogUtils.printInfo("加载配置文件失败.");
        }
        DbService service =  DbServiceFactory.getDbService();

        initTypeCoversion();

        Ftl ftl = new Ftl();
        ftl.setCommentInfo(commentInfo);
        ftl.setMethodInfo(methodInfo);
        ftl.setStratificationInfo(stratificationInfo);
        stratificationInfo.init();
        fileOutPathInfo.init(stratificationInfo);
        commentInfo.init();
        return new Generator(service,ftl,fileOutPathInfo);
    }

    private void initTypeCoversion() {
        if (!typeMapper.isEmpty()) {
            String dbType = PropertiesUtils.getProperties(ConfigConstants.DB_TYPE);
            for (Map.Entry<String, Class<?>> entry : typeMapper.entrySet()) {
                Class<?> value = entry.getValue();
                TypeConversion.addJavaDataTypeFullPath(value);
                TypeConversion.addParameterType(value);
                if (DbTypeEnums.MYSQL.toString().equals(dbType)) {
                    TypeConversion.addMySqlDbType2Java(entry.getKey(),value.getSimpleName());
                } else {
                    TypeConversion.addOracleDbType2Java(entry.getKey(),value.getSimpleName());
                }
            }
        }
    }

}