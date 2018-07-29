<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${daoFullPackage}.${daoName}">
	
	<resultMap type="${javaBeanFullPackage}.${tableInfo.tableName}" id="${tableInfo.tableName}ResultMap">
<#list tableInfo.javaColmBeans as colm>
	<#if colm??>
		<#if colm_index = 0>
		<id column="${colm.dbColmName?upper_case}" property="${colm.javaName}"/>
		<#else>
		<result column="${colm.dbColmName?upper_case}" property="${colm.javaName}"/>
		</#if>
	</#if>
</#list>
   </resultMap>

</mapper>
