package com.formula.generate.core.engine;


import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.engine.freemarker.FreemarkerEngine;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import lombok.extern.java.Log;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;

/**
 * @author luyanan
 * @since 2019/8/26
 * <p>freemarker 模板</p>
 **/
@Log
public class FreemarkerTemplateEngine implements ITemplateEngine {


    @Override
    public void process(Map<String, Object> data, String templatePath, File outputFile) {
        FreemarkerEngine engine = new FreemarkerEngine();
        String templateContext = FileUtil.readString(this.getClass().getClassLoader().getResource(templatePath), "utf-8");
        Template template = engine.getTemplate(templateContext);
        template.render(data, outputFile);
        log.info("模板生成 ->" + outputFile.getAbsolutePath());

    }
}
