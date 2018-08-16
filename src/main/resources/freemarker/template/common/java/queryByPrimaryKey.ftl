<#assign methodDescription = "${methodInfo.queryByPrimaryKeyDescription}">
<#if tableInfo.singlePrimaryKey>
    <#include "comment/singlePrimaryKeyCommentPara.ftl"/>
    <#include "comment/methodComment.ftl"/>
    ${stratificationInfo.pojoName} ${methodInfo.queryByPrimaryKeyMethodName}(${paramType} ${methodParam});
<#else>
    <#include "comment/commonCommentPara.ftl"/>
    <#include "comment/methodComment.ftl"/>
    ${stratificationInfo.pojoName} ${methodInfo.queryByPrimaryKeyMethodName}(${stratificationInfo.pojoName} ${methodParam});
</#if>
