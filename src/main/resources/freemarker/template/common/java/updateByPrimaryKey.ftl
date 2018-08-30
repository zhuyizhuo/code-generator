<#assign methodDescription = "${methodCommentInfo.updateByPrimaryKeyMethodDescription}">
<#assign methodReturn = "更新的数据条数">
<#include "comment/commonCommentPara.ftl"/>
<#include "comment/methodComment.ftl"/>
    int ${methodInfo.updateByPrimaryKeyMethodName}(${stratificationInfo.pojoName} ${methodParam});

