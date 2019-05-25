package com.github.zhuyizhuo.generator.mybatis.vo;

import com.github.zhuyizhuo.generator.mybatis.constants.ConfigConstants;
import com.github.zhuyizhuo.generator.mybatis.convention.ClassCommentInfo;
import com.github.zhuyizhuo.generator.mybatis.dto.JavaClassDefinition;
import com.github.zhuyizhuo.generator.mybatis.dto.MethodDescription;
import com.github.zhuyizhuo.generator.mybatis.dto.JavaColumnInfo;
import com.github.zhuyizhuo.generator.mybatis.enums.ModuleTypeEnums;
import com.github.zhuyizhuo.generator.mybatis.generator.support.mybatis.MybatisXmlDefinition;
import com.github.zhuyizhuo.generator.utils.GeneratorStringUtils;
import com.github.zhuyizhuo.generator.utils.PropertiesUtils;

import java.util.List;
import java.util.Map;

/**
 * @author yizhuo
 * @version 1.0
 * time: 2018/7/29 17:44
 */
public class GenerateInfo {
    /** 分层信息 */
    private Map<String, JavaClassDefinition> javaClassDefinition;
    /** 方法信息 */
    private Map<String, MethodDescription> methodDescription;
    /** 类注释信息 */
    private ClassCommentInfo classCommentInfo;
    /** 表信息 */
    private TableInfo tableInfo;
    /** mybatis xml 定义*/
    private MybatisXmlDefinition mybatisXmlDefinition;

    public GenerateInfo() { }

    public ClassCommentInfo getClassCommentInfo() {
        return classCommentInfo;
    }

    public void setClassCommentInfo(ClassCommentInfo classCommentInfo) {
        this.classCommentInfo = classCommentInfo;
    }

    public TableInfo getTableInfo() {
        return tableInfo;
    }

    public MybatisXmlDefinition getMybatisXmlDefinition() {
        return mybatisXmlDefinition;
    }

    public void init(TableInfo tableInfo, Map<String, JavaClassDefinition> javaClassDefinition, Map<String, MethodDescription> methodDescriptionMap) {
        // 初始化 dao pojo 名称 及 包路径
        this.tableInfo = tableInfo;
        this.javaClassDefinition = javaClassDefinition;
        this.methodDescription = methodDescriptionMap;
        // 初始化 xml 内容
        initXmlInfo();
    }

    public void initXmlInfo() {
        mybatisXmlDefinition = new MybatisXmlDefinition();
        boolean useTypeAliases = PropertiesUtils.getBooleanPropertiesDefaultFalse(ConfigConstants.PARAMETER_TYPE_USE_TYPE_ALIASES);

        JavaClassDefinition pojoDefinition = javaClassDefinition.get(ModuleTypeEnums.POJO.toString());
        JavaClassDefinition mapperDefinition = javaClassDefinition.get(ModuleTypeEnums.MAPPER.toString());
        mybatisXmlDefinition.setParameterType(
             useTypeAliases  ? GeneratorStringUtils.firstLower(pojoDefinition.getClassName())
                    : pojoDefinition.getFullPackage()+"."+pojoDefinition.getClassName());

        mybatisXmlDefinition.setTableName(tableInfo.getTableName());
        mybatisXmlDefinition.setTableSchema(tableInfo.getTableSchema());
        mybatisXmlDefinition.setTableNameCamelCase(tableInfo.getTableNameCamelCase());
        mybatisXmlDefinition.setTableComment(tableInfo.getTableComment());
        mybatisXmlDefinition.getResultMap().setId(GeneratorStringUtils.firstLower(tableInfo.getTableNameCamelCase())+"ResultMap");
        mybatisXmlDefinition.getResultMap().setType(mybatisXmlDefinition.getParameterType());

        mybatisXmlDefinition.setNameSpace(mapperDefinition.getFullPackage()+"." +mapperDefinition.getClassName());
        mybatisXmlDefinition.addMybatisXmlHeaderLine("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        mybatisXmlDefinition.addMybatisXmlHeaderLine("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">");

        List<JavaColumnInfo> columns = tableInfo.getColumnLists();
        for (int i = 0; i < columns.size(); i++) {
            JavaColumnInfo javaColumnInfo = columns.get(i);
            mybatisXmlDefinition.addColumn(javaColumnInfo);
        }
    }

    public Map<String, MethodDescription> getMethodDescription() {
        return methodDescription;
    }

    public Map<String, JavaClassDefinition> getJavaClassDefinition() {
        return javaClassDefinition;
    }
}
