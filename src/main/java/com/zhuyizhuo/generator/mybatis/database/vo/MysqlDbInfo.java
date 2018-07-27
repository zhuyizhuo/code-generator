package com.zhuyizhuo.generator.mybatis.database.vo;

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

    private List<DbTableInfo> tableInfoList = new ArrayList<>();

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
