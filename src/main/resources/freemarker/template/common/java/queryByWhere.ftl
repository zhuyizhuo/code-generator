<#assign methodComment = "${methodDescription.QUERY_BY_WHERE.comment}">
<#assign methodReturn = "符合条件的数据集合">
<#assign methodParam = "${javaClassDefinition.POJO.className?uncap_first}">
<#assign paramDescription = "${methodCommentInfo.paramsDescription}">
	/**
     * ${methodComment} <br>
     * @param ${methodParam} ${paramDescription}  <br>
     * @return ${methodReturn}
     */
    List<${javaClassDefinition.POJO.className}> ${methodDescription.QUERY_BY_WHERE.methodName}(${javaClassDefinition.POJO.className} ${methodParam});
