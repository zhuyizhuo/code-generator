	<!-- ${methodDescription.QUERY_BY_PRIMARY_KEY.comment}  -->
<#if tableInfo.singlePrimaryKey>
	<select id="${methodDescription.QUERY_BY_PRIMARY_KEY.methodName}" resultMap="${mybatisXmlDefinition.resultMap.id}" parameterType="${mybatisXmlDefinition.columns[0].parameterType}">
<#else>
    <select id="${methodDescription.QUERY_BY_PRIMARY_KEY.methodName}" resultMap="${mybatisXmlDefinition.resultMap.id}" parameterType="${mybatisXmlDefinition.parameterType}">
</#if>
        SELECT
    <#list mybatisXmlDefinition.columns as colm>
        <#if colm??>
            <#if colm_has_next>
            ${colm.columnName},
            <#else>
            ${colm.columnName}
            </#if>
        </#if>
    </#list>
  	    FROM ${tableInfo.tableName}
        WHERE
        <#list tableInfo.primaryKeyColumns as colm>
            <#if colm_index != 0>AND </#if>${colm.columnName} = ${'#{'}${colm.javaColumnName}}
       </#list>
    </select>
