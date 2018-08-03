package ${stratificationInfo.basePackage};

import ${stratificationInfo.daoFullPackage}.${stratificationInfo.daoName};
import ${stratificationInfo.pojoFullPackage}.${stratificationInfo.pojoName};
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

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

        List<${stratificationInfo.pojoName}> lists =
            mapper.${methodInfo.queryMethodName}(null);
        System.out.println(lists.size());

        ${stratificationInfo.pojoName}  aaa = new ${stratificationInfo.pojoName}();
        aaa.setName("testInsertAuto");
        mapper.insertSimpleMybatisUser(aaa);

        aaa.setId(12);
        aaa.setName(Math.random()+"");
        int i = mapper.updateSimpleMybatisUser(aaa);
        System.out.println(i);
        sqlSession.commit();
        sqlSession.close();
    }
}