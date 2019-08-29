package com.formula.generate.core;

import com.alibaba.druid.pool.DruidDataSource;
import com.formula.generate.core.config.GlobalConfig;
import com.formula.generate.core.engine.FreemarkerTemplateEngine;
import com.formula.generate.core.template.simple.SimpleTemplateGroup;
import org.junit.Test;

public class GenerateBootstrapTest {


    @Test
    public void execute() {
        //  设置数据源
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/manage?characterEncoding=utf8&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("rootroot");
        GenerateBootstrap bootstrap = new GenerateBootstrap();
        // 配置全局配置文件
        GlobalConfig globalConfig = new GlobalConfig();
        bootstrap.setGlobalConfig(globalConfig
                // 作者
                .setAuthor("luyanan")
                // 是否输出到当前项目
                .setOutPutFileInThisProject()
                // 指定的目录
//                .setOutPutFile("D:/")
                // 父包路径
                .setParentPkg("com.formula.generate.example"));
        bootstrap
                .setDataSource(dataSource)
                // 设置模板引擎
                .setTemplateEngine(new FreemarkerTemplateEngine())
                // 包含的表
                .setInclude("sys_user", "sys_role")
                // 需要排除的表
//                .setExclude("sys_.*")
                // 设置模板组
                .setTemplateGroups(new SimpleTemplateGroup(true, true))
//                .setTemplateGroups(new SimpleDocTemplateGroup())
                .execute();

    }

}
