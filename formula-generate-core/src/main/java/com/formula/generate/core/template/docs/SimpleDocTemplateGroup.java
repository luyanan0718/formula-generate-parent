package com.formula.generate.core.template.docs;

import com.formula.generate.core.config.GlobalConfig;
import com.formula.generate.core.core.AbstractTemplateGroup;
import com.formula.generate.core.core.ITemplate;
import com.formula.generate.core.core.ITemplateGroup;
import com.formula.generate.core.core.TableInfoEntity;
import com.formula.generate.core.engine.ITemplateEngine;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author luyanan
 * @since 2019/8/29
 * <p>数据库文档生成</p>
 **/
public class SimpleDocTemplateGroup extends AbstractTemplateGroup {


    @Override
    public List<ITemplate> templates(GlobalConfig globalConfig, TableInfoEntity tableInfoEntity) {
        DocumentTemplate documentTemplate = new DocumentTemplate();
        List<ITemplate> templates = new ArrayList<>();
        templates.add(documentTemplate);
        return templates;
    }

    @Override
    public String name() {
        return this.getClass().getName();
    }

    @Override
    public void generate(List<TableInfoEntity> tableInfoEntities, ITemplateEngine templateEngine, GlobalConfig globalConfig) {
        for (ITemplate template : templates(globalConfig, null)) {
            Map<String, Object> data = new HashMap<>();
            data.put("tableInfoEntities", tableInfoEntities);
            String fileName = super.fileName(template, "数据库文档");
            File outputFile = super.outputFile(template, fileName, globalConfig);
            defaultInitData(data, globalConfig);
            templateEngine.process(data, template.templatePath(), outputFile);
        }
    }
}
