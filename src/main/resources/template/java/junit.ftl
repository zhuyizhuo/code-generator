package ${junitPackage};

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ${javaBeanFullPackage}.${tableInfo.tableName};
import ${serviceFullPackage}.${tableInfo.tableName}Service;

import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:spring-mvc.xml","classpath:log4j.xml" })
public class Test${tableInfo.tableName}Service {

	@Autowired
	private ${tableInfo.tableName}Service ${tableInfo.tableName?uncap_first}ServiceImpl;

	@Test
	public void testInsert() {
		${tableInfo.tableName?uncap_first}ServiceImpl.insert${tableInfo.tableName}(getPara());
		System.out.println("testInsert执行完毕");
	}
	
	@Test
	public void testUpdate() {
		int i = ${tableInfo.tableName?uncap_first}ServiceImpl.update${tableInfo.tableName}(getPara());
		printJsonInfo("更新条数:",i);
	}
	
	@Test
	public void testSelect() {
		List<${tableInfo.tableName}> list = ${tableInfo.tableName?uncap_first}ServiceImpl.query${tableInfo.tableName}ListByWhere(getPara());
		printJsonInfo("testSelect返回信息:",list);
	}
	
	@Test
	public void testGetOne() {
		${tableInfo.tableName} one = ${tableInfo.tableName?uncap_first}ServiceImpl.getOne(getPara());
		printJsonInfo("testGetOne返回信息:",one);
	}
	
	@Test
	public void testCount() {
		int count = ${tableInfo.tableName?uncap_first}ServiceImpl.count${tableInfo.tableName}TotalByWhere(getPara());
		printJsonInfo("总条数:",count);
	}
	
	@Test
	public void testDelete() {
		int del = ${tableInfo.tableName?uncap_first}ServiceImpl.delete${tableInfo.tableName}(getPara().get${tableInfo.javaColmBeans[0].javaName?cap_first}());
		printJsonInfo("删除条数:",del);
	}
	
	/**
	 * 封装参数
	 * @return
	 */
	private ${tableInfo.tableName} getPara() {
		${tableInfo.tableName} bean = new ${tableInfo.tableName}();		
		<#list tableInfo.javaColmBeans as colm>
		<#if colm.typeName = 'String'>
		bean.set${colm.javaName?cap_first}("${colm.javaName?cap_first}");
		<#elseif colm.typeName = 'BigDecimal'>
		bean.set${colm.javaName?cap_first}(BigDecimal.ZERO);
		<#elseif colm.typeName = 'Date'>
		bean.set${colm.javaName?cap_first}(new Date());
		<#else>
		bean.set${colm.javaName?cap_first}(null);
		</#if>
		</#list>
		return bean;
	}
	
	/**
	 * 输出json数据
	 * @param msg
	 * @param obj
	 */
	private void printJsonInfo(String msg,Object obj) {
		System.out.println(msg + JSON.toJSONString(obj));
	}
	
}
