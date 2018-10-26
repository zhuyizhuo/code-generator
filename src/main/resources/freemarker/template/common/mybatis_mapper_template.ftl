<#list mybatisXmlDefinition.mybatisHeader as header>
<#if header??>
${header}
</#if>
</#list>
<mapper namespace="${mybatisXmlDefinition.nameSpace}">

    <resultMap id="${mybatisXmlDefinition.resultMap.id}" type="${mybatisXmlDefinition.resultMap.type}">
<#list mybatisXmlDefinition.resultMap.results as result>
    <#if result??>
        <#if result.primaryKey>
        <id column="${result.column}" property="${result.property}"/>
        <#else>
        <result column="${result.column}" property="${result.property}"/>
        </#if>
    </#if>
</#list>
    </resultMap>

    <sql id="Where_Clause">
        <where>
<#list mybatisXmlDefinition.columns as colm>
    <#if colm??>
        <if test="${colm.testNotBlankExpression}">
            AND ${colm.columnName} = ${'#'}{${colm.javaColumnName}}
        </if>
    </#if>
</#list>
        </where>
    </sql>

    <sql id="Base_Column_List">
<#list mybatisXmlDefinition.columns as colm>
    <#if colm??>
        ${colm.columnName}<#if colm_has_next>,</#if>
    </#if>
</#list>
    </sql>

    <!-- ${methodCommentInfo.insertMethodDescription} -->
    <insert id="${methodInfo.insertMethodName}" parameterType="${mybatisXmlDefinition.parameterType}">
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

<#if methodInfo.deleteByPrimaryKeyMethodEnabled && tableInfo.hasPrimaryKey>

    <#assign parameterType = "${tableInfo.singlePrimaryKey?then(mybatisXmlDefinition.columns[0].parameterType,mybatisXmlDefinition.parameterType)}">
    <!-- ${methodCommentInfo.deleteByPrimaryKeyMethodDescription} -->
	<delete id="${methodInfo.deleteByPrimaryKeyMethodName}" parameterType="${parameterType}">
        DELETE FROM
        ${mybatisXmlDefinition.tableSchema}.${mybatisXmlDefinition.tableName}
        WHERE <#list tableInfo.primaryKeyColumns as colm><#if colm_index != 0>AND </#if>${colm.columnName} = ${'#'}{${colm.javaColumnName}} </#list>
	</delete>
</#if>

    <!-- ${methodCommentInfo.deleteMethodDescription} -->
    <delete id="${methodInfo.deleteMethodName}" parameterType="${mybatisXmlDefinition.parameterType}">
        DELETE FROM
        ${mybatisXmlDefinition.tableSchema}.${mybatisXmlDefinition.tableName}
        <include refid="Where_Clause" />
    </delete>

<#if methodInfo.updateByPrimaryKeyMethodEnabled && tableInfo.hasPrimaryKey>

    <!-- ${methodCommentInfo.updateByPrimaryKeyMethodDescription} -->
    <update id="${methodInfo.updateByPrimaryKeyMethodName}" parameterType="${mybatisXmlDefinition.parameterType}">
		UPDATE
		<include refid="Table_Name" />
		<set>
    <#list tableInfo.columnLists as colm>
        <#if colm??>
            <#if colm_index != 0>
			<if test="${colm.javaColumnName} != null">${colm.columnName} = ${'#'}{${colm.javaColumnName}},</if>
		    </#if>
		</#if>
	</#list>
		</set>
		WHERE
	<#list tableInfo.primaryKeyColumns as colm>
		 <#if colm_index != 0>AND </#if>${colm.columnName} = ${'#'}{${colm.javaColumnName}}
	</#list>
	</update>
</#if>
<#if methodInfo.queryByPrimaryKeyEnabled && tableInfo.hasPrimaryKey>

    <!-- ${methodCommentInfo.queryByPrimaryKeyDescription}  -->
    <#assign parameterType = "${tableInfo.singlePrimaryKey?then(mybatisXmlDefinition.columns[0].parameterType,mybatisXmlDefinition.parameterType)}">
	<select id="${methodInfo.queryByPrimaryKeyMethodName}" resultMap="${mybatisXmlDefinition.resultMap.id}" parameterType="${parameterType}">
        SELECT
        <include refid="Base_Column_List" />
  	     FROM
        <include refid="Table_Name" />
        WHERE
    <#list tableInfo.primaryKeyColumns as colm>
        <#if colm_index != 0>AND </#if>${colm.columnName} = ${'#'}{${colm.javaColumnName}}
       </#list>
    </select>
</#if>

    <!-- ${methodCommentInfo.queryMethodDescription}  -->
    <select id="${methodInfo.queryMethodName}" resultMap="${mybatisXmlDefinition.resultMap.id}" parameterType="${mybatisXmlDefinition.parameterType}">
        SELECT
        <include refid="Base_Column_List" />
        FROM
        ${mybatisXmlDefinition.tableSchema}.${mybatisXmlDefinition.tableName}
        <include refid="Where_Clause" />
    </select>

    <!-- ${methodCommentInfo.countMethodDescription} -->
    <select id="${methodInfo.countMethodName}" resultType="int" parameterType="${mybatisXmlDefinition.parameterType}">
        SELECT COUNT(*) FROM
        ${mybatisXmlDefinition.tableSchema}.${mybatisXmlDefinition.tableName}
        <include refid="Where_Clause" />
    </select>

</mapper>