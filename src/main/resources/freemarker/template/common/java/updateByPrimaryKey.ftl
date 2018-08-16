<#assign methodDescription = "${methodInfo.updateByPrimaryKeyMethodDescription}">
<#if tableInfo.singlePrimaryKey>
    <#include "comment/singlePrimaryKeyCommentPara.ftl"/>
    <#include "comment/methodComment.ftl"/>
	int ${methodInfo.updateByPrimaryKeyMethodName}(${paramType} ${methodParam});
<#else>
    <#include "comment/commonCommentPara.ftl"/>
    <#include "comment/methodComment.ftl"/>
    int ${methodInfo.updateByPrimaryKeyMethodName}(${stratificationInfo.pojoName} ${methodParam});
</#if>
