<#list mybatisXmlDefinition.mybatisHeader as colm>
<#if colm??>
${colm}
</#if>
</#list>
<mapper namespace="${mybatisXmlDefinition.nameSpace}">

<#include "xml/resultMap.ftl"/>

<#include "xml/commonSql.ftl"/>
<#if methodInfo.insertMethodEnabled>

<#include "xml/insert.ftl"/>
</#if>
<#if methodInfo.deleteByPrimaryKeyMethodEnabled>
<#if tableInfo.hasPrimaryKey>

<#include "xml/deleteByPrimaryKey.ftl"/>
</#if>
</#if>
<#if methodInfo.deleteMethodEnabled>

<#include "xml/deleteByWhere.ftl"/>
</#if>
<#if methodInfo.updateByPrimaryKeyMethodEnabled>
<#if tableInfo.hasPrimaryKey>

    <#include "xml/updateByPrimaryKey.ftl"/>
</#if>
</#if>
<#if methodInfo.queryByPrimaryKeyEnabled>
<#if tableInfo.hasPrimaryKey>

    <#include "xml/queryByPrimaryKey.ftl"/>
</#if>
</#if>
<#if methodInfo.queryMethodEnabled>

<#include "xml/queryByWhere.ftl"/>
</#if>
<#if methodInfo.countMethodEnabled>

<#include "xml/countByWhere.ftl"/>
</#if>
</mapper>