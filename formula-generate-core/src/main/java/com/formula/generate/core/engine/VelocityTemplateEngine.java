package com.formula.generate.core.engine;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.engine.freemarker.FreemarkerEngine;
import cn.hutool.extra.template.engine.velocity.VelocityEngine;

import java.io.File;
import java.util.Map;

/**
 * @author luyanan
 * @since 2019/8/26
 * <p>velocity模板引擎</p>
 **/
public class VelocityTemplateEngine implements ITemplateEngine {


    @Override
    public void process(Map<String, Object> data, String templatePath, File outputFile) {
        VelocityEngine velocityEngine = new VelocityEngine();
        String templateContext = FileUtil.readString(this.getClass().getClassLoader().getResource(templatePath), "utf-8");
        Template template = velocityEngine.getTemplate(templateContext);
        template.render(data, outputFile);
    }
}
