<#assign methodComment = "${methodDescription.DELETE_BY_PRIMARY_KEY.comment}">
<#assign methodReturn = "删除的数据条数">
<#if tableInfo.singlePrimaryKey>
	/**
     * ${methodComment} <br>
     * @param ${tableInfo.primaryKeyColumns[0].javaColumnName?uncap_first} ${tableInfo.primaryKeyColumns[0].columnComment}  <br>
     * @return ${methodReturn}
     */
    int ${methodDescription.DELETE_BY_PRIMARY_KEY.methodName}(${tableInfo.primaryKeyColumns[0].javaDataType} ${tableInfo.primaryKeyColumns[0].javaColumnName?uncap_first});
<#else>
    <#assign methodParam = "${javaClassDefinition.POJO.className?uncap_first}">
    <#assign paramDescription = "${methodCommentInfo.paramsDescription}">
	/**
     * ${methodComment} <br>
     * @param ${methodParam} ${paramDescription}  <br>
     * @return ${methodReturn}
     */
    int ${methodDescription.DELETE_BY_PRIMARY_KEY.methodName}(${javaClassDefinition.POJO.className} ${methodParam});
</#if>
