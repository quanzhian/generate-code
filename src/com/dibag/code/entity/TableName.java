package com.dibag.code.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 表信息类
 * @author quan
 *
 */
public class TableName {

	/**
	 * 表名称
	 */
	private String name;

	/**
	 * 实体类名称
	 */
	private String entityName;
	
	/**
	 * 实体类名称首字母小写
	 */
	private String entityNameFirst;

	/**
	 * 注释
	 */
	private String entityComment;
	
	/**
	 * 服务类名称
	 */
	private String serviceName;
	
	/**
	 * 控制器名称
	 */
	private String controllerName;
	
	/**
	 * mapper名称
	 */
	private String mapperName;
	
	/**
	 * 主键
	 */
	private String primaryKey;
	
	/**
	 * 主键的实体字段名称
	 */
	private String primaryKeyEntityName;
	
	/**
	 * 主键的实体字段名称首字母大写
	 */
	private String primaryKeyEntityNameFirst;
	
	/**
	 * 主键类型
	 */
	private String primaryKeyType;
	

	/**
	 * 表和实体对应的字段集合
	 */
	private List<TableField> tableFields = new ArrayList<TableField>();

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName( String entityName ) {
		this.entityName = entityName;
		this.controllerName = entityName.concat( "AdminController" );
		this.serviceName = entityName.concat( "Service" );
		this.mapperName = entityName.concat( "Mapper" );
	}

	public String getEntityComment() {
		return entityComment;
	}

	public void setEntityComment( String entityComment ) {
		this.entityComment = entityComment == null || entityComment.isEmpty() ? " " : entityComment;
	}

	public List<TableField> getTableFields() {
		return tableFields;
	}

	public void setTableFields( List<TableField> tableFields ) {
		this.tableFields = tableFields;
	}

	public String getEntityNameFirst() {
		return entityNameFirst;
	}

	public void setEntityNameFirst( String entityNameFirst ) {
		this.entityNameFirst = entityNameFirst;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName( String serviceName ) {
		this.serviceName = serviceName;
	}

	public String getControllerName() {
		return controllerName;
	}

	public void setControllerName( String controllerName ) {
		this.controllerName = controllerName;
	}

	public String getMapperName() {
		return mapperName;
	}

	public void setMapperName( String mapperName ) {
		this.mapperName = mapperName;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey( String primaryKey ) {
		this.primaryKey = primaryKey;
	}

	public String getPrimaryKeyType() {
		return primaryKeyType;
	}

	public void setPrimaryKeyType( String primaryKeyType ) {
		this.primaryKeyType = primaryKeyType;
	}

	public String getPrimaryKeyEntityName() {
		return primaryKeyEntityName;
	}

	public void setPrimaryKeyEntityName( String primaryKeyEntityName ) {
		this.primaryKeyEntityName = primaryKeyEntityName;
	}

	public String getPrimaryKeyEntityNameFirst() {
		return primaryKeyEntityNameFirst;
	}

	public void setPrimaryKeyEntityNameFirst( String primaryKeyEntityNameFirst ) {
		this.primaryKeyEntityNameFirst = primaryKeyEntityNameFirst;
	}
	
}
