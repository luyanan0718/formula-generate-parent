package com.formula.generate.core.enums;

import com.formula.generate.core.exception.GeneratorException;

/**
 * @author luyanan
 * @since 2019/8/24
 * <p>数据库类型</p>
 **/
public enum DBTypeEnums {
    MYSQL("mysql", "mysql数据库"),
    Oracle("oracle", "oracle数据库"),
    UNKNOWN_TYPE("unknown", "未知类型");;
    private String name;

    private String introduce;

    DBTypeEnums(String name, String introduce) {
        this.name = name;
        this.introduce = introduce;
    }

    public static DBTypeEnums like(String name) {
        DBTypeEnums[] values = DBTypeEnums.values();
        for (DBTypeEnums dbTypeEnums : values) {
            if (name.toLowerCase().contains(dbTypeEnums.name)) {
                return dbTypeEnums;
            }
        }
        throw new GeneratorException(UNKNOWN_TYPE.introduce);
    }
}
