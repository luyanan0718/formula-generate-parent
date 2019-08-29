package com.formula.generate.core.core;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author luyanan
 * @since 2019/8/24
 * <p>表字段</p>
 **/
@Data
public class TableFieldEntity {

    /**
     * <p>字段名称</p>
     *
     * @author luyanan
     * @since 2019/8/24
     */
    private String jdbcFieldName;


    /**
     * <p>字段类型</p>
     *
     * @author luyanan
     * @since 2019/8/24
     */
    private String jdbcType;


    /**
     * <p>字段大小</p>
     *
     * @author luyanan
     * @since 2019/8/24
     */
    private String jdbcLength;


    /**
     * <p>字段默认值</p>
     *
     * @author luyanan
     * @since 2019/8/24
     */
    private String columnDefault = "";


    /**
     * <p>注释</p>
     *
     * @author luyanan
     * @since 2019/8/24
     */
    private String comment;


    /**
     * 是否为主键
     */
    private Boolean primaryKey;


    /**
     * 是否为自增
     */
    private Boolean increment;


    /**
     * 字段编码
     */
    private String jdbcColumnCharacter;


    /**
     * 是否可以为空
     */
    private Boolean nullable;


    /**
     * 字段名称
     */
    private String columnName;


    /**
     * 字段类型
     */
    private String columnType;


    /**
     * 字段引用
     */
    private String columnImport;


}
