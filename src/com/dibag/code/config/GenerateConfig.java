package com.dibag.code.config;

/**
 * 配置
 * @author quan
 *
 */
public class GenerateConfig {

	/**
	 * DB驱动
	 */
	private String jdbcDriver = "com.mysql.jdbc.Driver";
	/**
	 * DB连接字符串
	 */
	private String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/dbg_cms?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&amp;autoReconnectForPools=true";
	/**
	 * DB账号
	 */
	private String jdbcUserName = "root";
	/**
	 * DB密码
	 */
	private String jdbcPassword = "root";
	
	/**
	 * 父级包
	 */
	private String basePackage;

	/**
	 * 实体包
	 */
	private String entityPackage;

	/**
	 * mapper包
	 */
	private String mapperPackage;

	/**
	 * 服务包
	 */
	private String servicePackage;
	
	/**
	 * 控制器包
	 */
	private String controllerPackage;

	/**
	 * 公共包
	 */
	private String commonPackage;
	
	/**
	 * 模板基础路径
	 */
	private String templateBasePath;

	/**
	 * 生成的文件存储路径
	 */
	private String saveFilePath = "F:/lins/test";

	/**
	 * 限制只能生成指定的表,多个以","分割
	 */
	private String tables = "";

	/**
	 * 项目名称
	 */
	private String projectName = "";

	/**
	 * 生成指定的模块 generate specific layers(selectable value:
	 * mapper,mapperxml,service,controller,entity,jsp,util,shiro,config,api),
	 * split with comma(,).
	 */
	private String layers = "mapper,mapperxml,service,controller,entity,jsp,util,shiro,config,api";

	public String getJdbcDriver() {
		return jdbcDriver;
	}

	public void setJdbcDriver( String jdbcDriver ) {
		this.jdbcDriver = jdbcDriver;
	}

	public String getJdbcUrl() {
		return jdbcUrl;
	}

	public void setJdbcUrl( String jdbcUrl ) {
		this.jdbcUrl = jdbcUrl;
	}

	public String getJdbcUserName() {
		return jdbcUserName;
	}

	public void setJdbcUserName( String jdbcUserName ) {
		this.jdbcUserName = jdbcUserName;
	}

	public String getJdbcPassword() {
		return jdbcPassword;
	}

	public void setJdbcPassword( String jdbcPassword ) {
		this.jdbcPassword = jdbcPassword;
	}

	public String getBasePackage() {
		return basePackage;
	}

	public void setBasePackage( String basePackage ) {
		this.basePackage = basePackage;
	}

	public String getEntityPackage() {
		return entityPackage;
	}

	public void setEntityPackage( String entityPackage ) {
		this.entityPackage = entityPackage;
	}

	public String getMapperPackage() {
		return mapperPackage;
	}

	public void setMapperPackage( String mapperPackage ) {
		this.mapperPackage = mapperPackage;
	}

	public String getServicePackage() {
		return servicePackage;
	}

	public void setServicePackage( String servicePackage ) {
		this.servicePackage = servicePackage;
	}

	public String getCommonPackage() {
		return commonPackage;
	}

	public void setCommonPackage( String commonPackage ) {
		this.commonPackage = commonPackage;
	}

	public String getTemplateBasePath() {
		return templateBasePath;
	}

	public void setTemplateBasePath( String templateBasePath ) {
		this.templateBasePath = templateBasePath;
	}

	public String getSaveFilePath() {
		return saveFilePath;
	}

	public void setSaveFilePath( String saveFilePath ) {
		this.saveFilePath = saveFilePath;
	}

	public String getTables() {
		return tables;
	}

	public void setTables( String tables ) {
		this.tables = tables;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName( String projectName ) {
		this.projectName = projectName;
	}

	public String getLayers() {
		if(layers==null||"".equals(layers)){
			layers = "mapper,mapperxml,service,controller,entity,jsp,util,shiro,config,api";
		}
		return layers;
	}

	public void setLayers( String layers ) {
		this.layers = layers;
	}

	public String getControllerPackage() {
		return controllerPackage;
	}

	public void setControllerPackage( String controllerPackage ) {
		this.controllerPackage = controllerPackage;
	}
	
}
