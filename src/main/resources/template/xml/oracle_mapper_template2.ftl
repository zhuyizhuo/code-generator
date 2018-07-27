<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${daoFullPackage}.${daoName}">
	
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

	<sql id="Base_Column_List">
<#list tableInfo.javaColmBeans as colm>
	<#if colm??>
		<#if colm_has_next>
		${colm.dbColmName?upper_case},
		<#else>
		${colm.dbColmName?upper_case}
		</#if>
	</#if>
</#list>
  	</sql>
  
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
  	
  	<sql id="value_list">
<#list tableInfo.javaColmBeans as colm>
	<#if colm??>
		<#if colm_has_next>
		${'#{'}${colm.javaName},jdbcType=${colm.colmJdbcType}},
		<#else>
		${'#{'}${colm.javaName},jdbcType=${colm.colmJdbcType}}
		</#if>
	</#if>
</#list>
  	</sql>
  	
  	<sql id="base_query_by_where_sql">
		SELECT 
		<include refid="Base_Column_List" />
		FROM 
			${tableInfo.tableSchema?upper_case}.${tableInfo.dbTableName?upper_case}
		WHERE
			1 = 1
		<include refid="condition_list" />
  	</sql>
  	
	<!-- 新增 -->
	<insert id="insert${tableInfo.tableName}" parameterType="${javaBeanFullPackage}.${tableInfo.tableName}" keyProperty="id"
		useGeneratedKeys="true">
		INSERT INTO ${tableInfo.tableSchema?upper_case}.${tableInfo.dbTableName?upper_case}(
		<include refid="Base_Column_List" />
		) VALUES (
		<include refid="value_list" />
		)
	</insert>

	<!-- 根据传入条件更新数据 -->
	<update id="update${tableInfo.tableName}" parameterType="${javaBeanFullPackage}.${tableInfo.tableName}">
		UPDATE ${tableInfo.tableSchema?upper_case}.${tableInfo.dbTableName?upper_case} SET
		${tableInfo.javaColmBeans[0].dbColmName?upper_case} = ${tableInfo.javaColmBeans[0].dbColmName}
<#list tableInfo.javaColmBeans as colm>
	<#if colm??>
	<#if colm_index != 0>
		<if test="null != ${colm.javaName}">
			,${colm.dbColmName?upper_case} = ${'#{'}${colm.javaName}}
		</if>
	</#if>
	</#if>
</#list>		
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
	
	<!-- 根据条件分页查询  -->
	<select id="query${tableInfo.tableName}PagingListByWhere" resultMap="${tableInfo.tableName}ResultMap" parameterType="${javaBeanFullPackage}.${tableInfo.tableName}">
		SELECT * FROM (
			SELECT O.*,ROWNUM RN FROM (
			<include refid="base_query_by_where_sql" />
		<![CDATA[
		 	) O WHERE ROWNUM <= ${r'#{endNo}'}
		) T WHERE T.RN >= ${r'#{startNo}'}
		]]>
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
			 <include refid="Base_Column_List" />
			 ) VALUES (
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
		SELECT 1 FROM DUAL
	</insert>
	
</mapper>
