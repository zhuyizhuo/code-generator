package test.generator.pojo;


/**
 * database	: YIZHUO <br/>
 * table	: SIMPLE_MYBATIS_USER <br/>
 * description : 用户表POJO <br/>
 * @date 2018-08-03 21:32:07 <br/>
 * @author yizhuo <br/>
 * @version 1.0 <br/>
 * @since 1.0 <br/>
 */
public class SimpleMybatisUserPOJO {

	/** 主键 */
	private Integer id;
	/** 姓名
1212 */
	private String name;
	/** 年龄 */
	private String age;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
}