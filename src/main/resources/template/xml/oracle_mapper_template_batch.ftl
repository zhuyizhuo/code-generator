<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${daoFullPackage}.${daoName}">

	<!-- resultMap定义  ${tableInfo.tableComment} ${tableInfo.dbTableName?upper_case}与实体对应关系 -->
	<resultMap id="${tableInfo.tableName}ResultMap" type="${javaBeanFullPackage}.${tableInfo.tableName}">
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

	<!-- 查询用 条件判断 -->
  	<sql id="condition_list">
	<#list tableInfo.javaColmBeans as colm>
	<#if colm??>
	<#if colm.typeName = 'String'>
		<if test="null != ${colm.javaName} and '' != ${colm.javaName}">
	<#else>
		<if test="null != ${colm.javaName}">
	</#if>
			AND ${colm.dbColmName?upper_case} = ${'#{'}${colm.javaName}}
		</if>
	</#if>
	</#list>
  	</sql>
  	
  	<!-- 基本多条件查询 -->
  	<sql id="base_query_by_where_sql">
		SELECT 
		<#list tableInfo.javaColmBeans as colm>
		<#if colm??>
			<#if colm_has_next>
			${colm.dbColmName?upper_case},
			<#else>
			${colm.dbColmName?upper_case}
			</#if>
		</#if>
		</#list>
		FROM 
			${tableInfo.tableSchema?upper_case}.${tableInfo.dbTableName?upper_case}
		WHERE
			1 = 1
		<include refid="condition_list" />
  	</sql>
  	
	<!-- 新增 -->
	<insert id="insert${tableInfo.tableName}" parameterType="${javaBeanFullPackage}.${tableInfo.tableName}" keyProperty="id"
		useGeneratedKeys="false">
		INSERT INTO ${tableInfo.tableSchema?upper_case}.${tableInfo.dbTableName?upper_case}(
		<trim suffixOverrides=",">
	    <#list tableInfo.javaColmBeans as colm>
		<#if colm??>
			<if test="null != ${colm.javaName}">${colm.dbColmName?upper_case},</if>
		</#if>
		</#list>
	    </trim>
		) VALUES (
		<trim suffixOverrides=",">
		<#list tableInfo.javaColmBeans as colm>
		<#if colm??>
			<if test="null != ${colm.javaName}">${'#{'}${colm.javaName},jdbcType=${colm.colmJdbcType}},</if>
		</#if>
		</#list>
		</trim>
		)
	</insert>

	<!-- 根据传入条件更新数据 -->
	<update id="update${tableInfo.tableName}" parameterType="${javaBeanFullPackage}.${tableInfo.tableName}">
		UPDATE ${tableInfo.tableSchema?upper_case}.${tableInfo.dbTableName?upper_case} 
		<trim prefix="set" suffixOverrides=",">
		<#list tableInfo.javaColmBeans as colm>
		<#if colm??>
		<#if colm_index != 0>
			<if test="null != ${colm.javaName}">${colm.dbColmName?upper_case} = ${'#{'}${colm.javaName}},</if>
		</#if>
		</#if>
		</#list>
		</trim>	
		WHERE ${tableInfo.javaColmBeans[0].dbColmName?upper_case} = ${'#{'}${tableInfo.javaColmBeans[0].javaName}} 
	</update>

	<!-- 删除数据  -->
	<delete id="delete${tableInfo.tableName}" parameterType="${javaBeanFullPackage}.${tableInfo.tableName}">
		<![CDATA[
			DELETE FROM ${tableInfo.tableSchema?upper_case}.${tableInfo.dbTableName?upper_case} WHERE ${tableInfo.javaColmBeans[0].dbColmName?upper_case} = ${'#{'}${tableInfo.javaColmBeans[0].javaName}}
		]]>
	</delete>

	<!-- 获取一条记录  -->
	<select id="getOne" resultMap="${tableInfo.tableName}ResultMap" parameterType="${javaBeanFullPackage}.${tableInfo.tableName}">
		<include refid="base_query_by_where_sql" />
	</select>
	
	<!-- 根据条件查询  -->
	<select id="query${tableInfo.tableName}ListByWhere" resultMap="${tableInfo.tableName}ResultMap" parameterType="${javaBeanFullPackage}.${tableInfo.tableName}">
		<include refid="base_query_by_where_sql" />
	</select>
	
	<!-- 查询总数  -->
	<select id="count${tableInfo.tableName}TotalByWhere" resultType="java.lang.Integer" parameterType="${javaBeanFullPackage}.${tableInfo.tableName}">
		SELECT 
		COUNT(1)
		FROM 
			${tableInfo.tableSchema?upper_case}.${tableInfo.dbTableName?upper_case}
		WHERE
			1 = 1
		<include refid="condition_list" />
	</select>

	<!-- 批量插入数据 -->
	<insert id="batchInsert${tableInfo.tableName}" parameterType="java.util.List">
		INSERT All
		<foreach collection="list" item="item">
			INTO ${tableInfo.tableSchema?upper_case}.${tableInfo.dbTableName?upper_case} (
			<trim suffixOverrides=",">
		    <#list tableInfo.javaColmBeans as colm>
			<#if colm??>
				<if test="null != item.${colm.javaName}">${colm.dbColmName?upper_case},</if>
			</#if>
			</#list>
		    </trim>
			 ) VALUES (
			<trim suffixOverrides=",">
			<#list tableInfo.javaColmBeans as colm>
			<#if colm??>
				<if test="null != item.${colm.javaName}">${r'#{item.'}${colm.javaName},jdbcType=${colm.colmJdbcType}},</if>
			</#if>
			</#list>
			</trim>
			)
		</foreach>
		SELECT 1 FROM DUAL
	</insert>
	
</mapper>
