<#include "comment/commonCommentPara.ftl"/>
<#assign methodReturn = "删除的数据条数">
<#assign methodDescription = "${methodCommentInfo.deleteMethodDescription}">
<#include "comment/methodComment.ftl"/>
    int ${methodInfo.deleteMethodName}(${stratificationInfo.pojoName} ${methodParam});
