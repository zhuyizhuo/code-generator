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
