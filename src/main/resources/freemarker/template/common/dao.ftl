package ${stratificationInfo.daoFullPackage};

import java.util.List;

import ${stratificationInfo.pojoFullPackage}.${stratificationInfo.pojoName};

/**
 * description : ${tableInfo.tableName} table dao layer interface <br/>
<#include "java/comment/classComment.ftl"/>
 */
public interface ${stratificationInfo.daoName} {
<#if methodInfo.methodDescription.INSERT.enabled>

	<#include "java/insert.ftl"/>
</#if>
<#if methodInfo.methodDescription.DELETE_BY_PRIMARY_KEY.enabled>
<#if tableInfo.hasPrimaryKey>

	<#include "java/deleteByPrimaryKey.ftl"/>
</#if>
</#if>
<#if methodInfo.methodDescription.DELETE_BY_WHERE.enabled>

	<#include "java/deleteByWhere.ftl"/>
</#if>
<#if methodInfo.methodDescription.UPDATE_BY_PRIMARY_KEY.enabled>
<#if tableInfo.hasPrimaryKey>

	<#include "java/updateByPrimaryKey.ftl"/>
</#if>
</#if>
<#if methodInfo.methodDescription.QUERY_BY_PRIMARY_KEY.enabled>
<#if tableInfo.hasPrimaryKey>

    <#include "java/queryByPrimaryKey.ftl"/>
</#if>
</#if>
<#if methodInfo.methodDescription.QUERY_BY_WHERE.enabled>

	<#include "java/queryByWhere.ftl"/>
</#if>
<#if methodInfo.methodDescription.COUNT_BY_WHERE.enabled>

	<#include "java/countByWhere.ftl"/>
</#if>
<#if methodInfo.methodDescription.BATCH_INSERT.enabled>

  <#include "java/batchInsert.ftl"/>
</#if>
}