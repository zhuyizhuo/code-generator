    <resultMap id="${mybatisXmlDefinition.resultMap.id}" type="${mybatisXmlDefinition.resultMap.type}">
<#list mybatisXmlDefinition.columns as result>
    <#if result??>
        <#if result.primaryKey>
        <id column="${result.column}" property="${result.property}"/>
        <#else>
        <result column="${result.column}" property="${result.property}"/>
        </#if>
    </#if>
</#list>
    </resultMap>
