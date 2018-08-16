<#include "comment/commonCommentPara.ftl"/>
<#assign methodDescription = "${methodInfo.insertMethodDescription}">
    <#include "comment/methodComment.ftl"/>
	int ${methodInfo.insertMethodName}(${stratificationInfo.pojoName} ${methodParam});
