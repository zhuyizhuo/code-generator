<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${mybatisXmlDefinition.nameSpace}">

<#include "xml/resultMap.ftl"/>

<#include "xml/commonSql.ftl"/>
<#if methodDescription.INSERT.enabled>

<#include "xml/insert.ftl"/>
</#if>
<#if methodDescription.DELETE_BY_PRIMARY_KEY.enabled>

<#include "xml/deleteByPrimaryKey.ftl"/>
</#if>
<#if methodDescription.DELETE_BY_WHERE.enabled>

<#include "xml/deleteByWhere.ftl"/>
</#if>
<#if methodDescription.UPDATE_BY_PRIMARY_KEY.enabled>

    <#include "xml/updateByPrimaryKey.ftl"/>
</#if>
<#if methodDescription.QUERY_BY_PRIMARY_KEY.enabled>

    <#include "xml/queryByPrimaryKey.ftl"/>
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