<#include "comment/commonCommentPara.ftl"/>
<#assign methodDescription = "${methodCommentInfo.insertMethodDescription}">
    <#include "comment/methodComment.ftl"/>
	int ${methodInfo.insertMethodName}(${stratificationInfo.pojoName} ${methodParam});
