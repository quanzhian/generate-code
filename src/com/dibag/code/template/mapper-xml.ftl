<?xml version="1.0" encoding="UTF-8" ?>  
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd"> 
<mapper namespace="${generateConfig.mapperPackage}.${table.mapperName}">
    <resultMap type="${generateConfig.entityPackage}.${table.entityName}" id="${table.entityNameFirst}Map">
    <#list table.tableFields as tableField>
    <result property="${tableField.entityField}" column="${tableField.columnName}"/>
    </#list>
    </resultMap>
    
    <!-- 条件  -->
   <sql id="Example_Where_Clause" >
    <trim prefix="where" prefixOverrides="and|or" >
    <#list table.tableFields as tableField>
    <if test="${tableField.entityField} != null and ${tableField.entityField} != ''" >
	and ${tableField.columnName} = ${r"#"}{${tableField.entityField}}
	</if>
	</#list>
    </trim>
  </sql>
  
  <!-- 数据库字段 -->
  <sql id="Base_Column_List" >
  <#list table.tableFields as tableField>
    ${tableField.columnName}<#if tableField_has_next>,</#if>
  </#list>
  </sql>
    
    <!-- 获取全部 -->
    <select id="getList" resultMap="${table.entityNameFirst}Map">
    SELECT 
	<include refid="Base_Column_List" /> 
	FROM ${table.name}
    </select>
    
    <!-- 根据条件获取符合条件的数据  -->
    <select id="getPageList" resultMap="${table.entityNameFirst}Map" parameterType="${generateConfig.entityPackage}.${table.entityName}">
    	SELECT 
		<include refid="Base_Column_List" /> 
		FROM ${table.name}
		<include refid="Example_Where_Clause" />
    </select>
    
    <!-- 根据条件获取符合条件的数据 -->
    <select id="getPageListByMap" resultMap="${table.entityNameFirst}Map" parameterType="map">
    	SELECT 
		<include refid="Base_Column_List" /> 
		FROM ${table.name}
		<include refid="Example_Where_Clause" />    	
    </select>    
    
    <!-- 根据ID获取数据 -->
    <select id="selectByPrimaryKey" parameterType="${table.primaryKeyType}" resultMap="${table.entityNameFirst}Map">
    	SELECT 
			<include refid="Base_Column_List" /> 
		FROM ${table.name} WHERE ${table.primaryKey} = ${r"#"}{${table.primaryKeyEntityName}}
    </select>
    
    <!-- 根据条件获取数据 -->
    <select id="selectBy" parameterType="${generateConfig.entityPackage}.${table.entityName}" resultMap="${table.entityNameFirst}Map">
    	SELECT 
			<include refid="Base_Column_List" /> 
		FROM ${table.name}
		<include refid="Example_Where_Clause" />
    </select>
    
    <!-- 插入一条数据 -->
    <insert id="create" parameterType="${generateConfig.entityPackage}.${table.entityName}" keyProperty="${table.primaryKeyEntityName}" useGeneratedKeys="true">
		INSERT INTO ${table.name}(
		<include refid="Base_Column_List" /> 
		)
		values (
		<#list table.tableFields as tableField>
		${r"#"}{${tableField.entityField}}<#if tableField_has_next>,</#if>
		</#list>
		)
    </insert>
    
    <!-- 插入有值的字段数据 -->
    <insert id="createSelective" parameterType="${generateConfig.entityPackage}.${table.entityName}" keyProperty="${table.primaryKeyEntityName}" useGeneratedKeys="true">
		INSERT INTO ${table.name}
		<trim prefix="(" suffix=")" suffixOverrides="," >
		<#list table.tableFields as tableField>
		<if test="${tableField.entityField} != null" >
		${tableField.columnName},
		</if>
		</#list>
		</trim>
		<trim prefix="values (" suffix=")" suffixOverrides="," >
		<#list table.tableFields as tableField>
		<if test="${tableField.entityField} != null" >
		${r"#"}{${tableField.entityField}},
		</if>
		</#list>
		</trim>				
    </insert>
    
    <!-- 批量插入有值的字段数据 -->
    <insert id="createSelectiveBatch" parameterType="java.util.List" keyProperty="${table.primaryKeyEntityName}" useGeneratedKeys="true">
		INSERT INTO ${table.name}
		<trim prefix="(" suffix=")" suffixOverrides="," >
		<#list table.tableFields as tableField>
		<if test="${tableField.entityField} != null" >
		${tableField.columnName},
		</if>
		</#list>
		</trim>
		values 
		<foreach collection="list" item="item" index="index" open="" close="" separator="">
		<trim prefix="(" suffix=")" suffixOverrides="," >
		<#list table.tableFields as tableField>
		<if test="${tableField.entityField} != null" >
		${r"#"}{item.${tableField.entityField}},
		</if>
		</#list>
		</trim>
		</foreach>
    </insert>    
    
    <!-- 根据ID更新数据 -->
    <update id="update" parameterType="${generateConfig.entityPackage}.${table.entityName}">
		UPDATE ${table.name}
		<set>
		<#list table.tableFields as tableField>
		${tableField.columnName} = ${r"#"}{${tableField.entityField}}<#if tableField_has_next>,</#if>
		</#list>
		</set>
		WHERE ${table.primaryKey} = ${r"#"}{${table.primaryKeyEntityName}}
    </update>
    
    <!-- 根据ID更新有值的字段数据 -->
    <update id="updateSelective" parameterType="${generateConfig.entityPackage}.${table.entityName}">
		UPDATE ${table.name}
		<set>
		<#list table.tableFields as tableField>
		<#if (table.primaryKey != tableField.columnName)>
		<if test="${tableField.entityField} != null" >
		${tableField.columnName} = ${r"#"}{${tableField.entityField}},
		</if>
		</#if>
		</#list>
		</set>
		WHERE ${table.primaryKey} = ${r"#"}{${table.primaryKeyEntityName}}
    </update>
    
    <!-- 根据ID批量更新数据 -->
    <update id="updateSelectiveBatch" parameterType="java.util.List">
    <foreach collection="list" item="item" index="index" open="" close="" separator=";">
		UPDATE ${table.name}
		<set>
		<#list table.tableFields as tableField>
		<#if (table.primaryKey != tableField.columnName)>
		<if test="item.${tableField.entityField} != null" >
		${tableField.columnName} = ${r"#"}{item.${tableField.entityField}},
		</if>
		</#if>
		</#list>
		</set>
		WHERE ${table.primaryKey} = ${r"#"}{${table.primaryKeyEntityName}}
	</foreach>
    </update>    
    
    <!-- 根据条件更新数据 -->
    <update id="updateByWhere" >
		UPDATE ${table.name}
		<set>
		<#list table.tableFields as tableField>
		<#if (table.primaryKey != tableField.columnName)>
		<if test="${tableField.entityField} != null" >
		${tableField.columnName} = ${r"#"}{item.${tableField.entityField}},
		</if>
		</#if>
		</#list>
		</set>
		<where>
		<#list table.tableFields as tableField>
		<if test="condition != null and ${tableField.entityField} != null" >
		    and ${tableField.columnName} = ${r"#"}{condition.${tableField.entityField}}
		</if>
		</#list>
		</where>
    </update>    
    
    <!-- 根据ID删除数据 -->
    <delete id="deleteByPrimaryKey" parameterType="${table.primaryKeyType}">
    	DELETE FROM  ${table.name} WHERE ${table.primaryKey} = ${r"#"}{${table.primaryKeyEntityName}}
    </delete>
    
    <!-- 根据条件删除数据 -->
    <delete id="deleteBy" parameterType="${generateConfig.entityPackage}.${table.entityName}">
    	DELETE FROM ${table.name} 
    	<trim prefix="where" prefixOverrides="and|or" >
    	<#list table.tableFields as tableField>
    	<if test="${tableField.entityField} != null and ${tableField.entityField} != ''" >
    	and ${tableField.columnName} = ${r"#"}{${tableField.entityField}}
    	</if>
    	</#list>
    	</trim>
    </delete>
    
    <!-- 根据条件批量删除数据 -->
    <delete id="deleteBatch" parameterType="java.util.List">
    <foreach collection="list" item="item" index="index" open="" close="" separator=";">
    	DELETE FROM ${table.name} 
    	<trim prefix="where" prefixOverrides="and|or" >
    	<#list table.tableFields as tableField>
    	<if test="${tableField.entityField} != null and ${tableField.entityField} != ''" >
    	and ${tableField.columnName} = ${r"#"}{item.${tableField.entityField}}
    	</if>
    	</#list>
    	</trim>
    </foreach>
    </delete>
</mapper>