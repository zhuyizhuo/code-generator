<#assign methodDescription = "${methodCommentInfo.deleteByPrimaryKeyMethodDescription}">
<#assign methodReturn = "删除的数据条数">
<#if tableInfo.singlePrimaryKey>
    <#include "comment/singlePrimaryKeyCommentPara.ftl"/>
    <#include "comment/methodComment.ftl"/>
    int ${methodInfo.methodDescription.DELETE_BY_PRIMARY_KEY.methodName}(${paramType} ${methodParam});
<#else>
    <#include "comment/commonCommentPara.ftl"/>
    <#include "comment/methodComment.ftl"/>
    int ${methodInfo.methodDescription.DELETE_BY_PRIMARY_KEY.methodName}(${stratificationInfo.pojoName} ${methodParam});
</#if>
