package com.dibag.code.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 模板引擎工具类
 * @author quan
 *
 */
public class FreemarkerUtil {

	/**
	 * 获取模板内容
	 * @param name 模板名称 例如 teml.ftl
	 * @return
	 */
	public Template getTemplate( String name ) {
		try {
			// 通过Freemaker的Configuration读取相应的ftl
			Configuration cfg = new Configuration();
			cfg.setDefaultEncoding("utf-8");  
			// 设定去哪里读取相应的ftl模板文件
			String templatePath = FreemarkerUtil.class.getResource("").getPath().replace("util", "template");
			File file = new File(templatePath);
			cfg.setDirectoryForTemplateLoading( file );
			//cfg.setClassForTemplateLoading( this.getClass(), "/template" );
			//cfg.setCacheStorage(new freemarker.cache.MruCacheStorage(20, 250))
			// 在模板文件目录中找到名称为name的文件
			Template temp = cfg.getTemplate( name );
			return temp;
		}
		catch( IOException e ) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 控制台输出
	 * 
	 * @param name
	 * @param root
	 */
	public void print( String name, Map<String, Object> root ) {
		try {
			// 通过Template可以将模板文件输出到相应的流
			Template temp = this.getTemplate( name );
			temp.process( root, new PrintWriter( System.out ) );
		}
		catch( TemplateException e ) {
			e.printStackTrace();
		}
		catch( IOException e ) {
			e.printStackTrace();
		}
	}

	/**
	 * 输出HTML文件
	 * 
	 * @param name
	 * @param root
	 * @param outFile 类似 "E:/workspace/freemarkprj/page/ff.txt"全路径
	 */
	public void fprint( String name, Map<String, Object> root, String outFile ) {
		FileWriter out = null;
		try {
			// 通过一个文件输出流，就可以写到相应的文件中，此处用的是绝对路径
			out = new FileWriter( new File( outFile ) );
			Template temp = this.getTemplate( name );
			temp.process( root, out );
		}
		catch( IOException e ) {
			e.printStackTrace();
		}
		catch( TemplateException e ) {
			e.printStackTrace();
		}
		finally {
			try {
				if( out != null )
					out.close();
			}
			catch( IOException e ) {
				e.printStackTrace();
			}
		}
	}

}
