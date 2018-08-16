	<!-- ${methodInfo.countMethodDescription} -->
	<select id="${methodInfo.countMethodName}" resultType="int" parameterType="${parameterType}">
		SELECT COUNT(*) FROM
		<include refid="Table_Name" />
		<include refid="Where_Clause" />
	</select>
