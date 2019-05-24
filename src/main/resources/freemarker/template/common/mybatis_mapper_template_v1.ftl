<#list mybatisXmlDefinition.mybatisHeader as header>
<#if header??>
${header}
</#if>
</#list>
<mapper namespace="${mybatisXmlDefinition.nameSpace}">

<#include "xml/resultMap.ftl"/>

<#include "xml/commonSql.ftl"/>
<#if methodInfo.methodDescription.INSERT.enabled>

<#include "xml/insert.ftl"/>
</#if>
<#if methodInfo.methodDescription.DELETE_BY_PRIMARY_KEY.enabled && tableInfo.hasPrimaryKey>

<#include "xml/deleteByPrimaryKey.ftl"/>
</#if>
<#if methodInfo.methodDescription.DELETE_BY_WHERE.enabled>

<#include "xml/deleteByWhere.ftl"/>
</#if>
<#if methodInfo.methodDescription.UPDATE_BY_PRIMARY_KEY.enabled && tableInfo.hasPrimaryKey>

    <#include "xml/updateByPrimaryKey.ftl"/>
</#if>
<#if methodInfo.methodDescription.QUERY_BY_PRIMARY_KEY.enabled && tableInfo.hasPrimaryKey>

    <#include "xml/queryByPrimaryKey.ftl"/>
</#if>
<#if methodInfo.methodDescription.QUERY_BY_WHERE.enabled>

<#include "xml/queryByWhere.ftl"/>
</#if>
<#if methodInfo.methodDescription.COUNT_BY_WHERE.enabled>

<#include "xml/countByWhere.ftl"/>
</#if>
<#if methodInfo.methodDescription.BATCH_INSERT.enabled>

    <#include "xml/batchInsert.ftl"/>
</#if>
</mapper>