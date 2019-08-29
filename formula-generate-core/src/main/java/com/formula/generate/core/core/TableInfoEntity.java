package com.formula.generate.core.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * @author luyanan
 * @since 2019/8/24
 * <p>表信息</p>
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class TableInfoEntity {

    /**
     * <p>表名称</p>
     *
     * @author luyanan
     * @since 2019/8/24
     */
    private String tableName;


    /**
     * <p>处理之后的名称</p>
     *
     * @author luyanan
     * @since 2019/8/24
     */
    private String entityName;

    /**
     * <p>注释</p>
     *
     * @author luyanan
     * @since 2019/8/24
     */
    private String comment;


    /**
     * <p>数据库引擎</p>
     *
     * @author luyanan
     * @since 2019/8/24
     */
    private String engine;


    /**
     * <p>表创建时间</p>
     *
     * @author luyanan
     * @since 2019/8/24
     */
    private Date createTime;


    /**
     * <p>数据库表的编码格式</p>
     *
     * @author luyanan
     * @since 2019/8/24
     */
    private String encode;


    /**
     * <p>字段信息</p>
     *
     * @author luyanan
     * @since 2019/8/24
     */
    private List<TableFieldEntity> tableFieldEntityList;

    /**
     * <p>索引信息</p>
     *
     * @author luyanan
     * @since 2019/8/24
     */
    private List<TableIndexEntity> tableIndexEntityList;

}
