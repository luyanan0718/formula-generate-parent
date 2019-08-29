package com.formula.generate.core.conver;

import com.formula.generate.core.exception.GeneratorException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author luyanan
 * @since 2019/8/24
 * <p>Mysql类型</p>
 **/
public enum MySQLColumnType implements IColumnType {

    // 数值类型
    TINYINT("TINYINT", null),
    SMALLINT("SMALLINT", null),
    MEDIUMINT("MEDIUMINT", null),
    INT("INT", null),
    INTEGER("INTEGER", null),
    BIGINT("BIGINT", null),
    FLOAT("FLOAT", null),
    DOUBLE("DOUBLE", null),
    DECIMAL("DECIMAL", null),
    // 日期类型
    DATE("DATE", null),
    TIME("TIME", null),
    YEAR("YEAR", null),
    DATETIME("DATETIME", null),
    TIMESTAMP("TIMESTAMP", null),

    // 字符串类型
    CHAR("CHAR", null),
    VARCHAR("VARCHAR", null),
    TINYBLOB("TINYBLOB", null),
    TINYTEXT("TINYTEXT", null),
    BLOB("BLOB", null),
    TEXT("TEXT", null),
    MEDIUMBLOB("MEDIUMBLOB", null),
    MEDIUMTEXT("MEDIUMTEXT", null),
    LONGBLOB("LONGBLOB", null),
    LONGTEXT("LONGTEXT", null),
    UNUNKNOWN_TYPE("UNKNOWN_TYPE", null),


    ;
    private String type;

    private String pak;


    MySQLColumnType(String type, String pak) {
        this.type = type;
        this.pak = pak;
    }


    @Override
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getPak() {
        return pak;
    }


    public static MySQLColumnType get(String type) {
        List<MySQLColumnType> allEnums = Arrays.asList(MySQLColumnType.values());
        Map<String, MySQLColumnType> map = new HashMap<String, MySQLColumnType>();
        allEnums.stream().forEach(allEnum -> {
            map.put(allEnum.type, allEnum);
        });

        type = type.toUpperCase();
        MySQLColumnType iColumnType = map.get(type);
        if (null == iColumnType) {
            throw new GeneratorException(MySQLColumnType.UNUNKNOWN_TYPE.type + "--->" + type);
        }
        return iColumnType;
    }

}

