	<!-- ${methodDescription.COUNT_BY_WHERE.comment} -->
	<select id="${methodDescription.COUNT_BY_WHERE.methodName}" resultType="int" parameterType="${mybatisXmlDefinition.parameterType}">
		SELECT COUNT(*) FROM
		${mybatisXmlDefinition.tableSchema}.${mybatisXmlDefinition.tableName}
		<include refid="Where_Clause" />
	</select>
