package com.formula.generate.core.template.simple;

import com.formula.generate.core.core.ITemplate;
import com.formula.generate.core.core.TemplateConfig;

import java.util.Map;

/**
 * @author luyanan
 * @since 2019/8/28
 * <p>service 模板</p>
 **/
public class ServiceTemplate implements ITemplate {


    @Override
    public String templatePath() {
        return "templates/service.ftl";
    }

    @Override
    public String fileNameFormat() {
        return "%sService";
    }

    @Override
    public String pkg() {
        return "service";
    }
}
