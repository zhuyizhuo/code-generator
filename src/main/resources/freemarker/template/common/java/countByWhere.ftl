<#include "comment/commonCommentPara.ftl"/>
<#assign methodDescription = "${methodCommentInfo.countMethodDescription}">
<#include "comment/methodComment.ftl"/>
    int ${methodInfo.countMethodName}(${stratificationInfo.pojoName} ${methodParam});
