	<!-- ${methodCommentInfo.queryMethodDescription}  -->
	<select id="${methodInfo.queryMethodName}" resultMap="${resultMapId}" parameterType="${parameterType}">
        SELECT
        <include refid="Base_Column_List" />
  	     FROM
        <include refid="Table_Name" />
        <include refid="Where_Clause" />
    </select>
