	<!-- ${methodCommentInfo.countMethodDescription} -->
	<select id="${methodInfo.countMethodName}" resultType="int" parameterType="${tableInfo.parameterType}">
		SELECT COUNT(*) FROM
		<include refid="Table_Name" />
		<include refid="Where_Clause" />
	</select>
