package com.github.zhuyizhuo.generator.mybatis.vo;

import com.github.zhuyizhuo.generator.mybatis.constants.ConfigConstants;
import com.github.zhuyizhuo.generator.mybatis.convention.StratificationInfo;
import com.github.zhuyizhuo.generator.mybatis.database.pojo.ColumnInfo;
import com.github.zhuyizhuo.generator.mybatis.dto.JavaColumnInfo;
import com.github.zhuyizhuo.generator.mybatis.generator.support.MybatisXmlDefinition;
import com.github.zhuyizhuo.generator.utils.GeneratorStringUtils;
import com.github.zhuyizhuo.generator.utils.PropertiesUtils;
import com.google.common.collect.Lists;

import java.util.LinkedHashSet;
import java.util.List;

/**
 * class: TableInfo <br>
 * description: 生成模板所用对象 <br>
 * time: 2018/8/3 19:56
 *
 * @author yizhuo <br>
 * @version 1.0
 */
public class TableInfo {
    /** 数据库名称 */
    private String tableSchema;
    /** 表名 */
    private String tableName;
    /** 表注释 */
    private String tableComment;
    /** 表名转驼峰 首字母大写 */
    private String tableNameCamelCase;
    /** 导入的类路径 */
    private LinkedHashSet<String> importPackages = new LinkedHashSet<String>();
    /** 表所有字段 */
    private List<JavaColumnInfo> columnLists = Lists.newArrayList();
    /** 主键字段 */
    private List<JavaColumnInfo> primaryKeyColumns = Lists.newArrayList();
    /** 非主键字段 */
    private List<JavaColumnInfo> otherColumns = Lists.newArrayList();
    /** 是否有主键 */
    private boolean hasPrimaryKey = true;
    /** 是否单个主键 */
    private boolean singlePrimaryKey = true;

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

    public String getTableNameCamelCase() {
        return tableNameCamelCase;
    }

    public void setTableNameCamelCase(String tableNameCamelCase) {
        this.tableNameCamelCase = tableNameCamelCase;
    }

    public LinkedHashSet<String> getImportPackages() {
        return importPackages;
    }

    public void addImportPackage(String importPackage) {
        this.importPackages.add(importPackage);
    }

    public void addPrimaryKeyColumn(List<ColumnInfo> keyName) {
        if (keyName == null || keyName.size() == 0){
            this.hasPrimaryKey = false;
            return;
        }
        for (int i = 0; i < keyName.size(); i++) {
            ColumnInfo columnInfo = keyName.get(i);
            String columnName = columnInfo.getColumnName();
            for (int j = 0; j < columnLists.size(); j++) {
                JavaColumnInfo javaColumnInfo = columnLists.get(j);
                if (columnName != null && columnName.equalsIgnoreCase(javaColumnInfo.getColumnName())){
                    this.primaryKeyColumns.add(javaColumnInfo);
                    break;
                }
            }
        }

        initOtherColumns();
        this.hasPrimaryKey = true;
        this.singlePrimaryKey = primaryKeyColumns.size() == 1;
    }

    private void initOtherColumns() {
        for (int i = 0; i < columnLists.size(); i++) {
            JavaColumnInfo javaColumnInfo = columnLists.get(i);
            if (!primaryKeyColumns.contains(javaColumnInfo)){
                this.otherColumns.add(javaColumnInfo);
            }
        }
    }

    public List<JavaColumnInfo> getPrimaryKeyColumns() {
        return primaryKeyColumns;
    }

    public boolean isHasPrimaryKey() {
        return hasPrimaryKey;
    }

    public void setHasPrimaryKey(boolean hasPrimaryKey) {
        this.hasPrimaryKey = hasPrimaryKey;
    }

    public List<JavaColumnInfo> getOtherColumns() {
        return otherColumns;
    }

    public void setOtherColumns(List<JavaColumnInfo> otherColumns) {
        this.otherColumns = otherColumns;
    }

    public boolean isSinglePrimaryKey() {
        return singlePrimaryKey;
    }

    public void setSinglePrimaryKey(boolean singlePrimaryKey) {
        this.singlePrimaryKey = singlePrimaryKey;
    }

    @Override
    public String toString() {
        return "TableInfo{" +
                "tableSchema='" + tableSchema + '\'' +
                ", tableName='" + tableName + '\'' +
                ", tableComment='" + tableComment + '\'' +
                ", tableNameCamelCase='" + tableNameCamelCase + '\'' +
                ", importPackages=" + importPackages +
                ", columnLists=" + columnLists +
                '}';
    }

}