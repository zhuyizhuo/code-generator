<#include "comment/commonCommentPara.ftl"/>
<#assign methodReturn = "删除的数据条数">
<#assign methodComment = "${methodDescription.DELETE_BY_WHERE.comment}">
<#include "comment/methodComment.ftl"/>
    int ${methodDescription.DELETE_BY_WHERE.methodName}(${stratificationInfo.pojoName} ${methodParam});
