package com.formula.generate.core.template.simple;

import com.formula.generate.core.core.ITemplate;
import com.formula.generate.core.core.TableInfoEntity;
import com.formula.generate.core.core.TemplateConfig;

import java.util.List;
import java.util.Map;

/**
 * @author luyanan
 * @since 2019/8/28
 * <p>控制层实现类</p>
 **/
public class ControllerTemplate implements ITemplate {

    private TemplateConfig serviceConfig;

    private boolean swagger = false;

    public ControllerTemplate(TemplateConfig serviceConfig) {
        this.serviceConfig = serviceConfig;
    }

    public ControllerTemplate(TemplateConfig serviceConfig, boolean swagger) {
        this.serviceConfig = serviceConfig;
        this.swagger = swagger;
    }

    @Override
    public void after(Map<String, Object> data) {
        data.put("serviceConfig", serviceConfig);
        data.put("swagger", swagger);
    }

    @Override
    public void before(List<TableInfoEntity> tableInfoEntities, TemplateConfig templateConfig) {
        templateConfig.setImports(serviceConfig.getFullClassPkg());
        if (swagger) {
            templateConfig.setImports("io.swagger.annotations.Api");
        }
    }

    @Override
    public void handler(TableInfoEntity tableInfoEntity, TemplateConfig config) {
        String requestMapping = tableInfoEntity.getTableName().replaceAll("_", "/").toLowerCase();
        config.put("requestMapping", requestMapping);
    }

    @Override
    public String templatePath() {
        return "templates/controller.ftl";
    }

    @Override
    public String fileNameFormat() {
        return "%sController";
    }

    @Override
    public String pkg() {
        return "controller";
    }
}
