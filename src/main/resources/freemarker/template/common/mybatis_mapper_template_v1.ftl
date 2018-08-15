<#include "xml/mybatis3XmlHeader.ftl"/>
<mapper namespace="${stratificationInfo.daoFullPackage}.${stratificationInfo.daoName}">

<#include "xml/resultMap.ftl"/>

<#include "xml/commonSql.ftl"/>

<#include "xml/insert.ftl"/>

<#if tableInfo.hasPrimaryKey>
<#include "xml/delete.ftl"/>

<#include "xml/update.ftl"/>
</#if>

<#include "xml/query.ftl"/>

</mapper>