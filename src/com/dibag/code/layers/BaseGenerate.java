package com.dibag.code.layers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.dibag.code.config.Generate;
import com.dibag.code.entity.Layer;
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
	
	private FreemarkerUtil freemarkerUtil = new FreemarkerUtil();
	
	public BaseGenerate() {
	    
    }
	
	public BaseGenerate(Generate generate){
		this.generate = generate;
	}
	
	public List<TableName> getTableNames() {
		return tableNames;
	}

	public void setTableNames( List<TableName> tableNames ) {
		this.tableNames = tableNames;
	}
	
	public Generate getGenerate() {
		return generate;
	}
	
	public void setGenerate( Generate generate ) {
		this.generate = generate;
	}

	public FreemarkerUtil getFreemarkerUtil() {
		return freemarkerUtil;
	}
	
	/**
	 * 生成
	 */
	public void generateCode(){
		List<Layer> layers = generate.getGenerateConfig().getLayers();
		for( TableName tableName : tableNames ) {
			for( Layer layer : layers ) {
				Map<String, Object> _paramMap = new LinkedHashMap<String, Object>();
				_paramMap.put( "table", tableName );
				_paramMap.put( "generateConfig", generate.getGenerateConfig() );
				String _fileName = getFileName(tableName,layer);
				if(_fileName != null){
					String _savePath = layer.getDir().concat( "/" ).concat( _fileName ).concat( layer.getPrefix() );
					freemarkerUtil.fprint( layer.getTemplateName(), _paramMap, _savePath );
				}
            }
        }
	}
	
	/**
	 * 获取文件名称
	 * @param tableName
	 * @param layer
	 * @return
	 */
	protected String getFileName(TableName tableName,Layer layer) {
	    if(layer.getTemplateName().equalsIgnoreCase( "controller.ftl" )){
	    	return tableName.getControllerName();
	    }
	    if(layer.getTemplateName().equalsIgnoreCase( "entity.ftl" )){
	    	return tableName.getEntityName();
	    }
	    if(layer.getTemplateName().equalsIgnoreCase( "mapper.ftl" )){
	    	return tableName.getMapperName();
	    }
	    if(layer.getTemplateName().equalsIgnoreCase( "mapper-xml.ftl" )){
	    	return tableName.getMapperName();
	    }
	    if(layer.getPrefix().contains( ".jsp" )){
	    	generate.mkdir( layer.getDir().concat( "/" ).concat( tableName.getEntityNameFirst() ) );
	    	return tableName.getEntityNameFirst();
	    }
	    if(layer.getTemplateName().equalsIgnoreCase( "service.ftl" )){
	    	return tableName.getServiceName();
	    }
	    if(layer.getTemplateName().equalsIgnoreCase( "nodejs_sequelize_model.ftl" )){
	    	return tableName.getName();
	    }
	    if(layer.getTemplateName().equalsIgnoreCase( "nodejs_sequelize_route.ftl" )){
	    	return tableName.getName().concat( "s" );
	    }
	    return null;
    }
}
