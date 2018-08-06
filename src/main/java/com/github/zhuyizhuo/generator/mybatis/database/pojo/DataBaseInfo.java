package com.github.zhuyizhuo.generator.mybatis.database.pojo;

import java.util.List;

/**
 * @author yizhuo
 * @version 1.0
 * @date 2018/7/29 19:16
 */
public class DataBaseInfo {
    /** 数据库名称 */
    private String tableSchema;
    private List<String> tableNames;

    public String getTableSchema() {
        return tableSchema;
    }

    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }

    public List<String> getTableNames() {
        return tableNames;
    }

    public void setTableNames(List<String> tableNames) {
        this.tableNames = tableNames;
    }

    @Override
    public String toString() {
        return "{" +
                "tableSchema='" + tableSchema + '\'' +
                ", tableNames=" + tableNames +
                '}';
    }
}
