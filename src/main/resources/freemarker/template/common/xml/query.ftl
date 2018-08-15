	<!-- ${methodInfo.queryMethodDescription}  -->
	<select id="${methodInfo.queryMethodName}" resultMap="${resultMapId}" parameterType="${parameterType}">
        SELECT
        <include refid="Base_Column_List" />
  	     FROM
        <include refid="Table_Name" />
        <include refid="Where_Clause" />
    </select>

	<!-- ${methodInfo.countMethodDescription} -->
	<select id="${methodInfo.countMethodName}" resultType="int" parameterType="${parameterType}">
		SELECT COUNT(*) FROM
		<include refid="Table_Name" />
		<include refid="Where_Clause" />
	</select>
