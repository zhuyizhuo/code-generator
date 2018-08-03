package com.zhuyizhuo.generator.mybatis.service.impl;

import com.google.common.collect.Lists;
import com.zhuyizhuo.generator.mybatis.database.mapper.MysqlDataBaseMapper;
import com.zhuyizhuo.generator.mybatis.database.pojo.ColumnInfo;
import com.zhuyizhuo.generator.mybatis.database.pojo.DbTableInfo;
import com.zhuyizhuo.generator.mybatis.dto.JavaColumnInfo;
import com.zhuyizhuo.generator.mybatis.service.abst.AbstractDbService;
import com.zhuyizhuo.generator.mybatis.utils.SqlSessionUtils;
import com.zhuyizhuo.generator.mybatis.vo.TableInfoFtl;
import com.zhuyizhuo.generator.utils.GeneratorStringUtils;
import com.zhuyizhuo.generator.utils.TypeConversion;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.BeanUtils;

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

        List<DbTableInfo> tableList  = mapper.getTableNameListBySchema(getDataBaseInfo());
        System.out.println("共" + tableList.size() + "张表.");

        List<TableInfoFtl> tableColumns = getTableColumns(mapper, tableList);

        sqlSession.close();
        return tableColumns;
    }

    private static List<TableInfoFtl> getTableColumns(MysqlDataBaseMapper mapper, List<DbTableInfo> tableList) {
        List<TableInfoFtl> lists = Lists.newArrayList();
        TableInfoFtl ftlTableInfo = null;
        for (int i = 0; i < tableList.size(); i++) {
            ftlTableInfo = new TableInfoFtl();
            DbTableInfo dbTableInfo = tableList.get(i);
            dbTableInfo.setColumnLists(getColumnInfos(mapper, dbTableInfo));
            //设置值
            setTableInfoFtl(dbTableInfo,ftlTableInfo);
            ftlTableInfo.setJavaTableName(getJavaTableName(dbTableInfo.getTableName()));
            lists.add(ftlTableInfo);
            System.out.println(dbTableInfo.getTableName() + "表共" + getColumnInfos(mapper, dbTableInfo).size() + "列");
        }
        return lists;
    }

    private static void setTableInfoFtl(DbTableInfo dbTableInfo, TableInfoFtl ftlTableInfo) {
        BeanUtils.copyProperties(dbTableInfo,ftlTableInfo);
        List<ColumnInfo> columnLists = dbTableInfo.getColumnLists();
        JavaColumnInfo javaColumnInfo;
        for (int i = 0; i < columnLists.size(); i++) {
            ColumnInfo columnInfo = columnLists.get(i);
            javaColumnInfo = new JavaColumnInfo();
            BeanUtils.copyProperties(columnInfo,javaColumnInfo);
            javaColumnInfo.setColumnComment(replaceEnter(javaColumnInfo.getColumnComment()));
            javaColumnInfo.setJavaColumnName(GeneratorStringUtils.changeColmName2Java(columnInfo.getColumnName(),"_"));
            javaColumnInfo.setJavaDataType(TypeConversion.mySqlDbType2Java(columnInfo.getDataType()));
            javaColumnInfo.setJavaDataTypeFullPath(TypeConversion.javaDataTypeFullPathMap.get(javaColumnInfo.getJavaDataType()));
            ftlTableInfo.addJavaColumnInfo(javaColumnInfo);
            ftlTableInfo.addImportPackages(javaColumnInfo.getJavaDataTypeFullPath());
        }
    }

    /**
     * 备注去除回车换行
     * @param columnComment 字段备注
     * @return
     */
    private static String replaceEnter(String columnComment) {
        return columnComment.replaceAll("\r"," ").replaceAll("\n"," ").replaceAll("\r\n"," ");
    }

    private static String getJavaTableName(String tableName) {
        return GeneratorStringUtils.changeTableName2Java1(tableName,"_");
    }

    private static List<ColumnInfo> getColumnInfos(MysqlDataBaseMapper mapper, DbTableInfo dbTableInfo) {
        return mapper.getColumnListByTableName(dbTableInfo);
    }

}
