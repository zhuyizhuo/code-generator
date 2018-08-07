package com.github.zhuyizhuo.generator.mybatis.utils;

import com.github.zhuyizhuo.generator.utils.PropertiesUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * class: SqlSessionUtils <br>
 * description: 获取SqlSession <br>
 * time: 2018/7/30 12:34
 *
 * @author yizhuo <br>
 * @version 1.0
 */
public class SqlSessionUtils {

    /**
     *  相对路径加载配置文件
     */
    public static SqlSession getSqlSession() throws RuntimeException {
        try {
            //配置文件
            String resource = "mybatis/mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream,PropertiesUtils.proInfo);
//            LogFactory.useStdOutLogging();
            return sqlSessionFactory.openSession();
        } catch (Exception e){
            throw new RuntimeException("SqlSessionUtils.getSqlSession Exception",e);
        }
    }

}
