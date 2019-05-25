<#assign methodParam = "${javaClassDefinition.POJO.className?uncap_first}">
<#assign paramDescription = "${methodCommentInfo.paramsDescription}">
<#assign methodReturn = "更新的数据条数">
<#assign methodComment = "${methodCommentInfo.updateMethodDescription}">
	/**
     * ${methodComment} <br>
     * @param ${methodParam} ${paramDescription}  <br>
     * @return ${methodReturn}
     */
    int ${methodInfo.updateMethodName}(${javaClassDefinition.POJO.className} ${methodParam});
