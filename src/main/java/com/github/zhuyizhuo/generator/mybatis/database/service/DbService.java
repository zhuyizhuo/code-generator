package com.github.zhuyizhuo.generator.mybatis.database.service;

import com.github.zhuyizhuo.generator.mybatis.vo.TableInfo;

import java.util.List;

/**
 * 数据库抽象接口 <br>
 * time: 2018/7/30 12:56
 *
 * @author zhuo <br>
 * @version 1.0
 */
public interface DbService {

    /**
     *  查询数据库表信息及列信息
     * @return 数据库表信息 list
     */
    List<TableInfo> getTableColumns();

}
