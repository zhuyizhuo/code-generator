package ${javaBeanFullPackage};

<#list importList as import>
import ${import};
</#list>
<#if usePageEntity>import ${pageEntityPackage}.${pageEntityName};</#if>

/**
 * database	: ${tableInfo.tableSchema} <br/>
 *    table	: ${tableInfo.dbTableName} <br/>
 * description : ${tableInfo.tableComment}POJO <br/>
 */
public class ${tableInfo.tableName} <#if usePageEntity>extends ${pageEntityName}</#if>{

<#list tableInfo.javaColmBeans as colm>
	<#if colm??>
	/** ${colm.comment} */
	private ${colm.typeName} ${colm.javaName};
	</#if>
</#list>

<#list tableInfo.javaColmBeans as colm>
	<#if colm??>
	public ${colm.typeName} get${colm.javaName?cap_first}() {
		return ${colm.javaName};
	}

	public void set${colm.javaName?cap_first}(${colm.typeName} ${colm.javaName}) {
		this.${colm.javaName} = ${colm.javaName};
	}
	
	</#if>
</#list>
}