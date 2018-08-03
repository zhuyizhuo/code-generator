package test.generator.dao;

import java.util.List;

import test.generator.pojo.SimpleMybatisUserPOJO;

/**
 * description : SIMPLE_MYBATIS_USER table dao layer interface <br/>
 * @date 2018-08-03 21:32:07 <br/>
 * @author yizhuo <br/>
 * @version 1.0 <br/>
 * @since 1.0 <br/>
 */
public interface SimpleMybatisUserDao {

	void insertSimpleMybatisUser(SimpleMybatisUserPOJO simpleMybatisUserPOJO);

    int updateSimpleMybatisUser(SimpleMybatisUserPOJO simpleMybatisUserPOJO);
}