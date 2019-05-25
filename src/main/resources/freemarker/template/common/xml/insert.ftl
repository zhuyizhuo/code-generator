    <!-- ${methodDescription.INSERT.comment} -->
	<insert id="${methodDescription.INSERT.methodName}" parameterType="${mybatisXmlDefinition.parameterType}">
        INSERT INTO
        ${mybatisXmlDefinition.tableSchema}.${mybatisXmlDefinition.tableName} (
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
