	<!-- ${methodCommentInfo.countMethodDescription} -->
	<select id="${methodInfo.methodDescription.COUNT_BY_WHERE.methodName}" resultType="int" parameterType="${mybatisXmlDefinition.parameterType}">
		SELECT COUNT(*) FROM
		<include refid="Table_Name" />
		<include refid="Where_Clause" />
	</select>
