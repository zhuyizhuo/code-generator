package com.zhuyizhuo.generator.mybatis.database.mapper;

import com.zhuyizhuo.generator.mybatis.database.pojo.ColumnInfo;
import com.zhuyizhuo.generator.mybatis.database.pojo.DbTableInfo;

import java.util.List;

/**
 * @author yizhuo
 * @version 1.0
 * @date 2018/7/27 20:55
 */
public interface MysqlDataBaseMapper {
    List<DbTableInfo> getTableNameListBySchema(DbTableInfo schema);

    List<ColumnInfo> getColumnListByTableName(DbTableInfo schema);
}

