package com.github.zhuyizhuo.generator.mybatis.dto;

import com.github.zhuyizhuo.generator.mybatis.database.pojo.ColumnInfo;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * @author yizhuo
 * @version 1.0
 * @date 2018/7/29 16:39
 */
public class JavaTableInfo {
    /** java表名 */
    private String javaTableName;
    /** 生成文件名称 */
    private String fileName;
    /** 导入的类路径 */
    private LinkedHashSet<String> importPackages = new LinkedHashSet<String>();

    public String getJavaTableName() {
        return javaTableName;
    }

    public void setJavaTableName(String javaTableName) {
        this.javaTableName = javaTableName;
    }

    public LinkedHashSet<String> getImportPackages() {
        return importPackages;
    }

    public void setImportPackages(LinkedHashSet<String> importPackages) {
        this.importPackages = importPackages;
    }

    public void addImportPackages(String importPackage) {
        this.importPackages.add(importPackage);
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
