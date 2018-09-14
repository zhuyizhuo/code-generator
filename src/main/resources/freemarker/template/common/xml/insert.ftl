    <!-- ${methodCommentInfo.insertMethodDescription} -->
	<insert id="${methodInfo.insertMethodName}" parameterType="${mybatisXmlDefinition.parameterType}">
        INSERT INTO
        <include refid="Table_Name" /> (
            <trim suffixOverrides=",">
        <#list mybatisXmlDefinition.columns as colm>
            <#if colm??>
                <if test="${colm.testNotNullExpression}">${colm.columnName},</if>
            </#if>
        </#list>
            </trim>
        ) VALUES (
            <trim suffixOverrides=",">
        <#list mybatisXmlDefinition.columns as colm>
            <#if colm??>
                <if test="${colm.testNotNullExpression}">${'#'}{${colm.javaColumnName},jdbcType=${colm.jdbcType}},</if>
            </#if>
        </#list>
            </trim>
        )
	</insert>
