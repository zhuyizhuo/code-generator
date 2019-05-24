	<!-- ${methodDescription.QUERY_BY_WHERE.comment}  -->
	<select id="${methodDescription.QUERY_BY_WHERE.methodName}" resultMap="${mybatisXmlDefinition.resultMap.id}" parameterType="${mybatisXmlDefinition.parameterType}">
        SELECT
        <include refid="Base_Column_List" />
  	     FROM
        <include refid="Table_Name" />
        <include refid="Where_Clause" />
    </select>
