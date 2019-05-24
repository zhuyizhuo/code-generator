<#assign methodComment = "${methodDescription.QUERY_BY_WHERE.comment}">
<#assign methodReturn = "符合条件的数据集合">
<#include "comment/commonCommentPara.ftl"/>
<#include "comment/methodComment.ftl"/>
    List<${stratificationInfo.pojoName}> ${methodDescription.QUERY_BY_WHERE.methodName}(${stratificationInfo.pojoName} ${methodParam});
