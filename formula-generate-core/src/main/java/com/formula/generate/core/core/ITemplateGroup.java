package com.formula.generate.core.core;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.formula.generate.core.config.GlobalConfig;
import com.formula.generate.core.engine.ITemplateEngine;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author luyanan
 * @since 2019/8/24
 * <p>模板组</p>
 **/
public interface ITemplateGroup {


    /**
     * <p>作者</p>
     *
     * @return {@link String}
     * @author luyanan
     * @since 2019/8/29
     */
    default String author() {
        return null;
    }


    /**
     * <p>模块名</p>
     *
     * @return {@link String}
     * @author luyanan
     * @since 2019/8/28
     */
    default String module() {
        return null;
    }


    /**
     * <p>模板组的名称</p>
     *
     * @return {@link String}
     * @author luyanan
     * @since 2019/8/24
     */
    default String name() {
        return this.getClass().getName();
    }


    /**
     * <p>组的成员模板</p>
     *
     * @param globalConfig
     * @param tableInfoEntity
     * @return {@link List< ITemplate>}
     * @author luyanan
     * @since 2019/8/29
     */
    List<ITemplate> templates(GlobalConfig globalConfig, TableInfoEntity tableInfoEntity);


    /**
     * <p>代码生成</p>
     *
     * @param tableInfoEntities
     * @param templateEngine
     * @param globalConfig
     * @return {@link }
     * @author luyanan
     * @since 2019/8/29
     */
    void generate(List<TableInfoEntity> tableInfoEntities, ITemplateEngine templateEngine, GlobalConfig globalConfig);
}
