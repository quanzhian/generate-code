package com.dibag.code.config;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.dibag.code.db.DBUtil;
import com.dibag.code.entity.Layer;
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
	
	private String location;

	public GenerateConfig getGenerateConfig() {
		return generateConfig;
	}

	public void setGenerateConfig( GenerateConfig generateConfig ) {
		this.generateConfig = generateConfig;
	}
	
	public BaseGenerate getBaseGenerate() {
		return baseGenerate;
	}

	public void setBaseGenerate( Class<? extends BaseGenerate> c ) {
		try {
	        this.baseGenerate = c.newInstance();
	        this.baseGenerate.setGenerate( this );
        }
        catch( InstantiationException e ) {
	        e.printStackTrace();
        }
        catch( IllegalAccessException e ) {
	        e.printStackTrace();
        }
	}

	public String getLocation() {
		return location;
	}

	private void createPackageDirectory(){
		List<Layer> layers = generateConfig.getLayers();
		for( Layer layer : layers ) {
			mkdir(layer.getDir()); 
        }
	}

	public void mkdir(String dir) {
		File file;
		file = new File(dir); 
		if(!file.exists()){ 
			file.mkdirs();
		}
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
        baseGenerate.generateCode();
        System.out.println("Generate code finish ！");
    }
	
}
