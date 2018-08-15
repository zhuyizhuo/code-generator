package com.github.zhuyizhuo.generator.mybatis.service.impl;

import com.github.zhuyizhuo.generator.mybatis.service.abst.AbstractDbService;
import com.github.zhuyizhuo.generator.utils.TypeConversion;
import com.google.common.collect.Lists;
import com.github.zhuyizhuo.generator.mybatis.database.mapper.MysqlDataBaseMapper;
import com.github.zhuyizhuo.generator.mybatis.database.pojo.ColumnInfo;
import com.github.zhuyizhuo.generator.mybatis.database.pojo.DataBaseInfo;
import com.github.zhuyizhuo.generator.mybatis.database.pojo.DbTableInfo;
import com.github.zhuyizhuo.generator.mybatis.utils.SqlSessionUtils;
import com.github.zhuyizhuo.generator.mybatis.vo.TableInfoFtl;
import com.github.zhuyizhuo.generator.utils.LogUtils;
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
    public List<TableInfoFtl> getTableColumns() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        MysqlDataBaseMapper mapper = sqlSession.getMapper(MysqlDataBaseMapper.class);
        DataBaseInfo dataBaseInfo = getDataBaseInfo();
        List<DbTableInfo> tableList  = mapper.getTableNameListBySchema(dataBaseInfo);
        LogUtils.printInfo("DataBaseInfo:" + dataBaseInfo +",共查询出" + tableList.size() + "张表.");

        List<TableInfoFtl> tableColumns = getTableColumns(mapper, tableList);
        sqlSession.close();
        return tableColumns;
    }

    private List<TableInfoFtl> getTableColumns(MysqlDataBaseMapper mapper, List<DbTableInfo> tableList) {
        List<TableInfoFtl> lists = Lists.newArrayList();
        TableInfoFtl ftlTableInfo = null;
        for (int i = 0; i < tableList.size(); i++) {
            ftlTableInfo = new TableInfoFtl();
            DbTableInfo dbTableInfo = tableList.get(i);
            dbTableInfo.setColumnLists(getColumnInfos(mapper, dbTableInfo));
            //设置值
            setTableInfoFtl(dbTableInfo,ftlTableInfo);
            ftlTableInfo.setJavaTableName(getJavaTableName(dbTableInfo.getTableName()));
            ftlTableInfo.addPrimaryKeyColumn(getPrimaryKeys(mapper,dbTableInfo));
            lists.add(ftlTableInfo);
            LogUtils.printInfo(dbTableInfo.getTableName() + "表共" + dbTableInfo.getColumnLists().size() + "列");
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
        return TypeConversion.getTypeByMap(TypeConversion.mySqlDbType2JavaMap,columnInfo.getDataType());
    }
}
