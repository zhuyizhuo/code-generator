<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${mybatisXmlDefinition.nameSpace}">

    <resultMap id="${mybatisXmlDefinition.resultMap.id}" type="${mybatisXmlDefinition.resultMap.type}">
<#list mybatisXmlDefinition.columns as colm>
    <#if colm??>
        <#if colm.primaryKey>
        <id column="${colm.columnName}" property="${colm.javaColumnName}"/>
        <#else>
        <result column="${colm.columnName}" property="${colm.javaColumnName}"/>
        </#if>
    </#if>
</#list>
    </resultMap>

</mapper>