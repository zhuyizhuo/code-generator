package com.github.zhuyizhuo.generator.mybatis.generator.support;

import com.github.zhuyizhuo.generator.mybatis.dto.JavaColumnInfo;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * class: TableDefinition <br>
 * description: TODO <br>
 *
 * @author yizhuo <br>
 * @since 1.3.0
 */
public class TableDefinition {

    /** 数据库名称大写 */
    private String tableSchema;
    /** 表名大写 */
    private String tableName;
    /** 表注释 */
    private String tableComment;
    /**  表名转驼峰 首字母大写 */
    private String camelCaseTableName;
    /** 列信息定义 */
    private ColumnDefinition columnDefinition;

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

    public String getCamelCaseTableName() {
        return camelCaseTableName;
    }

    public void setCamelCaseTableName(String camelCaseTableName) {
        this.camelCaseTableName = camelCaseTableName;
    }

    public ColumnDefinition getColumnDefinition() {
        return columnDefinition;
    }

    public void setColumnDefinition(ColumnDefinition columnDefinition) {
        this.columnDefinition = columnDefinition;
    }
}
