	/**
     * ${methodDescription.INSERT.comment} <br>
    <#list methodDescription.INSERT.params as param>
     * @param ${javaClassDefinition.MODEL.className?uncap_first} ${param.comment}  <br>
    </#list>
     * @return 新增的数据条数
     */
	int ${methodDescription.INSERT.methodName}(${javaClassDefinition.MODEL.className} ${javaClassDefinition.MODEL.className?uncap_first});
