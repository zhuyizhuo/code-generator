<#assign methodComment = "${methodDescription.UPDATE_BY_PRIMARY_KEY.comment}">
<#assign methodReturn = "更新的数据条数">
<#include "comment/commonCommentPara.ftl"/>
<#include "comment/methodComment.ftl"/>
    int ${methodDescription.UPDATE_BY_PRIMARY_KEY.methodName}(${stratificationInfo.pojoName} ${methodParam});

