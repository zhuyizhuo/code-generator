package com.github.zhuyizhuo.generator.mybatis.database.service.impl;

import com.github.zhuyizhuo.generator.constants.ConfigConstants;
import com.github.zhuyizhuo.generator.mybatis.database.entity.ColumnInfo;
import com.github.zhuyizhuo.generator.mybatis.database.entity.DataBaseInfo;
import com.github.zhuyizhuo.generator.mybatis.database.entity.DbTableInfo;
import com.github.zhuyizhuo.generator.mybatis.database.mapper.OracleDataBaseMapper;
import com.github.zhuyizhuo.generator.mybatis.database.service.abstracted.AbstractDbService;
import com.github.zhuyizhuo.generator.mybatis.vo.TableInfo;
import com.github.zhuyizhuo.generator.utils.GeneratorStringUtils;
import com.github.zhuyizhuo.generator.utils.LogUtils;
import com.github.zhuyizhuo.generator.utils.PropertiesUtils;
import com.github.zhuyizhuo.generator.utils.SqlSessionUtils;
import com.github.zhuyizhuo.generator.utils.TypeConversion;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * oracle 数据库查询表结构实现 <br>
 * time: 2018/8/6 12:58
 *
 * @author zhuo <br>
 * @version 1.0
 */
public class OracleDbServiceImpl extends AbstractDbService {

    @Override
    protected String getTableSchema() {
        String tableSchema = super.getTableSchema();
        if (GeneratorStringUtils.isNotBlank(tableSchema)){
            return tableSchema.toUpperCase();
        }
        return tableSchema;
    }

    @Override
    protected List<String> getTables() {
        String includeTableName = PropertiesUtils.getProperties(ConfigConstants.GENERATE_TABLES_NAME);
        if (GeneratorStringUtils.isNotBlank(includeTableName)){
            return Arrays.asList(includeTableName.toUpperCase().split(","));
        }
        return null;
    }

    @Override
    public List<TableInfo> getTableColumns() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        OracleDataBaseMapper mapper = sqlSession.getMapper(OracleDataBaseMapper.class);
        DataBaseInfo dataBaseInfo = getDataBaseInfo();
        List<DbTableInfo> tableList  = mapper.getTableNameListBySchema(dataBaseInfo);
        LogUtils.info("共查询出" + tableList.size() + "张表.");
        List<TableInfo> tableInfos = getTableInfos(mapper, tableList);
        sqlSession.close();
        return tableInfos;
    }

    private List<TableInfo> getTableInfos(OracleDataBaseMapper mapper, List<DbTableInfo> tableList) {
        List<TableInfo> tableInfos = new ArrayList<>();
        TableInfo tableInfo = null;
        for (int i = 0; i < tableList.size(); i++) {
            DbTableInfo dbTableInfo = tableList.get(i);
            String tableName = dbTableInfo.getTableName();
            DbTableInfo allColumnsByTable = mapper.getAllColumnsByTable(dbTableInfo.getTableSchema(), tableName);
            tableInfo = new TableInfo();
            setTableInfo(allColumnsByTable,tableInfo);
            tableInfo.setTableNameCamelCase(changeTableNameCamelCase(tableName));
            tableInfo.addPrimaryKeyColumn(getPrimaryKeys(mapper,dbTableInfo));
            tableInfos.add(tableInfo);
        }
        return tableInfos;
    }

    private List<ColumnInfo> getPrimaryKeys(OracleDataBaseMapper mapper, DbTableInfo dbTableInfo) {
        return mapper.getPrimaryKeys(dbTableInfo);
    }

    @Override
    protected String getJavaDataType(ColumnInfo columnInfo) {
        return TypeConversion.getJavaTypeByDBDataType(getDataType(columnInfo.getDataType()));
    }
}
