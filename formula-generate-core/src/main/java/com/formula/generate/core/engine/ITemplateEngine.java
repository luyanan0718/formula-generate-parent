package com.formula.generate.core.engine;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.io.File;
import java.util.Map;

/**
 * @author luyanan
 * @since 2019/8/24
 * <p>模板引擎</p>
 **/
public interface ITemplateEngine {


    /**
     * <p>生成模板文件</p>
     *
     * @param data
     * @param templatePath
     * @param outputFile
     * @return {@link }
     * @author luyanan
     * @since 2019/8/24
     */
    void process(Map<String, Object> data, String templatePath, File outputFile);

}
