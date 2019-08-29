package com.formula.generate.core.template.simple;

import com.formula.generate.core.config.GlobalConfig;
import com.formula.generate.core.core.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luyanan
 * @since 2019/8/26
 * <p>默认的模板分组</p>
 **/
public class SimpleTemplateGroup extends AbstractTemplateGroup {
    private boolean swagger = false;
    private boolean mybatisPlus = false;

    public SimpleTemplateGroup(boolean swagger, boolean mybatisPlus) {
        this.swagger = swagger;
        this.mybatisPlus = mybatisPlus;
    }

    public SimpleTemplateGroup() {
    }

    @Override
    public String name() {
        return this.getClass().getName();
    }

    @Override
    public List<ITemplate> templates(GlobalConfig globalConfig, TableInfoEntity tableInfoEntity) {
        List<ITemplate> templates = new ArrayList<>();
        EntityTemplate entityTemplate = new EntityTemplate(swagger, mybatisPlus);
        TemplateConfig entityConfig = entityTemplate.config(globalConfig, module(), tableInfoEntity.getEntityName());
        templates.add(entityTemplate);
        MapperTemplate mapperTemplate = new MapperTemplate(entityConfig, mybatisPlus);
        templates.add(mapperTemplate);
        TemplateConfig mapperConfig = mapperTemplate.config(globalConfig, module(), tableInfoEntity.getEntityName());
        // mapper  xml
        MapperXmlTemplate mapperXmlTemplate = new MapperXmlTemplate(entityConfig, mapperConfig);
        templates.add(mapperXmlTemplate);
        //  mansage层 模板
        ManageTemplate manageTemplate = new ManageTemplate();
        TemplateConfig manageConfig = manageTemplate.config(globalConfig, module(), tableInfoEntity.getEntityName());
        templates.add(manageTemplate);
        ManageImplTemplate manageImplTemplate = new ManageImplTemplate(mybatisPlus, entityConfig, mapperConfig, manageConfig);
        templates.add(manageImplTemplate);
        // service层
        ServiceTemplate serviceTemplate = new ServiceTemplate();
        TemplateConfig serviceConfig = serviceTemplate.config(globalConfig, module(), tableInfoEntity.getEntityName());
        templates.add(serviceTemplate);
        ServiceImplTemplate serviceImplTemplate = new ServiceImplTemplate(manageConfig, serviceConfig);
        templates.add(serviceImplTemplate);
        //  controller层
        ControllerTemplate controllerTemplate = new ControllerTemplate(serviceConfig, swagger);
        templates.add(controllerTemplate);

        return templates;
    }


    @Override
    public String module() {
        return "user";
    }




}
