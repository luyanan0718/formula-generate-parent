package com.formula.generate.core.conver;


import java.util.HashMap;
import java.util.Map;

/**
 * @author luyanan
 * @since 2019/8/24
 * <p>字段类型转换  从数据库类型转换为java类型</p>
 **/
public class ColumnTypeConverRegister {


    private static Map<String, IColumnType> columnTypeMap = new HashMap<>();


    static {

        columnTypeMap.put(MySQLColumnType.TINYINT.getType(), JAVAColumnTypeEnums.INTEGER);
        columnTypeMap.put(MySQLColumnType.SMALLINT.getType(), JAVAColumnTypeEnums.INTEGER);
        columnTypeMap.put(MySQLColumnType.MEDIUMINT.getType(), JAVAColumnTypeEnums.LONG);
        columnTypeMap.put(MySQLColumnType.INT.getType(), JAVAColumnTypeEnums.INTEGER);
        columnTypeMap.put(MySQLColumnType.INTEGER.getType(), JAVAColumnTypeEnums.INTEGER);
        columnTypeMap.put(MySQLColumnType.BIGINT.getType(), JAVAColumnTypeEnums.LONG);
        columnTypeMap.put(MySQLColumnType.FLOAT.getType(), JAVAColumnTypeEnums.FLOAT);
        columnTypeMap.put(MySQLColumnType.DOUBLE.getType(), JAVAColumnTypeEnums.DOUBLE);
        columnTypeMap.put(MySQLColumnType.DECIMAL.getType(), JAVAColumnTypeEnums.BIG_DECIMAL);
        columnTypeMap.put(MySQLColumnType.DATE.getType(), JAVAColumnTypeEnums.DATE);
        columnTypeMap.put(MySQLColumnType.TIME.getType(), JAVAColumnTypeEnums.DATE);
        columnTypeMap.put(MySQLColumnType.YEAR.getType(), JAVAColumnTypeEnums.DATE);
        columnTypeMap.put(MySQLColumnType.DATETIME.getType(), JAVAColumnTypeEnums.DATE);
        columnTypeMap.put(MySQLColumnType.TIMESTAMP.getType(), JAVAColumnTypeEnums.DATE);
        columnTypeMap.put(MySQLColumnType.CHAR.getType(), JAVAColumnTypeEnums.STRING);
        columnTypeMap.put(MySQLColumnType.VARCHAR.getType(), JAVAColumnTypeEnums.STRING);
        columnTypeMap.put(MySQLColumnType.TINYBLOB.getType(), JAVAColumnTypeEnums.STRING);
        columnTypeMap.put(MySQLColumnType.TINYTEXT.getType(), JAVAColumnTypeEnums.STRING);
        columnTypeMap.put(MySQLColumnType.BLOB.getType(), JAVAColumnTypeEnums.STRING);
        columnTypeMap.put(MySQLColumnType.TEXT.getType(), JAVAColumnTypeEnums.STRING);
        columnTypeMap.put(MySQLColumnType.MEDIUMBLOB.getType(), JAVAColumnTypeEnums.STRING);
        columnTypeMap.put(MySQLColumnType.MEDIUMTEXT.getType(), JAVAColumnTypeEnums.STRING);
        columnTypeMap.put(MySQLColumnType.LONGBLOB.getType(), JAVAColumnTypeEnums.STRING);
        columnTypeMap.put(MySQLColumnType.LONGTEXT.getType(), JAVAColumnTypeEnums.STRING);


    }


    public void add(String jdbcTye, IColumnType columnType) {
        columnTypeMap.put(jdbcTye.toUpperCase(), columnType);
    }

    public IColumnType conver(String jdbcType) {
        return columnTypeMap.get(jdbcType.toUpperCase());
    }


}
