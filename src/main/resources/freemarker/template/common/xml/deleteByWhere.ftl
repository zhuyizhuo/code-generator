    <!-- ${methodCommentInfo.deleteMethodDescription} -->
	<delete id="${methodInfo.deleteMethodName}" parameterType="${parameterType}">
        DELETE FROM
        <include refid="Table_Name" />
        <include refid="Where_Clause" />
	</delete>
