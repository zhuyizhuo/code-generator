<#include "comment/commonCommentPara.ftl"/>
<#assign methodDescription = "${methodInfo.countMethodDescription}">
<#include "comment/methodComment.ftl"/>
    int ${methodInfo.countMethodName}(${stratificationInfo.pojoName} ${methodParam});
