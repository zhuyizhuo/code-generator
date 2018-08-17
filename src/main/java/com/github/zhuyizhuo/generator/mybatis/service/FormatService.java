package com.github.zhuyizhuo.generator.mybatis.service;

/**
 * class: FormatService <br>
 * description: 格式化表名 <br>
 * time: 2018/8/16 19:29
 *
 * @author yizhuo <br>
 * @version 1.0
 */
public interface FormatService {

    /**
     * 表名称格式化
     * @param tableName 数据库表名称的大写
     * @return 格式化后的名称
     */
    String formatTableName(String tableName);

}
