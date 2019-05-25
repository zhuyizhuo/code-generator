<#assign methodParam = "${javaClassDefinition.POJO.className?uncap_first}">
<#assign paramDescription = "${methodCommentInfo.paramsDescription}">
<#assign methodReturn = "删除的数据条数">
<#assign methodComment = "${methodDescription.DELETE_BY_WHERE.comment}">
	/**
     * ${methodComment} <br>
     * @param ${methodParam} ${paramDescription}  <br>
     * @return ${methodReturn}
     */
    int ${methodDescription.DELETE_BY_WHERE.methodName}(${javaClassDefinition.POJO.className} ${methodParam});
