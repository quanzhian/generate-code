package ${generateConfig.entityPackage};
<#list table.tableFields as tableField>
<#if (tableField.entityType == "BigDecimal")>
import java.math.BigDecimal;
</#if>
<#if (tableField.entityType == "Date")>
import java.util.Date;
</#if>
<#if (tableField.entityType == "Timestamp")>
import java.sql.Timestamp;
</#if>
</#list>

/**
*${table.entityComment}
*/
public class ${table.entityName} {
    <#list table.tableFields as tableField>
    
	/**
	 * ${tableField.comment}
	 */
	private ${tableField.entityType} ${tableField.entityField};
	</#list>
    <#list table.tableFields as tableField>

	public void set${tableField.entityFieldFirst}( ${tableField.entityType} ${tableField.entityField} ) {
		this.${tableField.entityField} = ${tableField.entityField};
	}

	public ${tableField.entityType} get${tableField.entityFieldFirst}() {
		return ${tableField.entityField};
	}
	</#list>
}
