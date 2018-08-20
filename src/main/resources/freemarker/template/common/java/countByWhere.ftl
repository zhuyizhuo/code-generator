<#include "comment/commonCommentPara.ftl"/>
<#assign methodDescription = "${methodCommentInfo.countMethodDescription}">
<#assign methodReturn = "符合条件的数据总数">
<#include "comment/methodComment.ftl"/>
    int ${methodInfo.countMethodName}(${stratificationInfo.pojoName} ${methodParam});
