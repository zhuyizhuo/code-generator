package ${daoFullPackage};

import java.util.List;

import ${javaBeanFullPackage}.${tableInfo.tableName};

/**
 * description : ${tableInfo.dbTableName} table dao layer interface <br/>
 */
public interface ${daoName} {

	/**
	 * 方法: insert${tableInfo.tableName} <br>
	 * 描述: 插入数据 <br>
	 * 作者: ${author} <br>
	 * 时间: ${date?string("yyyy-MM-dd HH:mm:ss")}<br>
	 * @param ${tableInfo.tableName?uncap_first} 存放参数的实体  <br>
<#list tableInfo.javaColmBeans as colm>
	 <#if colm??>
	 * @param ${colm.javaName} ${colm.comment} <br>
	</#if>
</#list>
	 */
	void insert${tableInfo.tableName}(${tableInfo.tableName} ${tableInfo.tableName?uncap_first});

	/**
	 * 方法: update${tableInfo.tableName} <br>
	 * 描述: 更新数据 <br>
	 * 作者: ${author} <br>
	 * 时间: ${date?string("yyyy-MM-dd HH:mm:ss")}<br>
	 * @param ${tableInfo.tableName?uncap_first} 存放参数的实体  <br>
<#list tableInfo.javaColmBeans as colm>
	 <#if colm??>
	 * @param ${colm.javaName} ${colm.comment} <br>
	</#if>
</#list>
	 */
	int update${tableInfo.tableName}(${tableInfo.tableName} ${tableInfo.tableName?uncap_first});

	/**
	 * 方法: delete${tableInfo.tableName} <br>
	 * 描述: 删除数据 <br>
	 * 作者: ${author} <br>
	 * 时间: ${date?string("yyyy-MM-dd HH:mm:ss")}<br>
	 * @param ${tableInfo.javaColmBeans[0].javaName} ${tableInfo.javaColmBeans[0].comment} <br>
	 */
	int delete${tableInfo.tableName}(${tableInfo.javaColmBeans[0].typeName} ${tableInfo.javaColmBeans[0].javaName});

	/**
	 * 方法: query${tableInfo.tableName}ListByWhere <br>
	 * 描述: 根据传入参数查询所有数据 <br>
	 * 作者: ${author} <br>
	 * 时间: ${date?string("yyyy-MM-dd HH:mm:ss")}<br>
	 * @param ${tableInfo.tableName?uncap_first} 存放参数的实体  <br>
<#list tableInfo.javaColmBeans as colm>
	 <#if colm??>
	 * @param ${colm.javaName} ${colm.comment} <br>
	</#if>
</#list>
	 */
	List<${tableInfo.tableName}> query${tableInfo.tableName}ListByWhere(${tableInfo.tableName} ${tableInfo.tableName?uncap_first});

	/**
	 * 方法: getOne <br>
	 * 描述: 根据传入条件获取单条记录 <br>
	 * 作者: ${author} <br>
	 * 时间: ${date?string("yyyy-MM-dd HH:mm:ss")}<br>
	 * @param ${tableInfo.tableName?uncap_first} 存放参数的实体  <br>
<#list tableInfo.javaColmBeans as colm>
	 <#if colm??>
	 * @param ${colm.javaName} ${colm.comment} <br>
	</#if>
</#list>
	 */
	${tableInfo.tableName} getOne(${tableInfo.tableName} ${tableInfo.tableName?uncap_first});

	/**
	 * 方法: count${tableInfo.tableName}TotalByWhere <br>
	 * 描述: 根据传入条件获取记录总数 <br>
	 * 作者: ${author} <br>
	 * 时间: ${date?string("yyyy-MM-dd HH:mm:ss")}<br>
	 * @param ${tableInfo.tableName?uncap_first} 存放参数的实体  <br>
<#list tableInfo.javaColmBeans as colm>
	 <#if colm??>
	 * @param ${colm.javaName} ${colm.comment} <br>
	</#if>
</#list>
	 */
	int count${tableInfo.tableName}TotalByWhere(${tableInfo.tableName} ${tableInfo.tableName?uncap_first});
	
}