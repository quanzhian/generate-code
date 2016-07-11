package com.dibag.code.config;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.dibag.code.db.DBUtil;
import com.dibag.code.entity.TableName;
import com.dibag.code.layers.BaseGenerate;

/**
 * 自动生成代码
 * @author quan
 *
 */
public class Generate {
	
	public Generate(GenerateConfig generateConfig){
		this.generateConfig = generateConfig;
		this.dbUtil = new DBUtil( generateConfig );
		this.baseGenerate = new BaseGenerate( this );
	}
	
	private BaseGenerate baseGenerate;
	
	private DBUtil dbUtil;
	
	private GenerateConfig generateConfig;
	
	/**
	 * 基包的目录
	 */
	private String packageDir;
	
	/**
	 * mapperxml的目录
	 */
	private String mapperxmlDir;
	
	/**
	 * mapper的目录
	 */
	private String mapperDir;
	
	/**
	 * 服务类的目录
	 */
	private String serviceDir;
	
	/**
	 * 控制器的目录
	 */
	private String controllerDir;
	
	/**
	 * 实体类的目录
	 */
	private String modelDir;
	
	/**
	 * 页面的目录
	 */
	private String jspDir;

	public GenerateConfig getGenerateConfig() {
		return generateConfig;
	}

	public void setGenerateConfig( GenerateConfig generateConfig ) {
		this.generateConfig = generateConfig;
	}
	
	public String getPackageDir() {
		return packageDir;
	}

	public String getMapperxmlDir() {
		return mapperxmlDir;
	}

	public String getMapperDir() {
		return mapperDir;
	}

	public String getServiceDir() {
		return serviceDir;
	}

	public String getControllerDir() {
		return controllerDir;
	}

	public String getModelDir() {
		return modelDir;
	}

	public String getJspDir() {
		return jspDir;
	}
	
	public BaseGenerate getBaseGenerate() {
		return baseGenerate;
	}

	public void setBaseGenerate( BaseGenerate baseGenerate ) {
		this.baseGenerate = baseGenerate;
	}

	private void createPackageDirectory(){
		String location = generateConfig.getSaveFilePath();		
		String project = generateConfig.getProjectName();
		if(project!=null && !"".equals(project)){
			location = location + "/" + project + "/src";
		}
		mapperxmlDir = location + "/resources/mybatis";
		packageDir = location + "/" + generateConfig.getBasePackage().replaceAll("[.]", "/");
		mapperDir = location + "/" + generateConfig.getMapperPackage().replaceAll("[.]", "/");
		serviceDir = location + "/" + generateConfig.getServicePackage().replaceAll("[.]", "/");
		controllerDir = location + "/" + generateConfig.getControllerPackage().replaceAll("[.]", "/");
		modelDir = location + "/" + generateConfig.getEntityPackage().replaceAll("[.]", "/");
		jspDir = location + "/views";
		
		String layers = generateConfig.getLayers();
		String[] layStrs = layers.split( "," );
		for( String lay : layStrs ) {
			if(lay.equals("controller")){
				mkdir(controllerDir); 
				baseGenerate.put( "controller.ftl", controllerDir,BizType.Controller );
				continue;
			}if(lay.equals("mapperxml")){
				mkdir(mapperxmlDir); 
				baseGenerate.put( "mapper-xml.ftl", mapperxmlDir,BizType.MapperXML );
				continue;
			}if(lay.equals("mapper")){
				mkdir(mapperDir); 
				baseGenerate.put( "mapper.ftl", mapperDir,BizType.Mapper );
				continue;
			}if(lay.equals("service")){
				mkdir(serviceDir);
				baseGenerate.put( "service.ftl", serviceDir,BizType.Service );
				continue;
			}if(lay.equals("entity")){
				mkdir(modelDir); 
				baseGenerate.put( "entity.ftl", modelDir,BizType.Entity );
				continue;
			}if(lay.equals("jsp")){
				mkdir(jspDir); 
				baseGenerate.put( "jsp.ftl", jspDir,BizType.Page_list );
				baseGenerate.put( "jsp_create.ftl", jspDir,BizType.Page_create );
				baseGenerate.put( "jsp_edit.ftl", jspDir,BizType.Page_edit );
				continue;
			}
        }
	}

	public void mkdir(String dir) {
		File file;
		file = new File(dir); 
		if(!file.exists()){ 
			file.mkdirs();
		}
	}
	
	public String getMapperPackageDirectory(){
		String location = generateConfig.getSaveFilePath();
		String packageDir = "/" + generateConfig.getMapperPackage().replaceAll("[.]", "/");
		String project = generateConfig.getProjectName();
		String directory = null;
		if(project!=null && !"".equals(project)){
			directory = location + "/src" + packageDir +"/";
		}else{
			directory = location + packageDir +"/";
		}
		return directory;
	}
	
	public String getPackageDirectory(String name){
		String location = generateConfig.getSaveFilePath();
		String packageDir = "/" + generateConfig.getBasePackage().replaceAll("[.]", "/");
		String project = generateConfig.getProjectName();
		String directory = null;
		if(project!=null && !"".equals(project)){
			directory = location + "/src" + packageDir +"/"+ name +"/";
		}else{
			directory = location + packageDir +"/"+ name +"/";
		}
		return directory;
	}
	
	public void run() {
		List<TableName> tableNames = null;
		try {
			System.out.println("begin loading db data ！");
	        tableNames = dbUtil.getData();
	        System.out.println("end loaded db data ！");
	        dbUtil.getConn().close();
	        System.out.println("close db ！");
        }
        catch( ClassNotFoundException e ) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
        catch( SQLException e ) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
        catch( IOException e ) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }finally{
        	try {
	            dbUtil.getConn().close();
            }
            catch( SQLException e ) {
	            e.printStackTrace();
            }
        }
		
		if(tableNames == null){
			System.out.println("tableNames is null, stop Generate ！");
			return;
		}
        createPackageDirectory();
        baseGenerate.setTableNames( tableNames );
        baseGenerate.generateAllCode();
        System.out.println("Generate code finish ！");
    }
	
}
