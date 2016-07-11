package com.dibag.code.util;


public class StringUtils {

	public static void main(String[] args) {
		System.out.println(formatAndNoPrefix("KB_CARD"));
	}
	/**
	 * format database type into java type, eg format "card_type" into "cardType"
	 * @param string
	 * @return
	 */
	public static String format(String string){
		StringBuilder sb = new StringBuilder();
		char[] cArr = string.trim().toLowerCase().toCharArray();
		for(int i=0;i<cArr.length;i++){
			char c = cArr[i];
			if(c=='_'){
				i++;
                sb.append(Character.toUpperCase(cArr[i]));
			}else{
				sb.append(c);
			}
		}
		
		return sb.toString();
	}
	
	
	public static String formatAndNoPrefix(String tableName){
		return formatAndNoPrefix( "", tableName );
	}
	
	/**
	 * 去除指定的头部部分例如   "th_tab_lbs" => "tabLbs"
	 * @param tablePrefix 例如  "th_"
	 * @param tableName 例如 "th_tab_lbs"
	 * @return
	 */
	public static String formatAndNoPrefix(String tablePrefix,String tableName){
		String noPrefixTableName = tableName.toLowerCase().replaceFirst(tablePrefix, "");
		noPrefixTableName = format(noPrefixTableName);
		return noPrefixTableName;
	}
	
	/**
	 * 首字母大写,例如 "ab" => "Ab"
	 * @param string
	 * @return
	 */
	public static String firstUpper(String string){
		String str = format(string);
		str = str.substring(0, 1).toUpperCase()+str.substring(1);
		return str;
	}
	
	/**
	 * 去除指定的头部部分,并且首字母大写 ，例如 "th_tab_lbs" => "TabLbs"
	 * @param tablePrefix 例如  "th_"
	 * @param tableName  例如 "th_tab_lbs"
	 * @return
	 */
	public static String firstUpperAndNoPrefix(String tablePrefix,String tableName){
		String noPrefixTableName = tableName.toLowerCase().replaceFirst(tablePrefix, "");
		noPrefixTableName = firstUpper(noPrefixTableName);
		return noPrefixTableName;
	}
	
	/**
	 * 首字母大写 ，例如 "th_tab_lbs" => "ThTabLbs"
	 * @param tableName
	 * @return
	 */
	public static String firstUpperAndNoPrefix(String tableName){
		return firstUpperAndNoPrefix( "", tableName );
	}
	
	/**
	 * 首字母大写 ，例如 "th_tab_lbs" => "Th_tab_lbs"
	 * @param string
	 * @return
	 */
	public static String firstUpperNoFormat(String string){
		String str = string.substring(0, 1).toUpperCase()+string.substring(1);
		return str;
	}
	
	public static boolean isEmpty(String str) {
	    return str == null || str.trim().length() <= 0;
    }
}
