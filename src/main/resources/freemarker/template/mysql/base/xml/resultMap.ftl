    <!-- resultMap定义  ${dbTableInfo.tableComment} ${dbTableInfo.tableName}与实体对应关系 -->
	<resultMap id="${javaTableInfo.javaTableName?lower_case}ResultMap" type="${stratificationInfo.pojoFullPackage}.${stratificationInfo.pojoName}">
<#list dbTableInfo.columnLists as colm>
	<#if colm??>
        <#if colm_index = 0><id <#else><result </#if>column="${colm.columnName}" property="${dbTableInfo.javaColumnLists[colm_index].javaColumnName}"/>
    </#if>
</#list>
    </resultMap>
