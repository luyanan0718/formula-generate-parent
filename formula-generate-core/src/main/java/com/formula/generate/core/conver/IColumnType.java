package com.formula.generate.core.conver;

/**
 * @author luyanan
 * @since 2019/8/24
 * <p>字段类型</p>
 **/
public interface IColumnType {

    /**
     * 获取类型
     *
     * @return
     */
    String getType();


    /**
     * 获取完整包名
     *
     * @return
     */
    String getPak();

}
