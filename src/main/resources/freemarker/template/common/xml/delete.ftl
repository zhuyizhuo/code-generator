    <!-- 根据主键删除数据 -->
	<delete id="${methodInfo.deleteByPrimaryKeyMethodName}" parameterType="${parameterType}">
        DELETE FROM ${tableInfo.tableName} WHERE <#list tableInfo.primaryKeyColumns as colm><#if colm_index != 0>AND </#if>${colm.columnName} = ${'#{'}${colm.javaColumnName}} </#list>
	</delete>
