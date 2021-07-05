package com.github.zhuyizhuo.generator.mybatis.database.service.impl;

import com.github.zhuyizhuo.generator.mybatis.database.entity.ColumnInfo;
import com.github.zhuyizhuo.generator.mybatis.database.entity.DataBaseInfo;
import com.github.zhuyizhuo.generator.mybatis.database.entity.DbTableInfo;
import com.github.zhuyizhuo.generator.mybatis.database.mapper.MysqlDataBaseMapper;
import com.github.zhuyizhuo.generator.mybatis.database.service.abstracted.AbstractDbService;
import com.github.zhuyizhuo.generator.mybatis.vo.TableInfo;
import com.github.zhuyizhuo.generator.utils.LogUtils;
import com.github.zhuyizhuo.generator.utils.SqlSessionUtils;
import com.github.zhuyizhuo.generator.utils.TypeConversion;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

/**
 * mysql 数据库查询表结构实现 <br>
 * time: 2018/7/30 13:10
 *
 * @author zhuo <br>
 * @version 1.0
 */
public class MysqlDbServiceImpl extends AbstractDbService {

    @Override
    public List<TableInfo> getTableColumns() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        MysqlDataBaseMapper mapper = sqlSession.getMapper(MysqlDataBaseMapper.class);
        DataBaseInfo dataBaseInfo = getDataBaseInfo();
        List<DbTableInfo> tableList  = mapper.getTableNameListBySchema(dataBaseInfo);
        LogUtils.info("DataBaseInfo:" + dataBaseInfo +",共查询出" + tableList.size() + "张表.");

        List<TableInfo> tableColumns = getTableColumns(mapper, tableList);
        sqlSession.close();
        return tableColumns;
    }

    private List<TableInfo> getTableColumns(MysqlDataBaseMapper mapper, List<DbTableInfo> tableList) {
        List<TableInfo> lists = new ArrayList<>();
        TableInfo tableInfo = null;
        for (int i = 0; i < tableList.size(); i++) {
            tableInfo = new TableInfo();
            DbTableInfo dbTableInfo = tableList.get(i);
            dbTableInfo.setColumnLists(getColumnInfos(mapper, dbTableInfo));
            //设置值
            setTableInfo(dbTableInfo,tableInfo);
            tableInfo.setTableNameCamelCase(changeTableNameCamelCase(dbTableInfo.getTableName()));
            tableInfo.addPrimaryKeyColumn(getPrimaryKeys(mapper,dbTableInfo));
            lists.add(tableInfo);
        }
        return lists;
    }

    private List<ColumnInfo> getPrimaryKeys(MysqlDataBaseMapper mapper, DbTableInfo dbTableInfo) {
        return mapper.getPrimaryKeys(dbTableInfo);
    }

    private List<ColumnInfo> getColumnInfos(MysqlDataBaseMapper mapper, DbTableInfo dbTableInfo) {
        return mapper.getColumnListByTableName(dbTableInfo);
    }

    @Override
    protected String getJavaDataType(ColumnInfo columnInfo) {
        return TypeConversion.getJavaTypeByDBDataType(columnInfo.getDataType());
    }
}
