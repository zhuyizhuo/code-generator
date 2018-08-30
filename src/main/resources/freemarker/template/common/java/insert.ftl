<#include "comment/commonCommentPara.ftl"/>
<#assign methodReturn = "新增的数据条数">
<#assign methodDescription = "${methodCommentInfo.insertMethodDescription}">
    <#include "comment/methodComment.ftl"/>
	int ${methodInfo.insertMethodName}(${stratificationInfo.pojoName} ${stratificationInfo.pojoName?uncap_first});
