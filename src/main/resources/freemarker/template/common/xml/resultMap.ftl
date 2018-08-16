    <!-- resultMap定义  <#if tableInfo.tableComment != "TODO">${tableInfo.tableComment} </#if>${tableInfo.tableName}与实体对应关系 -->
	<resultMap id="${resultMapId}" type="${parameterType}">
<#if tableInfo.hasPrimaryKey>
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
<#else>
    <#list tableInfo.columnLists as colm>
        <#if colm??>
        <result column="${colm.columnName}" property="${colm.javaColumnName}"/>
        </#if>
    </#list>
</#if>
    </resultMap>
