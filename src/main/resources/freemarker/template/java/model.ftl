package ${javaClassDefinition.MODEL.fullPackage};

<#list tableInfo.importPackages as import>
	<#if import??>
import ${import};
	</#if>
</#list>

/**
 * database	: ${tableInfo.tableSchema} <br/>
 * table	: ${tableInfo.tableName} <br/>
 * description : ${tableInfo.tableComment} <br/>
 * time:    ${classCommentInfo.createTime} <br/>
 * @author  ${classCommentInfo.author} <br/>
 * @since   ${classCommentInfo.sinceVersion} <br/>
 * @version ${classCommentInfo.version} <br/>
 */
public class ${javaClassDefinition.MODEL.className} {

<#list tableInfo.columnLists as colm>
	<#if colm??>
	/** ${colm.columnComment} */
	private ${colm.javaDataType} ${colm.javaColumnName};
	</#if>
</#list>

<#list tableInfo.columnLists as colm>
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