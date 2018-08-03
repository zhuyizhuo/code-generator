    <!-- 新增 -->
	<insert id="${methodInfo.insertMethodName}" parameterType="${stratificationInfo.pojoFullPackage}.${stratificationInfo.pojoName}">
			INSERT INTO ${dbTableInfo.tableName}(
            <trim suffixOverrides=",">
        <#list dbTableInfo.columnLists as colm>
            <#if colm??>
                <if test="null != ${dbTableInfo.javaColumnLists[colm_index].javaColumnName}">${colm.columnName},</if>
            </#if>
        </#list>
            </trim>
			) VALUES (
            <trim suffixOverrides=",">
        <#list dbTableInfo.javaColumnLists as colm>
            <#if colm??>
                <if test="null != ${colm.javaColumnName}">${'#{'}${colm.javaColumnName},jdbcType=${dbTableInfo.columnLists[colm_index].dataType}},</if>
            </#if>
        </#list>
            </trim>
			)
	</insert>
