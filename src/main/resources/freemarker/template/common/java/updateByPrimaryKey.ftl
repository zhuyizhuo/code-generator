<#assign methodDescription = "${methodCommentInfo.updateByPrimaryKeyMethodDescription}">
<#assign methodReturn = "更新的数据条数">
<#if tableInfo.singlePrimaryKey>
    <#include "comment/singlePrimaryKeyCommentPara.ftl"/>
    <#include "comment/methodComment.ftl"/>
	int ${methodInfo.updateByPrimaryKeyMethodName}(${paramType} ${methodParam});
<#else>
    <#include "comment/commonCommentPara.ftl"/>
    <#include "comment/methodComment.ftl"/>
    int ${methodInfo.updateByPrimaryKeyMethodName}(${stratificationInfo.pojoName} ${methodParam});
</#if>
