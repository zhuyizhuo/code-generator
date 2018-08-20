package com.github.zhuyizhuo.generator.mybatis.vo;

import com.github.zhuyizhuo.generator.mybatis.convention.ClassCommentInfo;
import com.github.zhuyizhuo.generator.mybatis.convention.MethodCommentInfo;
import com.github.zhuyizhuo.generator.mybatis.convention.MethodInfo;
import com.github.zhuyizhuo.generator.mybatis.convention.StratificationInfo;

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

    public void init(TableInfo tableInfo) {
        setTableInfo(tableInfo);
        this.methodInfo.initMethodName(tableInfo.getJavaTableName());
        this.stratificationInfo.initFilesName(tableInfo);
        this.tableInfo.initXmlInfo(stratificationInfo);
    }
}
