package ${stratificationInfo.basePackage};

import ${stratificationInfo.daoFullPackage}.${stratificationInfo.daoName};
import ${stratificationInfo.pojoFullPackage}.${stratificationInfo.pojoName};
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

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
        ${stratificationInfo.daoName} mapper = sqlSession.getMapper(${stratificationInfo.daoName}.class);

        ${stratificationInfo.pojoName}  aaa = new ${stratificationInfo.pojoName}();
        aaa.setName("testInsertAuto");
        mapper.insertSimpleMybatisUser(aaa);

        sqlSession.commit();
        sqlSession.close();
    }
}