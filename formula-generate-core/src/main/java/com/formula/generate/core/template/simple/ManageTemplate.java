package com.formula.generate.core.template.simple;

import com.formula.generate.core.core.ITemplate;
import com.formula.generate.core.core.TemplateConfig;

import java.util.Map;

/**
 * @author luyanan
 * @since 2019/8/28
 * <p>manage层模板</p>
 **/
public class ManageTemplate implements ITemplate {


    @Override
    public String templatePath() {
        return "templates/manage.ftl";
    }

    @Override
    public String fileNameFormat() {
        return "%sManage";
    }

    @Override
    public String pkg() {
        return "manage";
    }
}
