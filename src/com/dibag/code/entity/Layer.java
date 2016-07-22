package com.dibag.code.entity;

public class Layer {

	/**
	 * 模板名称
	 */
	private String templateName;
	
	/**
	 * 文件目录
	 */
	private String dir;

	/**
	 * 文件保存完整路径
	 */
	private String saveFilePath;
	
	/**
	 * 后缀
	 */
	private String prefix;

	public Layer(){
		
	}
	
	public Layer(String templateName,String dir,String prefix){
		this.templateName = templateName;
		this.dir = dir;
		this.prefix = prefix;
	}
	
	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName( String templateName ) {
		this.templateName = templateName;
	}

	public String getSaveFilePath() {
		return saveFilePath;
	}

	public void setSaveFilePath( String saveFilePath ) {
		this.saveFilePath = saveFilePath;
	}

	public String getDir() {
		return dir;
	}

	public void setDir( String dir ) {
		this.dir = dir;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix( String prefix ) {
		this.prefix = prefix;
	}

	
	
}
