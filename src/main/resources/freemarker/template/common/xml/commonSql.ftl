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
