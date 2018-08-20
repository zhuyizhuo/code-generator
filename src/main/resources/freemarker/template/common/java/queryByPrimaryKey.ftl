<#assign methodDescription = "${methodCommentInfo.queryByPrimaryKeyDescription}">
<#assign methodReturn = "${stratificationInfo.pojoName} 数据对象">
<#if tableInfo.singlePrimaryKey>
    <#include "comment/singlePrimaryKeyCommentPara.ftl"/>
    <#include "comment/methodComment.ftl"/>
    ${stratificationInfo.pojoName} ${methodInfo.queryByPrimaryKeyMethodName}(${paramType} ${methodParam});
<#else>
    <#include "comment/commonCommentPara.ftl"/>
    <#include "comment/methodComment.ftl"/>
    ${stratificationInfo.pojoName} ${methodInfo.queryByPrimaryKeyMethodName}(${stratificationInfo.pojoName} ${methodParam});
</#if>
