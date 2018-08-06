package com.github.zhuyizhuo.generator.mybatis.database.mapper;

import com.github.zhuyizhuo.generator.mybatis.database.pojo.ColumnInfo;
import com.github.zhuyizhuo.generator.mybatis.database.pojo.DataBaseInfo;
import com.github.zhuyizhuo.generator.mybatis.database.pojo.DbTableInfo;

import java.util.List;

/**
 * @author yizhuo
 * @version 1.0
 * @date 2018/7/27 20:55
 */
public interface MysqlDataBaseMapper {
    /***
     * 根据表空间和表名查询所有的表信息
     * @param schema
     * @return
     */
    List<DbTableInfo> getTableNameListBySchema(DataBaseInfo schema);

    /***
     * 根据表信息查询所有列信息
     * @param schema
     * @return
     */
    List<ColumnInfo> getColumnListByTableName(DbTableInfo schema);
}

