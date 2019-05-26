<#list mybatisXmlDefinition.mybatisHeader as header>
<#if header??>
${header}
</#if>
</#list>
<mapper namespace="${mybatisXmlDefinition.nameSpace}">

<#include "xml/resultMap.ftl"/>

<#include "xml/commonSql.ftl"/>
<#if methodDescription.INSERT.enabled>

<#include "xml/insert.ftl"/>
</#if>
<#if methodDescription.DELETE_BY_WHERE.enabled>

<#include "xml/deleteByWhere.ftl"/>
</#if>
<#if methodDescription.QUERY_BY_WHERE.enabled>

<#include "xml/queryByWhere.ftl"/>
</#if>
<#if methodDescription.COUNT_BY_WHERE.enabled>

<#include "xml/countByWhere.ftl"/>
</#if>
<#if methodDescription.BATCH_INSERT.enabled>

    <#include "xml/batchInsert.ftl"/>
</#if>
</mapper>