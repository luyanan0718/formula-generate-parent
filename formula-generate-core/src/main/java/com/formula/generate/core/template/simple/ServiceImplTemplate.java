package com.formula.generate.core.template.simple;

import com.formula.generate.core.core.ITemplate;
import com.formula.generate.core.core.TableInfoEntity;
import com.formula.generate.core.core.TemplateConfig;

import java.util.List;
import java.util.Map;

/**
 * @author luyanan
 * @since 2019/8/28
 * <p>service 实现层代码</p>
 **/
public class ServiceImplTemplate implements ITemplate {

    private TemplateConfig manageConfig;

    private TemplateConfig serviceConfig;


    public ServiceImplTemplate(TemplateConfig manageConfig, TemplateConfig serviceConfig) {
        this.manageConfig = manageConfig;
        this.serviceConfig = serviceConfig;
    }

    @Override
    public void before(List<TableInfoEntity> tableInfoEntities, TemplateConfig templateConfig) {
        templateConfig.setImports(manageConfig.getFullClassPkg(), serviceConfig.getFullClassPkg());

    }

    @Override
    public void after(Map<String, Object> data) {
        data.put("manageConfig", manageConfig);
        data.put("serviceConfig", serviceConfig);
    }

    @Override
    public String templatePath() {
        return "templates/serviceImpl.ftl";
    }

    @Override
    public String fileNameFormat() {
        return "%sServiceImpl";
    }

    @Override
    public String pkg() {
        return "service.impl";
    }
}
