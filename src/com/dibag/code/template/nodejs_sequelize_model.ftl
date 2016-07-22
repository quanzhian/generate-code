/**
 * ${table.entityComment}
 */
"use strict";
module.exports = function (sequelize, DataTypes) {

    // 默认表名（一般这里写单数），生成时会自动转换成复数形式
    // 这个值还会作为访问模型相关的模型时的属性名，所以建议用小写形式
    var ${table.entityName} = sequelize.define("${table.name}", {
<#list table.tableFields as tableField>
<#if (table.primaryKey == tableField.columnName)>
            '${tableField.columnName}': {
                'type': DataTypes.INTEGER, // 字段类型
                'autoIncrement' : true,      // 是否是自增
                'primaryKey' : true,         // 是否是主键
                'allowNull': false,        // 是否允许为NULL
                'comment' : '${tableField.comment}',    // 备注
                'unique': true             // 字段是否UNIQUE
            }
<#elseif (tableField.entityType == "BigDecimal")>
            '${tableField.columnName}': {
                'type': DataTypes.DECIMAL,  // 字段类型
                'allowNull': false,         // 是否允许为NULL
                'comment' : '${tableField.comment}',    // 备注
                'defaultValue' : 0           //默认值设置
            }
<#elseif (tableField.entityType == "Date")>
            '${tableField.columnName}' : {
                'type' : DataTypes.DATE, 
                'comment' : '${tableField.comment}',    // 备注
                'defaultValue' : DataTypes.NOW
            }
<#elseif (tableField.entityType == "Timestamp")>
            '${tableField.columnName}' : {
                'type' : DataTypes.DATE, 
                'comment' : '${tableField.comment}',    // 备注
                'defaultValue' : DataTypes.NOW
            }
<#elseif (tableField.columnType == "text")>
            '${tableField.columnName}': {
                'type': DataTypes.TEXT,  // 字段类型
                'comment' : '${tableField.comment}',    // 备注
                'allowNull': true         // 是否允许为NULL
            }               
<#else>
            '${tableField.columnName}': {
                'type': DataTypes.${tableField.entityTypeBig},  // 字段类型
                'comment' : '${tableField.comment}',    // 备注
                'allowNull': true         // 是否允许为NULL
            }            
</#if>
<#if tableField_has_next>,</#if>
</#list>  
},
        {
            'freezeTableName': true,      // 是否自定义表名
            'tableName': '${table.name}',//自定义表名
            'timestamps': false          // 是否需要增加createdAt、updatedAt、deletedAt字段
        }
);  
    return ${table.entityName};
};

 