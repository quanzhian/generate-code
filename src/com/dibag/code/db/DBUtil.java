package com.dibag.code.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.dibag.code.config.GenerateConfig;
import com.dibag.code.entity.TableField;
import com.dibag.code.entity.TableName;
import com.dibag.code.util.StringUtils;

public class DBUtil {
	
    private final String type_char = "char";
    
    private final String type_date = "date";
 
    private final String type_timestamp = "timestamp";
 
    private final String type_int = "int";
 
    private final String type_bigint = "bigint";
 
    private final String type_text = "text";
 
    private final String type_bit = "bit";
 
    private final String type_decimal = "decimal";
 
    private final String type_blob = "blob";
	
    private Connection conn = null;
    
    private GenerateConfig generateConfig;

    public DBUtil(GenerateConfig generateConfig){
    	this.generateConfig = generateConfig;
    }
    
    public Connection getConn() {
		return conn;
	}

    /**
     * 初始化驱动
     * @throws ClassNotFoundException
     * @throws SQLException
     */
	private void init() throws ClassNotFoundException, SQLException {
        Class.forName(generateConfig.getJdbcDriver());
        conn = DriverManager.getConnection(generateConfig.getJdbcUrl(), generateConfig.getJdbcUserName(), generateConfig.getJdbcPassword());
    }

    /**
     *  获取所有的表
     *
     * @throws SQLException 
     */
    private List<String> loadTables() throws SQLException {
    	//表集合
    	List<String> tableList = new ArrayList<String>();
        PreparedStatement pstate = conn.prepareStatement("show tables");
        ResultSet results = pstate.executeQuery();
        while ( results.next() ) {
            String tableName = results.getString(1);
            tableList.add(tableName);
        }
        return tableList;
    }
 
    /**
     * 表字段类型转成java实体字段类型
     * @param type
     * @return
     */
    private String processType( String type ) {
        if ( type.indexOf(type_char) > -1 ) {
            return "String";
        } else if ( type.indexOf(type_bigint) > -1 ) {
            return "Long";
        } else if ( type.indexOf(type_int) > -1 ) {
            return "Integer";
        } else if ( type.indexOf(type_date) > -1 ) {
            return "Date";
        } else if ( type.indexOf(type_text) > -1 ) {
            return "String";
        } else if ( type.indexOf(type_timestamp) > -1 ) {
            return "Date";
        } else if ( type.indexOf(type_bit) > -1 ) {
            return "Boolean";
        } else if ( type.indexOf(type_decimal) > -1 ) {
            return "BigDecimal";
        } else if ( type.indexOf(type_blob) > -1 ) {
            return "byte[]";
        }
        return null;
    }
    
    /**
     *  获取所有的数据库表注释
     *
     * @return
     * @throws SQLException 
     */
    private Map<String, String> loadTableComment() throws SQLException {
    	//表的注释
    	Map<String, String> tableCommentMap = new LinkedHashMap<String, String>();
        PreparedStatement pstate = conn.prepareStatement("show table status");
        ResultSet results = pstate.executeQuery();
        while ( results.next() ) {
            String tableName = results.getString("NAME");
            String comment = results.getString("COMMENT");
            tableCommentMap.put(tableName, comment);
        }
        return tableCommentMap;
    }
    
	public DatabaseMetaData getDatabaseMetaData(){
		DatabaseMetaData meta = null;
		try {
			meta = conn.getMetaData();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return meta;
	}
    
	/**
	 * 读取主键
	 * @param tableName
	 * @return primary key if table contains one
	 */
	public String loadPrimaryKey(String tableName){
		try {
			ResultSet pkRSet = getDatabaseMetaData().getPrimaryKeys(null, null, tableName); 
			while( pkRSet.next() ) { 
				String primaryKey = pkRSet.getString("COLUMN_NAME");
				return primaryKey;
			} 
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return null;
	}
    
	/**
	 * 获取数据库所有的表和字段信息
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
    public List<TableName> getData() throws ClassNotFoundException, SQLException, IOException {
        init();
        String prefix = "show full fields from ";
        PreparedStatement pstate = null;
        List<String> tables = loadTables();
        Map<String, String> tableComments = loadTableComment();
        
        List<TableName> lsTableNames = new ArrayList<TableName>();
        
        for ( String table : tables ) {
        	TableName tableName = new TableName();
        	tableName.setName( table );
        	tableName.setEntityComment( tableComments.get( table ) );
        	String ClassName = StringUtils.firstUpperAndNoPrefix( table );
        	tableName.setEntityName( ClassName );
        	tableName.setEntityNameFirst( StringUtils.format( table ) );
        	String _pk = loadPrimaryKey( table );
        	tableName.setPrimaryKey( _pk );
        	tableName.setPrimaryKeyEntityName( StringUtils.format( _pk ) );
        	tableName.setPrimaryKeyEntityNameFirst( StringUtils.firstUpperAndNoPrefix( _pk ) );
                   
            pstate = conn.prepareStatement(prefix + table);
            ResultSet results = pstate.executeQuery();
            while ( results.next() ) {
            	String _field = results.getString("FIELD");
            	String _type = results.getString("TYPE");
            	String _comment = results.getString("COMMENT");
                
                TableField tableField = new TableField();
                tableField.setColumnName( _field );
                tableField.setColumnType( _type );
                tableField.setComment( _comment );
                tableField.setEntityField( StringUtils.format( _field ) );
                tableField.setEntityFieldFirst( StringUtils.firstUpperAndNoPrefix( _field ) );
                tableField.setEntityType( processType( _type ) );
                
                if(tableName.getPrimaryKey() != null && tableName.getPrimaryKey().equals( _field )){
                	tableName.setPrimaryKeyType( processType( _type ) );
                	tableField.setPK( true );
                }
 

                                
                tableName.getTableFields().add( tableField );
            }
            lsTableNames.add( tableName );
        }
        conn.close();
        return lsTableNames;
    }
    
}
