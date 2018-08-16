<#if tableInfo.singlePrimaryKey>
    <#include "comment/singlePrimaryKeyCommentPara.ftl"/>
    <#assign methodDescription = "${methodInfo.updateByPrimaryKeyMethodDescription}">
    <#include "comment/methodComment.ftl"/>
	int ${methodInfo.updateByPrimaryKeyMethodName}(${paramType} ${methodParam});
<#else>
    <#include "comment/commonCommentPara.ftl"/>
    <#assign methodDescription = "${methodInfo.updateByPrimaryKeyMethodDescription}">
    <#include "comment/methodComment.ftl"/>
    int ${methodInfo.updateByPrimaryKeyMethodName}(${stratificationInfo.pojoName} ${methodParam});
</#if>
