package com.zhuyizhuo.generator.mybatis.database;

import com.zhuyizhuo.generator.mybatis.database.mapper.MysqlDataBaseMapper;
import com.zhuyizhuo.generator.mybatis.database.pojo.ColumnInfo;
import com.zhuyizhuo.generator.mybatis.database.pojo.DbTableInfo;
import com.zhuyizhuo.generator.mybatis.database.pojo.MysqlDbInfo;
import com.zhuyizhuo.generator.utils.Freemarker;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
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

        DbTableInfo tableInfo = new DbTableInfo();
        tableInfo.setTableSchema("yizhuo");
        List<DbTableInfo> l  = mapper.getTableNameListBySchema(tableInfo);
        System.out.println("共" + l.size() + "张表.");

        for (int i = 0; i < l.size(); i++) {
            DbTableInfo tableInfo1 = l.get(i);
            List<ColumnInfo> columnListByTableName = mapper.getColumnListByTableName(tableInfo1);
            System.out.println(tableInfo1.getTableName() + "表共" + columnListByTableName.size() + "列");
            for (int j = 0; j < columnListByTableName.size(); j++) {
                ColumnInfo columnInfo = columnListByTableName.get(j);
                System.out.println(columnInfo);
            }
            tableInfo1.setColumnLists(columnListByTableName);
        }
        /*Map<String, Object> root = new HashMap<>();
        List l = new ArrayList();
        Map n = new HashMap();
        n.put("dbColmName","1212");
        l.add(n);
        l.add(n);
        l.add(n);
        root.put("javaColmBeans",l);
        String ftlPath = "E:\\github\\code-generator\\src\\main\\resources\\freemarker\\template\\mysql";
        Freemarker.print("insert.ftl",root,ftlPath);*/
    }

}
