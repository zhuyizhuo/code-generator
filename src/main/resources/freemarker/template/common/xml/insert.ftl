    <!-- ${methodCommentInfo.insertMethodDescription} -->
	<insert id="${methodInfo.insertMethodName}" parameterType="${parameterType}">
        INSERT INTO
        <include refid="Table_Name" />
            <trim suffixOverrides=",">
        <#list tableInfo.columnLists as colm>
            <#if colm??>
                <if test="${colm.javaColumnName} != null">${colm.columnName},</if>
            </#if>
        </#list>
            </trim>
        ) VALUES (
            <trim suffixOverrides=",">
        <#list tableInfo.columnLists as colm>
            <#if colm??>
                <if test="${colm.javaColumnName} != null">${'#{'}${colm.javaColumnName},jdbcType=${colm.columnJdbcType}},</if>
            </#if>
        </#list>
            </trim>
        )
	</insert>
