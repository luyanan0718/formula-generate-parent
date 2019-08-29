package com.formula.generate.core.template.simple;

import com.formula.generate.core.core.ITemplate;
import com.formula.generate.core.core.TemplateConfig;

import java.util.Map;

/**
 * @author luyanan
 * @since 2019/8/28
 * <p>mapper的xml文件</p>
 **/
public class MapperXmlTemplate implements ITemplate {


    private TemplateConfig entityConfig;

    private TemplateConfig mapperConfig;


    public MapperXmlTemplate(TemplateConfig entityConfig, TemplateConfig mapperConfig) {
        this.entityConfig = entityConfig;
        this.mapperConfig = mapperConfig;
    }

    @Override
    public void after(Map<String, Object> data) {
        data.put("entityConfig", entityConfig);
        data.put("mapperConfig", mapperConfig);
    }

    @Override
    public String templatePath() {
        return "templates/mapperXml.ftl";
    }

    @Override
    public String fileNameFormat() {
        return "%sxml";
    }

    @Override
    public String pkg() {
        return "mapper/xml";
    }

    @Override
    public String fileNameSuffix() {
        return ".xml";
    }
}
