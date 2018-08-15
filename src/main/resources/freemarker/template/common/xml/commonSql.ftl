  	<sql id="Where_Clause">
      <where>
<#list tableInfo.columnLists as colm>
    <#if colm??>
        <#if colm.javaDataType = 'String'><if test="${colm.javaColumnName} != null and ${colm.javaColumnName} != ''"><#else><if test="${colm.javaColumnName} != null"></#if>
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
