<#include "xml/mybatis/mybatis3XmlHeader.ftl"/>
<mapper namespace="${stratificationInfo.daoFullPackage}.${stratificationInfo.daoName}">

<#include "xml/resultMap.ftl"/>

<#include "xml/commonSql.ftl"/>

<#include "xml/insert.ftl"/>
<#if tableInfo.hasPrimaryKey>

<#include "xml/deleteByPrimaryKey.ftl"/>
</#if>

<#include "xml/deleteByWhere.ftl"/>
<#if tableInfo.hasPrimaryKey>

    <#include "xml/updateByPrimaryKey.ftl"/>
</#if>

<#include "xml/updateByWhere.ftl"/>
<#if tableInfo.hasPrimaryKey>

    <#include "xml/queryByPrimaryKey.ftl"/>
</#if>

<#include "xml/queryByWhere.ftl"/>

<#include "xml/countByWhere.ftl"/>

</mapper>