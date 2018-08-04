    <!-- resultMap定义  ${tableInfo.tableComment} ${tableInfo.tableName}与实体对应关系 -->
	<resultMap id="${tableInfo.javaTableName?uncap_first}ResultMap" type="${parameterType}">
<#list tableInfo.columnLists as colm>
	<#if colm??>
        <#if colm_index = 0><id <#else><result </#if>column="${colm.columnName}" property="${colm.javaColumnName}"/>
    </#if>
</#list>
    </resultMap>
