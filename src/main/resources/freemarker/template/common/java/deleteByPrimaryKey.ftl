<#assign methodDescription = "${methodInfo.deleteByPrimaryKeyMethodDescription}">
<#if tableInfo.singlePrimaryKey>
    <#include "comment/singlePrimaryKeyCommentPara.ftl"/>
    <#include "comment/methodComment.ftl"/>
    int ${methodInfo.deleteByPrimaryKeyMethodName}(${paramType} ${methodParam});
<#else>
    <#include "comment/commonCommentPara.ftl"/>
    <#include "comment/methodComment.ftl"/>
    int ${methodInfo.deleteByPrimaryKeyMethodName}(${stratificationInfo.pojoName} ${methodParam});
</#if>
