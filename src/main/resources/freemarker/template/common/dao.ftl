package ${stratificationInfo.daoFullPackage};

import java.util.List;

import ${stratificationInfo.pojoFullPackage}.${stratificationInfo.pojoName};

/**
 * description : ${tableInfo.tableName} table dao layer interface <br/>
<#include "java/comment/comment.ftl"/>
 */
public interface ${stratificationInfo.daoName} {

	<#include "java/insert.ftl"/>
<#if tableInfo.hasPrimaryKey>

	<#include "java/deleteByPrimaryKey.ftl"/>
</#if>

	<#include "java/deleteByWhere.ftl"/>
<#if tableInfo.hasPrimaryKey>

	<#include "java/updateByPrimaryKey.ftl"/>
</#if>

	<#include "java/updateByWhere.ftl"/>
<#if tableInfo.hasPrimaryKey>

    <#include "java/queryByPrimaryKey.ftl"/>
</#if>

	<#include "java/queryByWhere.ftl"/>


	<#include "java/countByWhere.ftl"/>

}