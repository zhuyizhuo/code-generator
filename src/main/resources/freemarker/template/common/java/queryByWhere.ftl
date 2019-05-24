<#assign methodDescription = "${methodCommentInfo.queryMethodDescription}">
<#assign methodReturn = "符合条件的数据集合">
<#include "comment/commonCommentPara.ftl"/>
<#include "comment/methodComment.ftl"/>
    List<${stratificationInfo.pojoName}> ${methodInfo.methodDescription.QUERY_BY_WHERE.methodName}(${stratificationInfo.pojoName} ${methodParam});
