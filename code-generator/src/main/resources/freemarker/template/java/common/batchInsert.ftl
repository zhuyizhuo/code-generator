	/**
     * ${methodDescription.BATCH_INSERT.comment} <br>
<#list methodDescription.BATCH_INSERT.params as param>
     * @param ${javaClassDefinition.MODEL.className?uncap_first}List ${param.comment}  <br>
</#list>
     * @return 新增的数据条数
     */
    int ${methodDescription.BATCH_INSERT.methodName}(List<${javaClassDefinition.MODEL.className}> ${javaClassDefinition.MODEL.className?uncap_first}List);
