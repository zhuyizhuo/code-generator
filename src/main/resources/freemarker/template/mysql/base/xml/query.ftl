	<!-- 查询用 条件判断 -->
  	<sql id="condition_list">
    <#list tableInfo.columnLists as colm>
		<#if colm??>
			<#if colm.javaDataType = 'String'>
		<if test="null != ${colm.javaColumnName} and '' != ${colm.javaColumnName}">
			<#else>
		<if test="null != ${colm.javaColumnName}">
			</#if>
			AND ${colm.columnName} = ${'#{'}${colm.javaColumnName}}
		</if>
	    </#if>
	</#list>
  	</sql>

  	<!-- 基本多条件查询 -->
  	<sql id="base_query_by_where_sql">
		SELECT
		<#list tableInfo.columnLists as colm>
		<#if colm??>
			<#if colm_has_next>
			${colm.columnName?upper_case},
			<#else>
			${colm.columnName?upper_case}
			</#if>
		</#if>
		</#list>
		FROM
			 ${tableInfo.tableName}
		WHERE
			1 = 1
		<include refid="condition_list" />
  	</sql>

	<!-- 根据条件查询  -->
	<select id="${methodInfo.queryMethodName}" resultMap="${tableInfo.javaTableName?uncap_first}ResultMap" parameterType="${parameterType}">
        <include refid="base_query_by_where_sql" />
    </select>
