<#include "comment/commonCommentPara.ftl"/>
<#assign methodReturn = "更新的数据条数">
<#assign methodDescription = "${methodCommentInfo.updateMethodDescription}">
    <#include "comment/methodComment.ftl"/>
    int ${methodInfo.updateMethodName}(${stratificationInfo.pojoName} ${methodParam});
