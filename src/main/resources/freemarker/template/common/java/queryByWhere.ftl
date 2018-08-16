<#assign methodDescription = "${methodInfo.queryMethodDescription}">
<#include "comment/methodComment.ftl"/>
    List<${stratificationInfo.pojoName}> ${methodInfo.queryMethodName}(${stratificationInfo.pojoName} ${methodParam});
