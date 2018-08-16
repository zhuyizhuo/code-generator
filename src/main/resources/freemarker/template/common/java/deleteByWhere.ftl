<#include "comment/commonCommentPara.ftl"/>
<#assign methodDescription = "${methodInfo.deleteMethodDescription}">
<#include "comment/methodComment.ftl"/>
    int ${methodInfo.deleteMethodName}(${stratificationInfo.pojoName} ${methodParam});
