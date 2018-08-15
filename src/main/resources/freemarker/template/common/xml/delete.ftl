    <!-- ${methodInfo.deleteByPrimaryKeyMethodDescription} -->
<#if tableInfo.singlePrimaryKey>
	<delete id="${methodInfo.deleteByPrimaryKeyMethodName}" parameterType="${tableInfo.primaryKeyColumns[0].parameterType}">
<#else>
    <delete id="${methodInfo.deleteByPrimaryKeyMethodName}" parameterType="${parameterType}">
</#if>
        DELETE FROM
        <include refid="Table_Name" />
        WHERE <#list tableInfo.primaryKeyColumns as colm><#if colm_index != 0>AND </#if>${colm.columnName} = ${'#{'}${colm.javaColumnName}} </#list>
	</delete>

    <!-- ${methodInfo.deleteMethodDescription} -->
	<delete id="${methodInfo.deleteMethodName}" parameterType="${parameterType}">
        DELETE FROM
        <include refid="Table_Name" />
        <include refid="Where_Clause" />
	</delete>
