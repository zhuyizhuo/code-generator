package com.zhuyizhuo.generator.mybatis.service.impl;

import com.google.common.base.Splitter;
import com.zhuyizhuo.generator.mybatis.constants.ConfigConstants;
import com.zhuyizhuo.generator.mybatis.database.mapper.MysqlDataBaseMapper;
import com.zhuyizhuo.generator.mybatis.database.pojo.ColumnInfo;
import com.zhuyizhuo.generator.mybatis.database.pojo.DataBaseInfo;
import com.zhuyizhuo.generator.mybatis.database.pojo.DbTableInfo;
import com.zhuyizhuo.generator.mybatis.service.DbService;
import com.zhuyizhuo.generator.mybatis.service.abst.AbstractDbService;
import com.zhuyizhuo.generator.mybatis.utils.SqlSessionUtils;
import com.zhuyizhuo.generator.utils.PropertiesUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * class: MysqlDbServiceImpl <br>
 * description: mysql数据库查询表结构实现 <br>
 * time: 2018/7/30 13:10
 *
 * @author yizhuo <br>
 * @version 1.0
 */
public class MysqlDbServiceImpl extends AbstractDbService {
    @Override
    public List<DbTableInfo> getTableColumns() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        MysqlDataBaseMapper mapper = sqlSession.getMapper(MysqlDataBaseMapper.class);

        List<DbTableInfo> tableList  = mapper.getTableNameListBySchema(getDataBaseInfo());
        System.out.println("共" + tableList.size() + "张表.");

        getTableColumns(mapper, tableList);
        sqlSession.close();
        return tableList;
    }

    private static void getTableColumns(MysqlDataBaseMapper mapper, List<DbTableInfo> tableList) {
        for (int i = 0; i < tableList.size(); i++) {
            DbTableInfo dbTableInfo = tableList.get(i);
            List<ColumnInfo> columnListByTableName = mapper.getColumnListByTableName(dbTableInfo);
            dbTableInfo.setColumnLists(columnListByTableName);
            System.out.println(dbTableInfo.getTableName() + "表共" + columnListByTableName.size() + "列");
        }
    }

}
