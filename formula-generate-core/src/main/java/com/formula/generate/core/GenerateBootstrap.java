package com.formula.generate.core;

import cn.hutool.core.lang.Assert;
import com.alibaba.fastjson.JSON;
import com.formula.generate.core.config.GlobalConfig;
import com.formula.generate.core.conver.ColumnTypeConverRegister;
import com.formula.generate.core.core.ITemplateGroup;
import com.formula.generate.core.core.TableInfoEntity;
import com.formula.generate.core.engine.ITemplateEngine;
import com.formula.generate.core.query.TableQueryResultFactory;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author luyanan
 * @since 2019/8/24
 * <p>生成器主启动类</p>
 **/
public class GenerateBootstrap {


    /**
     * <p>全局配置文件</p>
     *
     * @author luyanan
     * @since 2019/8/24
     */
    private GlobalConfig globalConfig;


    /**
     * <p>数据源配置</p>
     *
     * @author luyanan
     * @since 2019/8/24
     */
    private DataSource dataSource;

    /**
     * <p>需要生成的表,支持正则, 当不设置的时候,则生成全部</p>
     *
     * @author luyanan
     * @since 2019/8/24
     */
    private String[] include;


    /**
     * <p>需要排除的表</p>
     *
     * @author luyanan
     * @since 2019/8/24
     */
    private String[] exclude;


    /**
     * <p>需要移除的表前缀</p>
     *
     * @author luyanan
     * @since 2019/8/24
     */
    private String[] removeTablePrefix;


    /**
     * <p>模板引擎</p>
     *
     * @author luyanan
     * @since 2019/8/24
     */
    private ITemplateEngine templateEngine;

    /**
     * <p>类型转换  从数据库类型-> java类型</p>
     *
     * @author luyanan
     * @since 2019/8/24
     */
    private ColumnTypeConverRegister columnTypeConverRegister;


    /**
     * <p>模板组</p>
     *
     * @author luyanan
     * @since 2019/8/24
     */
    private Map<String, ITemplateGroup> templateGroups;

    {

        templateGroups = new HashMap<>();
    }

    public GenerateBootstrap setGlobalConfig(GlobalConfig globalConfig) {
        this.globalConfig = globalConfig;
        return this;
    }


    public GenerateBootstrap setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        return this;
    }

    public GenerateBootstrap setInclude(String... include) {
        this.include = include;
        return this;
    }

    public GenerateBootstrap setExclude(String... exclude) {
        this.exclude = exclude;
        return this;
    }

    public GenerateBootstrap setRemoveTablePrefix(String... removeTablePrefix) {
        this.removeTablePrefix = removeTablePrefix;
        return this;
    }

    public GenerateBootstrap setTemplateEngine(ITemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
        return this;
    }


    public GenerateBootstrap setTemplateGroups(ITemplateGroup... templateGroups) {
        for (ITemplateGroup templateGroup : templateGroups) {
            this.templateGroups.put(templateGroup.name(), templateGroup);
        }
        return this;
    }


    public ColumnTypeConverRegister getColumnTypeConverRegister() {
        return columnTypeConverRegister;
    }

    public GenerateBootstrap setColumnTypeConverRegister(ColumnTypeConverRegister columnTypeConverRegister) {
        this.columnTypeConverRegister = columnTypeConverRegister;
        return this;
    }

    public void execute() {
        // 参数校验
        Assert.notNull(globalConfig, "全局配置文件不能为空");
        Assert.notNull(dataSource, "数据源配置不能为空");
        Assert.notNull(templateEngine, "请设置模板引擎");
        Assert.notEmpty(templateGroups, "模板组不能为空");

        if (null == columnTypeConverRegister) {
            columnTypeConverRegister = new ColumnTypeConverRegister();
        }
        TableQueryResultFactory tableQueryResultFactory = new TableQueryResultFactory(this.dataSource);
        List<TableInfoEntity> tableInfoEntities = tableQueryResultFactory
                .getGenerateTableInfo(this.include, this.exclude,
                        this.removeTablePrefix, this.columnTypeConverRegister);
        System.out.println(JSON.toJSONString(tableInfoEntities));
        for (ITemplateGroup templateGroup : templateGroups.values()) {
            templateGroup.generate(tableInfoEntities, templateEngine, globalConfig);
        }
    }

}
