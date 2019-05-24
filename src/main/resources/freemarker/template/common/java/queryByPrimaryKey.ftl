<#assign methodComment = "${methodDescription.QUERY_BY_PRIMARY_KEY.comment}">
<#assign methodReturn = "${stratificationInfo.pojoName} 数据对象">
<#if tableInfo.singlePrimaryKey>
    <#include "comment/singlePrimaryKeyCommentPara.ftl"/>
    <#include "comment/methodComment.ftl"/>
    ${stratificationInfo.pojoName} ${methodDescription.QUERY_BY_PRIMARY_KEY.methodName}(${paramType} ${methodParam});
<#else>
    <#include "comment/commonCommentPara.ftl"/>
    <#include "comment/methodComment.ftl"/>
    ${stratificationInfo.pojoName} ${methodDescription.QUERY_BY_PRIMARY_KEY.methodName}(${stratificationInfo.pojoName} ${methodParam});
</#if>
