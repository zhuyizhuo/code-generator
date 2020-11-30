package ${javaClassDefinition.MODEL.fullPackage};

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
<#list tableInfo.importPackages as import>
	<#if import??>
import ${import};
	</#if>
</#list>

/**
 * ${tableInfo.tableComment!'${tableInfo.tableName}'} <br/>
 *
 * @author  ${classCommentInfo.author} <br/>
 * @date    ${classCommentInfo.createTime} <br/>
 * @since   ${classCommentInfo.sinceVersion} <br/>
 */
@TableName("${tableInfo.tableName}")
public class ${javaClassDefinition.MODEL.className} implements Serializable {
    private static final long serialVersionUID=1L;
<#list tableInfo.primaryKeyColumns as colm>
    <#if colm??>
	/** ${colm.columnComment} */
	@TableId
	private ${colm.javaDataType} ${colm.javaColumnName};
	</#if>
</#list>
<#list tableInfo.columnLists as colm>
	<#if colm??>
    <#if (colm_index >= tableInfo.primaryKeyColumns?size)>
	/** ${colm.columnComment} */
	private ${colm.javaDataType} ${colm.javaColumnName};
	</#if>
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