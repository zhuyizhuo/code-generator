<#assign methodDescription = "${methodCommentInfo.deleteByPrimaryKeyMethodDescription}">
<#assign methodReturn = "删除的数据条数">
<#if tableInfo.singlePrimaryKey>
    <#include "comment/singlePrimaryKeyCommentPara.ftl"/>
    <#include "comment/methodComment.ftl"/>
    int ${methodInfo.deleteByPrimaryKeyMethodName}(${paramType} ${methodParam});
<#else>
    <#include "comment/commonCommentPara.ftl"/>
    <#include "comment/methodComment.ftl"/>
    int ${methodInfo.deleteByPrimaryKeyMethodName}(${stratificationInfo.pojoName} ${methodParam});
</#if>
