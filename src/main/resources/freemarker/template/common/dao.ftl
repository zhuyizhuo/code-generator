package ${javaClassDefinition.MAPPER.fullPackage};

import java.util.List;

import ${javaClassDefinition.POJO.fullPackage}.${javaClassDefinition.POJO.className};

/**
 * description : ${tableInfo.tableName} table dao layer interface <br/>
 * time:    ${classCommentInfo.createTime} <br/><#if classCommentInfo.author!="">
 * @author  ${classCommentInfo.author} <br/></#if><#if classCommentInfo.sinceVersion!="">
 * @since   ${classCommentInfo.sinceVersion} <br/></#if>
 * @version ${classCommentInfo.version} <br/>
 */
public interface ${javaClassDefinition.MAPPER.className} {
<#if methodDescription.INSERT.enabled>

	<#include "java/insert.ftl"/>
</#if>
<#if methodDescription.DELETE_BY_PRIMARY_KEY.enabled>
<#if tableInfo.hasPrimaryKey>

	<#include "java/deleteByPrimaryKey.ftl"/>
</#if>
</#if>
<#if methodDescription.DELETE_BY_WHERE.enabled>

	<#include "java/deleteByWhere.ftl"/>
</#if>
<#if methodDescription.UPDATE_BY_PRIMARY_KEY.enabled>
<#if tableInfo.hasPrimaryKey>

	<#include "java/updateByPrimaryKey.ftl"/>
</#if>
</#if>
<#if methodDescription.QUERY_BY_PRIMARY_KEY.enabled>
<#if tableInfo.hasPrimaryKey>

    <#include "java/queryByPrimaryKey.ftl"/>
</#if>
</#if>
<#if methodDescription.QUERY_BY_WHERE.enabled>

	<#include "java/queryByWhere.ftl"/>
</#if>
<#if methodDescription.COUNT_BY_WHERE.enabled>

	<#include "java/countByWhere.ftl"/>
</#if>
<#if methodDescription.BATCH_INSERT.enabled>

  <#include "java/batchInsert.ftl"/>
</#if>
}