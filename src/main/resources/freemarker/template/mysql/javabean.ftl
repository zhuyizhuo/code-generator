package ${stratificationInfo.pojoFullPackage};

<#list javaTableInfo.importPackages as import>
	<#if import??>
import ${import};
	</#if>
</#list>

/**
 * database	: ${dbTableInfo.tableSchema} <br/>
 * table	: ${dbTableInfo.tableName} <br/>
 * description : ${dbTableInfo.tableComment}POJO <br/>
<#include "base/java/comment.ftl"/>
 */
public class ${stratificationInfo.pojoName} {

<#list dbTableInfo.javaColumnLists as colm>
	<#if colm??>
	/** ${dbTableInfo.columnLists[colm_index].columnComment} */
	private ${colm.javaDataType} ${colm.javaColumnName};
	</#if>
</#list>

<#list dbTableInfo.javaColumnLists as colm>
	<#if colm??>
	public ${colm.javaDataType} get${colm.javaColumnName?cap_first}() {
		return ${colm.javaColumnName};
	}

	public void set${colm.javaColumnName?cap_first}(${colm.javaDataType} ${colm.javaColumnName}) {
		this.${colm.javaColumnName} = ${colm.javaColumnName};
	}
	
	</#if>
</#list>
}