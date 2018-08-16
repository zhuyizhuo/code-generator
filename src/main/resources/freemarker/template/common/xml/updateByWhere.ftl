	<!-- ${methodInfo.updateMethodDescription} -->
	<update id="${methodInfo.updateMethodName}" parameterType="${parameterType}">
		UPDATE
		<include refid="Table_Name" />
		<set>
    <#list tableInfo.columnLists as colm>
        <#if colm??>
            <#if colm_index != 0>
			<if test="${colm.javaColumnName} != null">${colm.columnName} = ${'#{'}${colm.javaColumnName}},</if>
		    </#if>
		</#if>
	</#list>
		</set>
		<include refid="Where_Clause" />
	</update>
