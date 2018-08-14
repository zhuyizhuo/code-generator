    <!-- 根据主键更新数据 -->
	<update id="${methodInfo.updateByPrimaryKeyMethodName}" parameterType="${parameterType}">
		UPDATE ${tableInfo.tableName}
		<trim prefix="set" suffixOverrides=",">
    <#list tableInfo.columnLists as colm>
        <#if colm??>
            <#if colm_index != 0>
			<if test="null != ${colm.javaColumnName}">${colm.columnName} = ${'#{'}${colm.javaColumnName}},</if>
		    </#if>
		</#if>
	</#list>
		</trim>
		WHERE
	<#list tableInfo.primaryKeyColumns as colm>
		 <#if colm_index != 0>AND </#if>${colm.columnName} = ${'#{'}${colm.javaColumnName}}
	</#list>
	</update>
