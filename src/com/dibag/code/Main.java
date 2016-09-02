package com.dibag.code;

import com.dibag.code.config.Generate;
import com.dibag.code.config.GenerateConfig;
import com.dibag.code.util.FreemarkerUtil;

public class Main {

	public static void main( String[] args ) {
		// TODO Auto-generated method stub
		//System.out.println(UUID.randomUUID().toString().replace( "-", "" ).length());
		//System.out.println(new FreemarkerUtil().getTemplate( "jsp.ftl" ));
		
		GenerateConfig generateConfig = new GenerateConfig();
		generateConfig.setBasePackage( "com.dibage.pro" );
		generateConfig.setCommonPackage( "com.dibage.pro.common" );
		generateConfig.setControllerPackage( "com.dibage.pro.controller.cms" );
		generateConfig.setEntityPackage( "com.dibage.pro.entity" );
		generateConfig.setMapperPackage( "com.dibage.pro.mapper" );
		generateConfig.setServicePackage( "com.dibage.pro.service" );
		generateConfig.setJdbcDriver( "com.mysql.jdbc.Driver" );
		generateConfig.setJdbcUrl( "jdbc:mysql://127.0.0.1:3306/dbg_cms?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&amp;autoReconnectForPools=true" );
		generateConfig.setJdbcUserName( "root" );
		generateConfig.setJdbcPassword( "root" );
		generateConfig.setProjectName( "dibage-cms-pro" );
		generateConfig.setSaveFilePath( "F:/lins/test" );
		
		generateConfig.setTables( "" );
		generateConfig.setTemplateBasePath( "" );
		
		generateConfig.initDefaultDirectory();
		Generate generate = new Generate( generateConfig );
		generate.run();
	}

}
