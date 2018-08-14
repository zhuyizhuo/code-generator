	<!-- 查询用 条件判断 -->
  	<sql id="Where_Clause">
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

    <sql id="Base_Column_List">
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

    <sql id="Table_Name">
        ${tableInfo.tableName}
    </sql>

	<!-- 根据条件查询  -->
	<select id="${methodInfo.queryMethodName}" resultMap="${resultMapId}" parameterType="${parameterType}">
        SELECT
  	     <include refid="Base_Column_List" />
  	     FROM
  	     <include refid="Table_Name" />
        <include refid="Where_Clause" />
    </select>

	<!-- 查询总数  -->
	<select id="${methodInfo.countMethodName}" resultType="int" parameterType="${parameterType}">
		SELECT COUNT(*) FROM
		<include refid="Table_Name" />
		<include refid="Where_Clause" />
	</select>
