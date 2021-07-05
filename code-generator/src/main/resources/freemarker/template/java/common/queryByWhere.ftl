	/**
     * ${methodDescription.QUERY_BY_WHERE.comment} <br>
    <#list methodDescription.QUERY_BY_WHERE.params as param>
     * @param ${javaClassDefinition.MODEL.className?uncap_first} ${param.comment}  <br>
    </#list>
     * @return 符合条件的数据集合
     */
    List<${javaClassDefinition.MODEL.className}> ${methodDescription.QUERY_BY_WHERE.methodName}(${javaClassDefinition.MODEL.className} ${javaClassDefinition.MODEL.className?uncap_first});
