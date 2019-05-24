<#assign parameterType = "${tableInfo.singlePrimaryKey?then(mybatisXmlDefinition.columns[0].parameterType,mybatisXmlDefinition.parameterType)}">
    <!-- ${methodCommentInfo.deleteByPrimaryKeyMethodDescription} -->
	<delete id="${methodInfo.methodDescription.DELETE_BY_PRIMARY_KEY.methodName}" parameterType="${parameterType}">
        DELETE FROM
        <include refid="Table_Name" />
        WHERE <#list tableInfo.primaryKeyColumns as colm><#if colm_index != 0>AND </#if>${colm.columnName} = ${'#{'}${colm.javaColumnName}} </#list>
	</delete>
