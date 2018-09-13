    <resultMap id="${mybatisXmlDefinition.resultMap.resultMapId}" type="${mybatisXmlDefinition.resultMap.type}">
<#list mybatisXmlDefinition.resultMap.results as result>
    <#if result??>
        <#if result.primaryKey>
        <id column="${result.column}" property="${result.property}"/>
        <#else>
        <result column="${result.column}" property="${result.property}"/>
        </#if>
    </#if>
</#list>
    </resultMap>
