package ${javaClassDefinition.MAPPER.fullPackage};

import java.util.List;

import ${javaClassDefinition.MODEL.fullPackage}.${javaClassDefinition.MODEL.className};

/**
 * ${tableInfo.tableComment!} dao layer interface <br/>
 *
 * @author  ${classCommentInfo.author} <br/>
 * @date    ${classCommentInfo.createTime} <br/>
 * @since   ${classCommentInfo.sinceVersion} <br/>
 */
public interface ${javaClassDefinition.MAPPER.className} {
<#if methodDescription.INSERT.enabled>

	<#include "common/insert.ftl"/>
</#if>
<#if methodDescription.DELETE_BY_PRIMARY_KEY.enabled>

	<#include "common/deleteByPrimaryKey.ftl"/>
</#if>
<#if methodDescription.DELETE_BY_WHERE.enabled>

	<#include "common/deleteByWhere.ftl"/>
</#if>
<#if methodDescription.UPDATE_BY_PRIMARY_KEY.enabled>

	<#include "common/updateByPrimaryKey.ftl"/>
</#if>
<#if methodDescription.QUERY_BY_PRIMARY_KEY.enabled>

    <#include "common/queryByPrimaryKey.ftl"/>
</#if>
<#if methodDescription.QUERY_BY_WHERE.enabled>

	<#include "common/queryByWhere.ftl"/>
</#if>
<#if methodDescription.COUNT_BY_WHERE.enabled>

	<#include "common/countByWhere.ftl"/>
</#if>
<#if methodDescription.BATCH_INSERT.enabled>

  <#include "common/batchInsert.ftl"/>
</#if>
}