package ${javaClassDefinition.VO.fullPackage};

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
<#list tableInfo.importPackages as import>
	<#if import??>
import ${import};
	</#if>
</#list>

/**
 * ${tableInfo.tableComment} <br/>
 *
 * @author ${classCommentInfo.author} <br/>
 * @date    ${classCommentInfo.createTime} <br/>
 * @since  ${classCommentInfo.sinceVersion} <br/>
 */
@Data
public class ${javaClassDefinition.VO.className} implements Serializable {
    private static final long serialVersionUID=1L;

<#list tableInfo.columnLists as colm>
	<#if colm??>
	/** ${colm.columnComment} */
    @ApiModelProperty(value = "${colm.columnComment}")
	private ${colm.javaDataType} ${colm.javaColumnName};
	</#if>
</#list>

}