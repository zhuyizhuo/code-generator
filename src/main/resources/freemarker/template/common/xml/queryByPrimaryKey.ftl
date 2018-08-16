	<!-- ${methodInfo.queryByPrimaryKeyDescription}  -->
<#if tableInfo.singlePrimaryKey>
	<update id="${methodInfo.queryByPrimaryKeyMethodName}" parameterType="${tableInfo.primaryKeyColumns[0].parameterType}">
<#else>
    <update id="${methodInfo.queryByPrimaryKeyMethodName}" parameterType="${parameterType}">
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
