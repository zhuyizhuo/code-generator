package ${package};

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 类: BaseDao <br>
 * 描述: Dao层基类 所有dao需继承此类 <br>
 * 作者: 朱一卓 <br>
 * 使用该dao需配置xml文件 sqlSessionTemplate 将sqlSessionFactory注入
 */
public class BaseDao extends SqlSessionDaoSupport {
	
	@Autowired
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	/**
	 * 
	 * Description: 新增一条记录
	 * 
	 * @Version1.0 
	 * @param statementName SQL语句的ID
	 * @param object 参数
	 */
	public void insert(String statementName, Object object) {
		getSqlSession().insert(statementName, object);
	}

	/**
	 * 
	 * Description: 更新一条记录
	 * 
	 * @Version1.0 2014-1-21 
	 * @param statementName SQL语句的ID
	 * @param object  参数
	 */
	public int update(String statementName, Object object) {
		int update = getSqlSession().update(statementName, object);
		return update;
	}

	/**
	 * 
	 * Description: 删除一条记录
	 * 
	 * @Version1.0 
	 * @param statementName
	 *            SQL语句的ID
	 * @param object
	 *            参数
	 */
	public int delete(String statementName, Object object) {
		int delete = getSqlSession().delete(statementName, object);
		return delete;
	}

	/**
	 * 
	 * Description: 获取一条记录(一般根据逐渐获取)
	 * 
	 * @Version1.0 
	 * @param statementName SQL语句ID
	 * @param object   参数
	 * @return 符合条件的一条记录
	 */
	public <T> T getOne(String statementName, Object paramObj) {
		return getSqlSession().selectOne(statementName,paramObj);
	}

	/**
	 * 
	 * Description: 查询所有
	 * 
	 * @Version1.0
	 * @param statementName SQL语句的ID
	 * @return
	 */
	public <T> List<T> queryForList(String statementName) {
		return getSqlSession().selectList(statementName);
	}

	/**
	 * 
	 * Description: 根据条件查询
	 * 
	 * @Version1.0 
	 * @param statementName SQL语句的ID
	 * @param paramObj 查询条件
	 * @return
	 */
	public <T> List<T> queryForList(String statementName, Object paramObj) {
		return getSqlSession().selectList(statementName, paramObj);
	}

}