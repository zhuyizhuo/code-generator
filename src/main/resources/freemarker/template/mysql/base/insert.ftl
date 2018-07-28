    <!-- 新增 -->
	<insert id="${insertMethodName}" parameterType="${parameterType}">
		<![CDATA[
			INSERT INTO ${tableInfo.dbTableName}(
        <#list tableInfo.javaColmBeans as colm>
            <#if colm??>
                ${colm.dbColmName}<#if colm_has_next>,<#else></#if>
            </#if>
        </#list>
			) VALUES (
        <#list tableInfo.javaColmBeans as colm>
            <#if colm??>
                ${'#{'}${colm.javaName},jdbcType=${colm.colmJdbcType}}<#if colm_has_next>,<#else></#if>
            </#if>
        </#list>
			)
		]]>
	</insert>