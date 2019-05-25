	/**
     * ${methodDescription.BATCH_INSERT.comment} <br>
<#list methodDescription.COUNT_BY_WHERE.params as param>
     * @param ${javaClassDefinition.POJO.className?uncap_first}List ${param.comment}  <br>
</#list>
     * @return 符合条件的数据总数
     */
    void ${methodDescription.BATCH_INSERT.methodName}(List<${javaClassDefinition.POJO.className}> ${javaClassDefinition.POJO.className?uncap_first}List);
