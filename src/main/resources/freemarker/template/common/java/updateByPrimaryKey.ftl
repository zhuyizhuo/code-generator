<#assign methodComment = "${methodDescription.UPDATE_BY_PRIMARY_KEY.comment}">
<#assign methodReturn = "更新的数据条数">
<#assign methodParam = "${javaClassDefinition.POJO.className?uncap_first}">
<#assign paramDescription = "${methodCommentInfo.paramsDescription}">
	/**
     * ${methodComment} <br>
     * @param ${methodParam} ${paramDescription}  <br>
     * @return ${methodReturn}
     */
    int ${methodDescription.UPDATE_BY_PRIMARY_KEY.methodName}(${javaClassDefinition.POJO.className} ${methodParam});

