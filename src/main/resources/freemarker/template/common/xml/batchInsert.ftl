	<!-- ${methodCommentInfo.batchInsertMethodDescription} -->
	<insert id="${methodInfo.batchInsertMethodName}" parameterType="java.util.List">
        INSERT All
        <foreach collection="list" item="item">
            INTO ${mybatisXmlDefinition.tableSchema}.${mybatisXmlDefinition.tableName} (
            <trim suffixOverrides=",">
		    <#list mybatisXmlDefinition.columns as colm>
			<#if colm??>
				<if test="null != item.${colm.javaColumnName}">${colm.columnName},</if>
            </#if>
            </#list>
            </trim>
            ) VALUES (
            <trim suffixOverrides=",">
			<#list mybatisXmlDefinition.columns as colm>
			<#if colm??>
				<if test="null != item.${colm.javaColumnName}">${'#'}{item.${colm.javaColumnName},jdbcType=${colm.jdbcType}},</if>
            </#if>
            </#list>
            </trim>
            )
        </foreach>
        SELECT 1 FROM DUAL
    </insert>
