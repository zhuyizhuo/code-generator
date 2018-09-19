package com.github.zhuyizhuo.generator.mybatis.vo;

import com.github.zhuyizhuo.generator.mybatis.constants.ConfigConstants;
import com.github.zhuyizhuo.generator.mybatis.convention.ClassCommentInfo;
import com.github.zhuyizhuo.generator.mybatis.convention.MethodCommentInfo;
import com.github.zhuyizhuo.generator.mybatis.convention.MethodInfo;
import com.github.zhuyizhuo.generator.mybatis.convention.StratificationInfo;
import com.github.zhuyizhuo.generator.mybatis.dto.JavaColumnInfo;
import com.github.zhuyizhuo.generator.mybatis.generator.support.mybatis.MybatisXmlDefinition;
import com.github.zhuyizhuo.generator.utils.GeneratorStringUtils;
import com.github.zhuyizhuo.generator.utils.PropertiesUtils;

import java.util.List;

/**
 * @author yizhuo
 * @version 1.0
 * time: 2018/7/29 17:44
 */
public class GenerateInfo {
    /** 分层信息 */
    private StratificationInfo stratificationInfo;
    /** 类注释信息 */
    private ClassCommentInfo classCommentInfo;
    /** 方法注释信息 */
    private MethodCommentInfo methodCommentInfo;
    /** 方法信息 */
    private MethodInfo methodInfo;
    /** 表信息 */
    private TableInfo tableInfo;
    /** mybatis xml 定义*/
    private MybatisXmlDefinition mybatisXmlDefinition;

    public GenerateInfo() { }

    public StratificationInfo getStratificationInfo() {
        return stratificationInfo;
    }

    public void setStratificationInfo(StratificationInfo stratificationInfo) {
        this.stratificationInfo = stratificationInfo;
    }

    public MethodInfo getMethodInfo() {
        return methodInfo;
    }

    public void setMethodInfo(MethodInfo methodInfo) {
        this.methodInfo = methodInfo;
    }

    public ClassCommentInfo getClassCommentInfo() {
        return classCommentInfo;
    }

    public void setClassCommentInfo(ClassCommentInfo classCommentInfo) {
        this.classCommentInfo = classCommentInfo;
    }

    public TableInfo getTableInfo() {
        return tableInfo;
    }

    public void setTableInfo(TableInfo tableInfo) {
        this.tableInfo = tableInfo;
    }

    public MethodCommentInfo getMethodCommentInfo() {
        return methodCommentInfo;
    }

    public void setMethodCommentInfo(MethodCommentInfo methodCommentInfo) {
        this.methodCommentInfo = methodCommentInfo;
    }

    public MybatisXmlDefinition getMybatisXmlDefinition() {
        return mybatisXmlDefinition;
    }

    public void init(TableInfo tableInfo) {
        setTableInfo(tableInfo);
        this.methodInfo.initMethodName(tableInfo.getTableNameCamelCase());
        this.stratificationInfo.initFilesName(tableInfo);
        this.initXmlInfo(stratificationInfo);
    }

    public void initXmlInfo(StratificationInfo stratificationInfo) {
        mybatisXmlDefinition = new MybatisXmlDefinition();
        boolean useTypeAliases = PropertiesUtils.getBooleanPropertiesDefaultFalse(ConfigConstants.PARAMETER_TYPE_USE_TYPE_ALIASES);
        if (useTypeAliases){
            mybatisXmlDefinition.setParameterType(GeneratorStringUtils.firstLower(stratificationInfo.getPojoName()));
        } else {
            mybatisXmlDefinition.setParameterType(stratificationInfo.getPojoFullPackage()+"."+stratificationInfo.getPojoName());
        }

        mybatisXmlDefinition.setTableName(tableInfo.getTableName());
        mybatisXmlDefinition.setTableSchema(tableInfo.getTableSchema());
        mybatisXmlDefinition.setTableNameCamelCase(tableInfo.getTableNameCamelCase());
        mybatisXmlDefinition.setTableComment(tableInfo.getTableComment());
        mybatisXmlDefinition.getResultMap().setId(GeneratorStringUtils.firstLower(tableInfo.getTableNameCamelCase())+"ResultMap");
        mybatisXmlDefinition.getResultMap().setType(mybatisXmlDefinition.getParameterType());

        mybatisXmlDefinition.setNameSpace(stratificationInfo.getDaoFullPackage()+"." +stratificationInfo.getDaoName());
        mybatisXmlDefinition.addMybatisXmlHeaderLine("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        mybatisXmlDefinition.addMybatisXmlHeaderLine("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">");

        List<JavaColumnInfo> columns = tableInfo.getColumnLists();
        for (int i = 0; i < columns.size(); i++) {
            JavaColumnInfo javaColumnInfo = columns.get(i);
            mybatisXmlDefinition.addColumn(javaColumnInfo);
        }
    }
}
