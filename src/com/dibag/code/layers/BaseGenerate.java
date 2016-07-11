package com.dibag.code.layers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.dibag.code.config.BizType;
import com.dibag.code.config.Generate;
import com.dibag.code.entity.TableName;
import com.dibag.code.util.FreemarkerUtil;

/**
 * 基类生成器
 * @author quan
 *
 */
public class BaseGenerate {

	private List<TableName> tableNames;
	
	private Generate generate;
	
	private FreemarkerUtil freemarkerUtil;
	
	private Map<String, String> parmMap = new LinkedHashMap<String, String>();
	
	private Map<String, BizType> enumMap = new LinkedHashMap<String, BizType>();
	
	public BaseGenerate(Generate generate){
		this.generate = generate;
		this.freemarkerUtil = new FreemarkerUtil();
	}
	
	public List<TableName> getTableNames() {
		return tableNames;
	}

	public void setTableNames( List<TableName> tableNames ) {
		this.tableNames = tableNames;
	}
	
	public Map<String, String> getParmMap() {
		return parmMap;
	}

	public void setParmMap( Map<String, String> parmMap ) {
		this.parmMap = parmMap;
	}
	
	public Generate getGenerate() {
		return generate;
	}

	public FreemarkerUtil getFreemarkerUtil() {
		return freemarkerUtil;
	}

	public Map<String, BizType> getEnumMap() {
		return enumMap;
	}

	/**
	 * 添加
	 * @param key 模板文件名称
	 * @param value 生成后保存的路径   类似 "E:/workspace/freemarkprj/page"全路径
	 * @param bizType 业务枚举类型
	 */
	public void put(String key , String value , BizType bizType) {
	    parmMap.put( key, value );
	    enumMap.put( key, bizType );
    }

	/**
	 * 生成
	 */
	public void generateAllCode() {
	    for( Entry<String, String> entry : parmMap.entrySet() ) {
	        generateCode( entry.getKey(), entry.getValue() );
        }
    }
	
	/**
	 * 生成
	 * @param templateName 模板名称
	 * @param saveFilePath 模板生成后保存路径 类似 "E:/workspace/freemarkprj/page"全路径
	 */
	public void generateCode(String templateName,String saveFilePath){
		for( TableName tableName : tableNames ) {
			Map<String, Object> _paramMap = new LinkedHashMap<String, Object>();
			_paramMap.put( "table", tableName );
			_paramMap.put( "generateConfig", generate.getGenerateConfig() );
			String _fileName = getFullSaveFilePath( tableName, templateName , saveFilePath);
			if(_fileName != null){
				freemarkerUtil.fprint( templateName, _paramMap, saveFilePath.concat( "/" ).concat( _fileName ) );
			}
        }
	}
	
	private String getFullSaveFilePath(TableName tableName, String key , String saveFilePath) {
	    if(BizType.Controller == enumMap.get( key )){
	    	return tableName.getControllerName().concat( ".java" );
	    }
	    if(BizType.Entity == enumMap.get( key )){
	    	return tableName.getEntityName().concat( ".java" );
	    }
	    if(BizType.Mapper == enumMap.get( key )){
	    	return tableName.getMapperName().concat( ".java" );
	    }
	    if(BizType.MapperXML == enumMap.get( key )){
	    	return tableName.getMapperName().concat( ".xml" );
	    }
	    if(BizType.Page_list == enumMap.get( key )){
	    	generate.mkdir( saveFilePath.concat( "/" ).concat( tableName.getEntityNameFirst() ) );
	    	return tableName.getEntityNameFirst().concat( "/list.jsp" );
	    }
	    if(BizType.Page_edit == enumMap.get( key )){
	    	generate.mkdir( saveFilePath.concat( "/" ).concat( tableName.getEntityNameFirst() ) );
	    	return tableName.getEntityNameFirst().concat( "/edit.jsp" );
	    }
	    if(BizType.Page_create == enumMap.get( key )){
	    	generate.mkdir( saveFilePath.concat( "/" ).concat( tableName.getEntityNameFirst() ) );
	    	return tableName.getEntityNameFirst().concat( "/create.jsp" );
	    }
	    if(BizType.Service == enumMap.get( key )){
	    	return tableName.getServiceName().concat( ".java" );
	    }
	    if(BizType.Other == enumMap.get( key )){
	    	return extendFunc( tableName, key, saveFilePath );
	    }
	    return null;
    }
	
	/**
	 * 子类实现扩展
	 * @param tableName
	 * @param key
	 * @param saveFilePath
	 * @return
	 */
	public String extendFunc(TableName tableName, String key , String saveFilePath) {
	    return null;
    }
}
