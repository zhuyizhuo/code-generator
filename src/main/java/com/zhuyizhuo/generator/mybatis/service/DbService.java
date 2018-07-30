package com.zhuyizhuo.generator.mybatis.service;

import com.zhuyizhuo.generator.mybatis.database.pojo.DbTableInfo;

import java.util.List;

/**
 * class: DbService <br>
 * description: 数据库抽象接口 <br>
 * time: 2018/7/30 12:56
 *
 * @author yizhuo <br>
 * @version 1.0
 */
public interface DbService {

    List<DbTableInfo> getTableColumns();

}
