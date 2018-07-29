package com.zhuyizhuo.generator.mybatis.database.vo;

import com.zhuyizhuo.generator.mybatis.constants.ConfigConstants;
import com.zhuyizhuo.generator.mybatis.database.convention.CommentInfo;
import com.zhuyizhuo.generator.mybatis.database.convention.MethodInfo;
import com.zhuyizhuo.generator.mybatis.database.convention.StratificationInfo;
import com.zhuyizhuo.generator.mybatis.database.dto.JavaTableInfo;
import com.zhuyizhuo.generator.mybatis.database.pojo.DbTableInfo;
import com.zhuyizhuo.generator.utils.PropertiesUtils;

/**
 * @author yizhuo
 * @version 1.0
 * @date 2018/7/29 17:44
 */
public class Ftl {

    private StratificationInfo stratificationInfo = new StratificationInfo();
    private MethodInfo methodInfo = new MethodInfo();
    private CommentInfo commentInfo = new CommentInfo();
    private JavaTableInfo javaTableInfo;
    private DbTableInfo dbTableInfo;
    private String fileOutPutPath;
    private String fileName;

    public MethodInfo getMethodInfo() {
        return methodInfo;
    }

    public void setMethodInfo(MethodInfo methodInfo) {
        this.methodInfo = methodInfo;
    }

    public StratificationInfo getStratificationInfo() {
        return stratificationInfo;
    }

    public void setStratificationInfo(StratificationInfo stratificationInfo) {
        this.stratificationInfo = stratificationInfo;
    }

    public JavaTableInfo getJavaTableInfo() {
        return javaTableInfo;
    }

    public void setJavaTableInfo(JavaTableInfo javaTableInfo) {
        this.javaTableInfo = javaTableInfo;
    }

    public String getFileOutPutPath() {
        return fileOutPutPath;
    }

    public void setFileOutPutPath(String fileOutPutPath) {
        this.fileOutPutPath = fileOutPutPath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public DbTableInfo getDbTableInfo() {
        return dbTableInfo;
    }

    public void setDbTableInfo(DbTableInfo dbTableInfo) {
        this.dbTableInfo = dbTableInfo;
    }

    public CommentInfo getCommentInfo() {
        return commentInfo;
    }

    public void setCommentInfo(CommentInfo commentInfo) {
        this.commentInfo = commentInfo;
    }
}
