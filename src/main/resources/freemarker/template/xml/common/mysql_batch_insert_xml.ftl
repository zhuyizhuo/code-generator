    <!-- ${methodDescription.BATCH_INSERT.comment} -->
	<insert id="${methodDescription.BATCH_INSERT.methodName}" parameterType="java.util.List">
        INSERT INTO ${tableInfo.tableSchema}.${tableInfo.tableName} (
        <trim suffixOverrides=",">
        <#list mybatisXmlDefinition.columns as colm>
            <#if colm??>
				<if test="null != list[0].${colm.javaColumnName}">${colm.columnName},</if>
            </#if>
        </#list>
        </trim>
        ) VALUES
        <foreach collection="list" item="item" index= "index" separator =",">
            (
            <trim suffixOverrides=",">
			<#list mybatisXmlDefinition.columns as colm>
			<#if colm??>
				<if test="null != item.${colm.javaColumnName}">${'#'}{item.${colm.javaColumnName},jdbcType=${colm.jdbcType}},</if>
            </#if>
            </#list>
            </trim>
            )
        </foreach>
    </insert>
