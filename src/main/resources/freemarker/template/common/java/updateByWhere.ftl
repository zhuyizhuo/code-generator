<#include "comment/commonCommentPara.ftl"/>
<#assign methodDescription = "${methodInfo.updateMethodDescription}">
    <#include "comment/methodComment.ftl"/>
    int ${methodInfo.updateMethodName}(${stratificationInfo.pojoName} ${methodParam});
