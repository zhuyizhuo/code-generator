<#include "comment/commonCommentPara.ftl"/>
<#assign methodDescription = "${methodCommentInfo.deleteMethodDescription}">
<#include "comment/methodComment.ftl"/>
    int ${methodInfo.deleteMethodName}(${stratificationInfo.pojoName} ${methodParam});
