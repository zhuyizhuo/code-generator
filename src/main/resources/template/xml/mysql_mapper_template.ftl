<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${daoFullPackage}.${daoName}">
	<!-- 新增 -->
	<insert id="insert${tableInfo.tableName}" parameterType="${javaBeanFullPackage}.${tableInfo.tableName}" keyProperty="id"
		useGeneratedKeys="true">
		<![CDATA[
			INSERT INTO ${tableInfo.tableSchema?upper_case}.${tableInfo.dbTableName?upper_case}(
			<#list tableInfo.javaColmBeans as colm>
			<#if colm??>
				<#if colm_has_next>
				${colm.dbColmName?upper_case},
				<#else>
				${colm.dbColmName?upper_case}
				</#if>
			</#if>
			</#list>
			) VALUES (
			<#list tableInfo.javaColmBeans as colm>
			<#if colm??>
				<#if colm_has_next>
				${'#{'}${colm.javaName},jdbcType=${colm.colmJdbcType}},
				<#else>
				${'#{'}${colm.javaName},jdbcType=${colm.colmJdbcType}}
				</#if>
			</#if>
			</#list>
			)
		]]>
	</insert>

	<!-- 根据传入条件更新数据 -->
	<update id="update${tableInfo.tableName}" parameterType="${javaBeanFullPackage}.${tableInfo.tableName}">
		<![CDATA[
			UPDATE ${tableInfo.tableSchema?upper_case}.${tableInfo.dbTableName?upper_case} SET
			${tableInfo.javaColmBeans[0].dbColmName?upper_case} = ${tableInfo.javaColmBeans[0].dbColmName}
		]]>
<#list tableInfo.javaColmBeans as colm>
	<#if colm??>
	<#if colm_index != 0>
		<if test="null != ${colm.javaName}">
		<![CDATA[
			,${colm.dbColmName?upper_case} = ${'#{'}${colm.javaName}}
		]]>
		</if>
	</#if>
	</#if>
</#list>		
		<![CDATA[
			WHERE ${tableInfo.javaColmBeans[0].dbColmName?upper_case} = ${'#{'}${tableInfo.javaColmBeans[0].javaName}} 
		]]>
	</update>

	<!-- 删除数据  -->
	<delete id="delete${tableInfo.tableName}" parameterType="${javaBeanFullPackage}.${tableInfo.tableName}">
		<![CDATA[
			DELETE FROM ${tableInfo.tableSchema?upper_case}.${tableInfo.dbTableName?upper_case} WHERE ${tableInfo.javaColmBeans[0].dbColmName?upper_case} = ${'#{'}${tableInfo.javaColmBeans[0].javaName}}
		]]>
	</delete>

	<!-- 根据条件查询  -->
	<select id="query${tableInfo.tableName}ListByWhere" resultType="${javaBeanFullPackage}.${tableInfo.tableName}" parameterType="${javaBeanFullPackage}.${tableInfo.tableName}">
		<![CDATA[
			SELECT 
			<#list tableInfo.javaColmBeans as colm>
			<#if colm??>
				<#if colm_has_next>
				${colm.dbColmName?upper_case} AS ${colm.javaName},
				<#else>
				${colm.dbColmName?upper_case} AS ${colm.javaName}
				</#if>
			</#if>
			</#list>
			FROM 
				${tableInfo.tableSchema?upper_case}.${tableInfo.dbTableName?upper_case}
			WHERE
				1 = 1
		]]>
<#list tableInfo.javaColmBeans as colm>
<#if colm??>
	<#if colm.typeName = 'String'>
		<if test="null != ${colm.javaName} and '' != ${colm.javaName}">
	<#else>
		<if test="null != ${colm.javaName}">
	</#if>
		<![CDATA[
			AND ${colm.dbColmName?upper_case} = ${'#{'}${colm.javaName}}
		]]>
		</if>
</#if>
</#list>
		<!--limit ${r'#{startNo}'}, ${r'#{endNo}'}-->
	</select>
	
		<!-- 获取一条记录  -->
	<select id="getOne" resultType="${javaBeanFullPackage}.${tableInfo.tableName}" parameterType="${javaBeanFullPackage}.${tableInfo.tableName}">
		<![CDATA[
			SELECT 
			<#list tableInfo.javaColmBeans as colm>
			<#if colm??>
				<#if colm_has_next>
				${colm.dbColmName?upper_case} AS ${colm.javaName},
				<#else>
				${colm.dbColmName?upper_case} AS ${colm.javaName}
				</#if>
			</#if>
			</#list>
			FROM 
				${tableInfo.tableSchema?upper_case}.${tableInfo.dbTableName?upper_case}
			WHERE
				1 = 1
		]]>
<#list tableInfo.javaColmBeans as colm>
<#if colm??>
	<#if colm.typeName = 'String'>
		<if test="null != ${colm.javaName} and '' != ${colm.javaName}">
	<#else>
		<if test="null != ${colm.javaName}">
	</#if>
		<![CDATA[
			AND ${colm.dbColmName?upper_case} = ${'#{'}${colm.javaName}}
		]]>
		</if>
</#if>
</#list>
	</select>
	
	<!-- 查询总数  -->
	<select id="count${tableInfo.tableName}TotalByWhere" resultType="java.lang.Integer" parameterType="${javaBeanFullPackage}.${tableInfo.tableName}">
		<![CDATA[
			SELECT 
			COUNT(1)
			FROM 
				${tableInfo.tableSchema?upper_case}.${tableInfo.dbTableName?upper_case}
			WHERE
				1 = 1
		]]>
<#list tableInfo.javaColmBeans as colm>
<#if colm??>
	<#if colm.typeName = 'String'>
		<if test="null != ${colm.javaName} and '' != ${colm.javaName}">
	<#else>
		<if test="null != ${colm.javaName}">
	</#if>
		<![CDATA[
			AND ${colm.dbColmName?upper_case} = ${'#{'}${colm.javaName}}
		]]>
		</if>
</#if>
</#list>
	</select>


	<!-- 批量插入数据 -->
	<insert id="batchInsert${tableInfo.tableName}" parameterType="java.util.List">
		<![CDATA[
		INSERT INTO ${tableInfo.tableSchema?upper_case}.${tableInfo.dbTableName?upper_case} (
		 <#list tableInfo.javaColmBeans as colm>
		<#if colm??>
			<#if colm_has_next>
			${colm.dbColmName?upper_case},
			<#else>
			${colm.dbColmName?upper_case}
			</#if>
		</#if>
		</#list>
		)VALUES
		]]>
		<foreach collection="list" item="item" separator =",">
		( 
		<#list tableInfo.javaColmBeans as colm>
		<#if colm??>
			<#if colm_has_next>
			${r'#{item.'}${colm.javaName},jdbcType=${colm.colmJdbcType}},
			<#else>
			${r'#{item.'}${colm.javaName},jdbcType=${colm.colmJdbcType}}
			</#if>
		</#if>
		</#list>
		)
		</foreach>
	</insert>
	
</mapper>
