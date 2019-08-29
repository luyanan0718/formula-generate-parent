package com.formula.generate.core.template.docs;

import com.formula.generate.core.core.ITemplate;

/**
 * @author luyanan
 * @since 2019/8/29
 * <p>文档模板</p>
 **/
public class DocumentTemplate implements ITemplate {


    @Override
    public String templatePath() {
        return "templates/dataBaseDocument.ftl";
    }

    @Override
    public String fileNameFormat() {
        return "数据库文档";
    }

    @Override
    public String pkg() {
        return "doc";
    }

    @Override
    public String fileNameSuffix() {
        return ".md";
    }
}
