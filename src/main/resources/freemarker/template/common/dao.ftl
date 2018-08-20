package ${stratificationInfo.daoFullPackage};

import java.util.List;

import ${stratificationInfo.pojoFullPackage}.${stratificationInfo.pojoName};

/**
 * description : ${tableInfo.tableName} table dao layer interface <br/>
<#include "java/comment/classComment.ftl"/>
 */
public interface ${stratificationInfo.daoName} {
<#if methodInfo.insertMethodEnabled>

	<#include "java/insert.ftl"/>
</#if>
<#if methodInfo.deleteByPrimaryKeyMethodEnabled>
<#if tableInfo.hasPrimaryKey>

	<#include "java/deleteByPrimaryKey.ftl"/>
</#if>
</#if>
<#if methodInfo.deleteMethodEnabled>

	<#include "java/deleteByWhere.ftl"/>
</#if>
<#if methodInfo.updateByPrimaryKeyMethodEnabled>
<#if tableInfo.hasPrimaryKey>

	<#include "java/updateByPrimaryKey.ftl"/>
</#if>
</#if>
<#if methodInfo.queryByPrimaryKeyEnabled>
<#if tableInfo.hasPrimaryKey>

    <#include "java/queryByPrimaryKey.ftl"/>
</#if>
</#if>
<#if methodInfo.queryMethodEnabled>

	<#include "java/queryByWhere.ftl"/>
</#if>
<#if methodInfo.countMethodEnabled>

	<#include "java/countByWhere.ftl"/>
</#if>
}