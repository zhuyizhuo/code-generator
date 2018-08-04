    <!-- 新增 -->
	<insert id="${methodInfo.insertMethodName}" parameterType="${parameterType}">
			INSERT INTO ${tableInfo.tableName}(
            <trim suffixOverrides=",">
        <#list tableInfo.columnLists as colm>
            <#if colm??>
                <if test="null != ${colm.javaColumnName}">${colm.columnName},</if>
            </#if>
        </#list>
            </trim>
			) VALUES (
            <trim suffixOverrides=",">
        <#list tableInfo.columnLists as colm>
            <#if colm??>
                <if test="null != ${colm.javaColumnName}">${'#{'}${colm.javaColumnName},jdbcType=${colm.dataType}},</if>
            </#if>
        </#list>
            </trim>
			)
	</insert>
