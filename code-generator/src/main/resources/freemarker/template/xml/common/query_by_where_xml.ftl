	<!-- ${methodDescription.QUERY_BY_WHERE.comment}  -->
	<select id="${methodDescription.QUERY_BY_WHERE.methodName}" resultMap="${mybatisXmlDefinition.resultMap.id}" parameterType="${mybatisXmlDefinition.parameterType}">
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
        <include refid="Where_Clause" />
    </select>
