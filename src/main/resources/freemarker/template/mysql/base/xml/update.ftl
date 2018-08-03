    <!-- 根据传入条件更新数据 -->
	<update id="${methodInfo.updateMethodName}" parameterType="${stratificationInfo.pojoFullPackage}.${stratificationInfo.pojoName}">
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
		WHERE ${tableInfo.columnLists[0].columnName} = ${'#{'}${tableInfo.columnLists[0].javaColumnName}}
	</update>
