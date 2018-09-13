	<!-- ${methodCommentInfo.queryByPrimaryKeyDescription}  -->
<#if tableInfo.singlePrimaryKey>
	<select id="${methodInfo.queryByPrimaryKeyMethodName}" resultMap="${mybatisXmlDefinition.resultMap.id}" parameterType="${tableInfo.primaryKeyColumns[0].parameterType}">
<#else>
    <select id="${methodInfo.queryByPrimaryKeyMethodName}" resultMap="${mybatisXmlDefinition.resultMap.id}" parameterType="${mybatisXmlDefinition.parameterType}">
</#if>
        SELECT
        <include refid="Base_Column_List" />
  	     FROM
        <include refid="Table_Name" />
        WHERE
        <#list tableInfo.primaryKeyColumns as colm>
            <#if colm_index != 0>AND </#if>${colm.columnName} = ${'#{'}${colm.javaColumnName}}
       </#list>
    </select>
