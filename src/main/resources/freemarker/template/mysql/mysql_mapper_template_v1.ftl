<#include "base/xml/mybatis3XmlHeader.ftl"/>
<mapper namespace="${stratificationInfo.daoFullPackage}.${stratificationInfo.daoName}">

<#include "base/xml/resultMap.ftl"/>

<#include "base/xml/insert.ftl"/>

<#include "base/xml/delete.ftl"/>

<#include "base/xml/update.ftl"/>

<#include "base/xml/query.ftl"/>

</mapper>