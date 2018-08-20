<#include "comment/commonCommentPara.ftl"/>
<#assign methodDescription = "${methodCommentInfo.updateMethodDescription}">
    <#include "comment/methodComment.ftl"/>
    int ${methodInfo.updateMethodName}(${stratificationInfo.pojoName} ${methodParam});
