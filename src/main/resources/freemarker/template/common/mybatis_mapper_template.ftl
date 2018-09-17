<#list mybatisXmlDefinition.mybatisHeader as header>
<#if header??>
${header}
</#if>
</#list>
<mapper namespace="${mybatisXmlDefinition.nameSpace}">

    <resultMap id="${mybatisXmlDefinition.resultMap.id}" type="${mybatisXmlDefinition.resultMap.type}">
<#list mybatisXmlDefinition.resultMap.results as result>
    <#if result??>
        <#if result.primaryKey>
        <id column="${result.column}" property="${result.property}"/>
        <#else>
        <result column="${result.column}" property="${result.property}"/>
        </#if>
    </#if>
</#list>
    </resultMap>

    <sql id="Where_Clause">
        <where>
<#list mybatisXmlDefinition.columns as colm>
    <#if colm??>
        <if test="${colm.testNotBlankExpression}">
            AND ${colm.columnName} = ${'#'}{${colm.javaColumnName}}
        </if>
    </#if>
</#list>
        </where>
    </sql>

    <sql id="Base_Column_List">
<#list mybatisXmlDefinition.columns as colm>
    <#if colm??>
        <#if colm_has_next>
            ${colm.columnName},
        <#else>
            ${colm.columnName}
        </#if>
    </#if>
</#list>
    </sql>

    <sql id="Table_Name">
    ${mybatisXmlDefinition.tableName}
    </sql>

<#if methodInfo.insertMethodEnabled>

<#include "xml/insert.ftl"/>
</#if>
<#if methodInfo.deleteByPrimaryKeyMethodEnabled>
<#if tableInfo.hasPrimaryKey>

<#include "xml/deleteByPrimaryKey.ftl"/>
</#if>
</#if>
<#if methodInfo.deleteMethodEnabled>

<#include "xml/deleteByWhere.ftl"/>
</#if>
<#if methodInfo.updateByPrimaryKeyMethodEnabled>
<#if tableInfo.hasPrimaryKey>

    <#include "xml/updateByPrimaryKey.ftl"/>
</#if>
</#if>
<#if methodInfo.queryByPrimaryKeyEnabled>
<#if tableInfo.hasPrimaryKey>

    <#include "xml/queryByPrimaryKey.ftl"/>
</#if>
</#if>
<#if methodInfo.queryMethodEnabled>

<#include "xml/queryByWhere.ftl"/>
</#if>
<#if methodInfo.countMethodEnabled>

<#include "xml/countByWhere.ftl"/>
</#if>
</mapper>