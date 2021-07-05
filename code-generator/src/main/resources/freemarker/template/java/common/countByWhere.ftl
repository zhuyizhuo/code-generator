	/**
     * ${methodDescription.COUNT_BY_WHERE.comment} <br>
<#list methodDescription.COUNT_BY_WHERE.params as param>
     * @param ${javaClassDefinition.MODEL.className?uncap_first} ${param.comment}  <br>
</#list>
     * @return 符合条件的数据总数
     */
    int ${methodDescription.COUNT_BY_WHERE.methodName}(${javaClassDefinition.MODEL.className} ${javaClassDefinition.MODEL.className?uncap_first});
