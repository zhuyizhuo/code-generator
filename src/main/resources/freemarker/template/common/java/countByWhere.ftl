<#assign methodParam = "${stratificationInfo.pojoName?uncap_first}">
	/**
     * ${methodCommentInfo.countMethodDescription} <br>
     * @param ${methodParam} ${methodCommentInfo.paramsDescription}  <br>
     * @return 符合条件的数据总数
     */
    int ${methodInfo.methodDescription.COUNT_BY_WHERE.methodName}(${stratificationInfo.pojoName} ${methodParam});
