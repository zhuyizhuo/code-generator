<#assign methodDescription = "${methodCommentInfo.updateByPrimaryKeyMethodDescription}">
<#assign methodReturn = "更新的数据条数">
<#include "comment/commonCommentPara.ftl"/>
<#include "comment/methodComment.ftl"/>
    int ${methodInfo.methodDescription.UPDATE_BY_PRIMARY_KEY.methodName}(${stratificationInfo.pojoName} ${methodParam});

