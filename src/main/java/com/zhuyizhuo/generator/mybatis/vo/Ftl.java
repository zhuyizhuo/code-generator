package com.zhuyizhuo.generator.mybatis.vo;

import com.zhuyizhuo.generator.mybatis.convention.CommentInfo;
import com.zhuyizhuo.generator.mybatis.convention.MethodInfo;
import com.zhuyizhuo.generator.mybatis.convention.StratificationInfo;
import com.zhuyizhuo.generator.mybatis.dto.JavaTableInfo;
import com.zhuyizhuo.generator.mybatis.database.pojo.DbTableInfo;
import com.zhuyizhuo.generator.utils.GeneratorStringUtils;

/**
 * @author yizhuo
 * @version 1.0
 * @date 2018/7/29 17:44
 */
public class Ftl {

    private StratificationInfo stratificationInfo;
    private MethodInfo methodInfo;
    private CommentInfo commentInfo;
    private TableInfoFtl tableInfo;
    /** 参数类型 */
    private String parameterType;

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

    public CommentInfo getCommentInfo() {
        return commentInfo;
    }

    public void setCommentInfo(CommentInfo commentInfo) {
        this.commentInfo = commentInfo;
    }

    public TableInfoFtl getTableInfo() {
        return tableInfo;
    }

    public void setTableInfo(TableInfoFtl tableInfo) {
        this.tableInfo = tableInfo;
    }

    public String getParameterType() {
        return parameterType;
    }

    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }

    public void init() {
//        setParameterType(stratificationInfo.getPojoFullPackage()+"."+stratificationInfo.getPojoName());
        setParameterType(GeneratorStringUtils.firstLower(stratificationInfo.getPojoName()));
    }
}
