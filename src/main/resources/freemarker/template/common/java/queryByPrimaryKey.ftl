<#assign methodDescription = "${methodInfo.queryByPrimaryKeyDescription}">
<#if tableInfo.singlePrimaryKey>
    <#include "comment/singlePrimaryKeyCommentPara.ftl"/>
    <#include "comment/methodComment.ftl"/>
	int ${methodInfo.queryByPrimaryKeyMethodName}(${paramType} ${methodParam});
<#else>
    <#include "comment/commonCommentPara.ftl"/>
    <#include "comment/methodComment.ftl"/>
    int ${methodInfo.queryByPrimaryKeyMethodName}(${stratificationInfo.pojoName} ${methodParam});
</#if>
