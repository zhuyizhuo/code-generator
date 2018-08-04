	<!-- 查询用 条件判断 -->
  	<sql id="condition_list">
      <where>
<#list tableInfo.columnLists as colm>
    <#if colm??>
        <#if colm.javaDataType = 'String'><if test="null != ${colm.javaColumnName} and '' != ${colm.javaColumnName}"><#else><if test="null != ${colm.javaColumnName}"></#if>
            AND ${colm.columnName} = ${'#{'}${colm.javaColumnName}}
        </if>
    </#if>
</#list>
      </where>
  	</sql>

    <sql id="select_columns">
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
    </sql>

    <sql id="count">
        SELECT COUNT(*)
    </sql>

  	<!-- 基本多条件查询 -->
  	<sql id="base_query_by_where_sql">
		FROM
			 ${tableInfo.tableName}
		<include refid="condition_list" />
  	</sql>

	<!-- 根据条件查询  -->
	<select id="${methodInfo.queryMethodName}" resultMap="${resultMap}" parameterType="${parameterType}">
        <include refid="select_columns" />
        <include refid="base_query_by_where_sql" />
    </select>

	<!-- 查询总数  -->
	<select id="${methodInfo.countMethodName}" resultType="int" parameterType="${parameterType}">
		<include refid="count" />
		<include refid="base_query_by_where_sql" />
	</select>