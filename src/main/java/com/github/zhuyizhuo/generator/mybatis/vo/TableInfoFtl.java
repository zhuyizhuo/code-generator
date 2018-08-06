package com.github.zhuyizhuo.generator.mybatis.vo;

import com.github.zhuyizhuo.generator.mybatis.dto.JavaColumnInfo;
import com.google.common.collect.Lists;
import com.github.zhuyizhuo.generator.mybatis.dto.JavaColumnInfo;

import java.util.LinkedHashSet;
import java.util.List;

/**
 * class: TableInfoFtl <br>
 * description: 生成模板所用对象 <br>
 * time: 2018/8/3 19:56
 *
 * @author yizhuo <br>
 * @version 1.0
 */
public class TableInfoFtl {
    /** 数据库名称 */
    private String tableSchema;
    /** 表名 */
    private String tableName;
    /** 表注释 */
    private String tableComment;
    /** java表名 驼峰 首字母大写 */
    private String javaTableName;
    /** 导入的类路径 */
    private LinkedHashSet<String> importPackages = new LinkedHashSet<String>();
    /** 表字段 */
    private List<JavaColumnInfo> columnLists = Lists.newArrayList();

    public String getTableSchema() {
        return tableSchema;
    }

    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public List<JavaColumnInfo> getColumnLists() {
        return columnLists;
    }

    public void addJavaColumnInfo(JavaColumnInfo javaColumnInfo) {
        this.columnLists.add(javaColumnInfo);
    }

    public String getJavaTableName() {
        return javaTableName;
    }

    public void setJavaTableName(String javaTableName) {
        this.javaTableName = javaTableName;
    }

    public LinkedHashSet<String> getImportPackages() {
        return importPackages;
    }

    public void addImportPackages(String importPackage) {
        this.importPackages.add(importPackage);
    }

    @Override
    public String toString() {
        return "TableInfoFtl{" +
                "tableSchema='" + tableSchema + '\'' +
                ", tableName='" + tableName + '\'' +
                ", tableComment='" + tableComment + '\'' +
                ", javaTableName='" + javaTableName + '\'' +
                ", importPackages=" + importPackages +
                ", columnLists=" + columnLists +
                '}';
    }
}