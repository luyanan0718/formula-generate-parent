package com.formula.generate.core.conver;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author luyanan
 * @since 2019/8/24
 * <p>java数据类型</p>
 **/
public enum  JAVAColumnTypeEnums   implements  IColumnType{


    // 基本类型
    BASE_BYTE("byte", null),

    BASE_SHORT("short", null),

    BASE_CHAR("char", null),

    BASE_INT("int", null),

    BASE_LONG("long", null),

    BASE_FLOAT("float", null),

    BASE_DOUBLE("double", null),

    BASE_BOOLEAN("boolean", null),

    // 包装类型
    BYTE("Byte", null),

    SHORT("Short", null),

    CHARACTER("Character", null),

    INTEGER("Integer", null),

    LONG("Long", null),

    FLOAT("Float", null),

    DOUBLE("Double", null),

    BOOLEAN("Boolean", null),

    STRING("String", null),

    // sql 包下数据类型
    DATE_SQL("Date", "java.sql.Date"),

    TIME("Time", "java.sql.Time"),

    TIMESTAMP("Timestamp", "java.sql.Timestamp"),

    BLOB("Blob", "java.sql.Blob"),

    CLOB("Clob", "java.sql.Clob"),

    // java8 新时间类型
    LOCAL_DATE("LocalDate", "java.time.LocalDate"),

    LOCAL_TIME("LocalTime", "java.time.LocalTime"),

    YEAR("Year", "java.time.Year"),

    YEAR_MONTH("YearMonth", "java.time.YearMonth"),

    LOCAL_DATE_TIME("LocalDateTime", "java.time.LocalDateTime"),

    // 其他杂类
    BYTE_ARRAY("byte[]", null),

    OBJECT("Object", null),

    DATE("Date", "java.util.Date"),

    BIG_INTEGER("BigInteger", "java.math.BigInteger"),

    BIG_DECIMAL("BigDecimal", "java.math.BigDecimal");


    private String type;

    private String pak;


    @Override
    public String getType() {
        return type;
    }


    @Override
    public String getPak() {
        return pak;
    }


    JAVAColumnTypeEnums(String type, String pak) {
        this.type = type;
        this.pak = pak;
    }


    public IColumnType get(String type) {

        List<JAVAColumnTypeEnums> allEnums = Arrays.asList(JAVAColumnTypeEnums.values());
        Map<String, IColumnType> map = new HashMap<>();
        allEnums.stream().forEach(javaColumnTypeEnums -> {
            map.put(javaColumnTypeEnums.type, javaColumnTypeEnums);
        });
        IColumnType iColumnType = map.get(type);
        return iColumnType;
    }

}
