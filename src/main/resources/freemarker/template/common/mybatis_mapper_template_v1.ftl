<#list mybatisXmlDefinition.mybatisHeader as header>
<#if header??>
${header}
</#if>
</#list>
<mapper namespace="${mybatisXmlDefinition.nameSpace}">

<#include "xml/resultMap.ftl"/>

<#include "xml/commonSql.ftl"/>
<#if methodInfo.insertMethodEnabled>

<#include "xml/insert.ftl"/>
</#if>
<#if methodInfo.deleteByPrimaryKeyMethodEnabled && tableInfo.hasPrimaryKey>

<#include "xml/deleteByPrimaryKey.ftl"/>
</#if>
<#if methodInfo.deleteMethodEnabled>

<#include "xml/deleteByWhere.ftl"/>
</#if>
<#if methodInfo.updateByPrimaryKeyMethodEnabled && tableInfo.hasPrimaryKey>

    <#include "xml/updateByPrimaryKey.ftl"/>
</#if>
<#if methodInfo.queryByPrimaryKeyEnabled && tableInfo.hasPrimaryKey>

    <#include "xml/queryByPrimaryKey.ftl"/>
</#if>
<#if methodInfo.queryMethodEnabled>

<#include "xml/queryByWhere.ftl"/>
</#if>
<#if methodInfo.countMethodEnabled>

<#include "xml/countByWhere.ftl"/>
</#if>
</mapper>