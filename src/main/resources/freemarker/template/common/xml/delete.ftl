    <!-- 删除数据  -->
	<delete id="${methodInfo.deleteMethodName}" parameterType="${parameterType}">
        DELETE FROM ${tableInfo.tableName} WHERE ${tableInfo.columnLists[0].columnName} = ${'#{'}${tableInfo.columnLists[0].javaColumnName}}
	</delete>
