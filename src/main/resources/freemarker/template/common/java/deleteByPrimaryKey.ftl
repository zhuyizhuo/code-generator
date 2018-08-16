<#if tableInfo.singlePrimaryKey>
    <#include "comment/singlePrimaryKeyCommentPara.ftl"/>
    <#assign methodDescription = "${methodInfo.deleteByPrimaryKeyMethodDescription}">
    <#include "comment/methodComment.ftl"/>
    int ${methodInfo.deleteByPrimaryKeyMethodName}(${paramType} ${methodParam});
<#else>
    <#include "comment/commonCommentPara.ftl"/>
    <#assign methodDescription = "${methodInfo.deleteByPrimaryKeyMethodDescription}">
    <#include "comment/methodComment.ftl"/>
    int ${methodInfo.deleteByPrimaryKeyMethodName}(${stratificationInfo.pojoName} ${methodParam});
</#if>
