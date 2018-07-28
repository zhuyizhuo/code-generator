package com.zhuyizhuo.generator.mybatis.database.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * class: MysqlDbInfo <br>
 * description: TODO <br>
 * time: 2018/7/27 19:12
 *
 * @author yizhuo <br>
 * @version 1.0
 */
public class MysqlDbInfo {
    private String tableSchema;

    private List<DbTableInfo> tableInfoList = new ArrayList<>();

    public String getTableSchema() {
        return tableSchema;
    }

    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }

    public List<DbTableInfo> getTableInfoList() {
        return tableInfoList;
    }

    public void setTableInfoList(List<DbTableInfo> tableInfoList) {
        this.tableInfoList = tableInfoList;
    }

    public void addTableInfoList(DbTableInfo tableInfo) {
        this.tableInfoList.add(tableInfo);
    }
}
