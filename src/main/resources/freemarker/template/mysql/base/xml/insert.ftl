    <!-- 新增 -->
	<insert id="${methodInfo.insertMethodName}" parameterType="${stratificationInfo.pojoFullPackage}.${stratificationInfo.pojoName}">
		<![CDATA[
			INSERT INTO ${dbTableInfo.tableName}(
        <#list dbTableInfo.columnLists as colm>
            <#if colm??>
                ${colm.columnName}<#if colm_has_next>,<#else></#if>
            </#if>
        </#list>
			) VALUES (
        <#list dbTableInfo.javaColumnLists as colm>
            <#if colm??>
                ${'#{'}${colm.javaColumnName}}<#if colm_has_next>,<#else></#if>
            </#if>
        </#list>
			)
		]]>
	</insert>
