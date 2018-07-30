package com.zhuyizhuo.generator.mybatis;

import com.google.common.base.Splitter;
import com.zhuyizhuo.generator.mybatis.constants.ConfigConstants;
import com.zhuyizhuo.generator.mybatis.database.mapper.MysqlDataBaseMapper;
import com.zhuyizhuo.generator.mybatis.database.pojo.ColumnInfo;
import com.zhuyizhuo.generator.mybatis.database.pojo.DataBaseInfo;
import com.zhuyizhuo.generator.mybatis.database.pojo.DbTableInfo;
import com.zhuyizhuo.generator.utils.PropertiesUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * mybatis代码生成器
 * @author yizhuo
 * @version 1.0
 * @date 2018/7/27 20:55
 */
public class BootStrap {

    /**
     *  相对路径加载配置文件
     */
    public static SqlSession getSqlSession() throws Exception {
        //配置文件
        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        LogFactory.useStdOutLogging();
        return sqlSessionFactory.openSession();
    }

    public static void main(String[] args) throws Exception {
        SqlSession sqlSession = getSqlSession();
        MysqlDataBaseMapper mapper = sqlSession.getMapper(MysqlDataBaseMapper.class);

        List<DbTableInfo> tableList  = mapper.getTableNameListBySchema(getDataBaseInfo());
        System.out.println("共" + tableList.size() + "张表.");

        getTableColumns(mapper, tableList);

        Generator.printAll(tableList);

        sqlSession.close();
    }

    private static void getTableColumns(MysqlDataBaseMapper mapper, List<DbTableInfo> tableList) {
        for (int i = 0; i < tableList.size(); i++) {
            DbTableInfo dbTableInfo = tableList.get(i);
            List<ColumnInfo> columnListByTableName = mapper.getColumnListByTableName(dbTableInfo);
            dbTableInfo.setColumnLists(columnListByTableName);
            System.out.println(dbTableInfo.getTableName() + "表共" + columnListByTableName.size() + "列");
        }
    }

    private static DataBaseInfo getDataBaseInfo() {
        DataBaseInfo tableInfo = new DataBaseInfo();
        tableInfo.setTableSchema(getTableSchema());
        tableInfo.setTableNames(getTables());
        return tableInfo;
    }

    private static String getTableSchema() {
        return PropertiesUtils.getProperties(ConfigConstants.TABLE_SCHEMA);
    }

    private static List<String> getTables() {
        String includeTableName = PropertiesUtils.getProperties(ConfigConstants.INCLUDE_TABLE_NAME);
        return Splitter.on(",").splitToList(includeTableName);
    }

}
