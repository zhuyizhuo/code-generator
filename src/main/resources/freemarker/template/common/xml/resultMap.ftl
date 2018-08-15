    <!-- resultMap定义  <#if tableInfo.tableComment != "TODO">${tableInfo.tableComment} </#if>${tableInfo.tableName}与实体对应关系 -->
	<resultMap id="${resultMapId}" type="${parameterType}">
<#list tableInfo.primaryKeyColumns as colm>
    <#if colm??>
        <id column="${colm.columnName}" property="${colm.javaColumnName}"/>
    </#if>
</#list>
<#list tableInfo.otherColumns as colm>
	<#if colm??>
        <result column="${colm.columnName}" property="${colm.javaColumnName}"/>
    </#if>
</#list>
    </resultMap>
