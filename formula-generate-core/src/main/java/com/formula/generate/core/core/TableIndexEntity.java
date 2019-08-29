package com.formula.generate.core.core;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author luyanan
 * @since 2019/8/24
 * <p>索引字段</p>
 **/
@Builder
@Data
@Accessors(chain = true)
public class TableIndexEntity {

    /**
     * 索引名称
     */
    private String name;


    /**
     * 索引字段
     */
    private String column;


    /**
     * 索引类型
     */
    private String indexType;


    /**
     * 索引方法
     */
    private String indexMethod;


    /**
     * 索引注释
     */
    private String indexComment;
}
