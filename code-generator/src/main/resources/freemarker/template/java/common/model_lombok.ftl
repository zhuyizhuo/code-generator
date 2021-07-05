package ${javaClassDefinition.MODEL.fullPackage};

import lombok.Data;

<#list tableInfo.importPackages as import>
	<#if import??>
import ${import};
	</#if>
</#list>

/**
 * ${tableInfo.tableComment!} <br/>
 *
 * @author  ${classCommentInfo.author} <br/>
 * @date    ${classCommentInfo.createTime} <br/>
 * @since   ${classCommentInfo.sinceVersion} <br/>
 */
@Data
public class ${javaClassDefinition.MODEL.className} {

<#list tableInfo.columnLists as colm>
	<#if colm??>
	/** ${colm.columnComment} */
	private ${colm.javaDataType} ${colm.javaColumnName};
	</#if>
</#list>

}