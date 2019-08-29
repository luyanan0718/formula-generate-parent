package com.formula.generate.core.core;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.formula.generate.core.config.GlobalConfig;
import com.formula.generate.core.engine.ITemplateEngine;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author luyanan
 * @since 2019/8/29
 * <p>模板组抽象类</p>
 **/
public abstract class AbstractTemplateGroup implements ITemplateGroup {


    @Override
    public void generate(List<TableInfoEntity> tableInfoEntities, ITemplateEngine templateEngine, GlobalConfig globalConfig) {
        Map<String, Object> data = null;
        for (TableInfoEntity tableInfoEntity : tableInfoEntities) {

            for (ITemplate template : this.templates(globalConfig, tableInfoEntity)) {
                String packagePath = globalConfig.getParentPkg();
                if (null != this.module()) {
                    packagePath = packagePath + "." + this.module() + ".";
                }

                TemplateConfig config = template.config(globalConfig, this.module(), tableInfoEntity.getEntityName());
                // 执行模板初始化操作
                template.before(tableInfoEntities, config);
                packagePath = packagePath + template.pkg();
                packagePath = packagePath.replaceAll("\\.+", ".");
                data = new HashMap<>(6);
                String fileName = fileName(template, tableInfoEntity.getEntityName());
                config.setClassName(fileName);
                File outputFile = outputFile(template, fileName, globalConfig);
                template.handler(tableInfoEntity, config);
                data.put("className", fileName);
                data.put("config", config);
                data.put("entity", tableInfoEntity);
                data.put("classPkg", packagePath);

                defaultInitData(data, globalConfig);
                //  对结果进行拦截
                template.after(data);
                templateEngine.process(data, template.templatePath(), outputFile);
            }
        }
    }

    /**
     * <p>默认的数据初始化</p>
     *
     * @param data
     * @param globalConfig
     * @return {@link }
     * @author luyanan
     * @since 2019/8/29
     */
    protected void defaultInitData(Map<String, Object> data, GlobalConfig globalConfig) {
        if (null != this.author()) {
            globalConfig.setAuthor(this.author());
        }
        data.put("author", globalConfig.getAuthor());
        data.put("since", DateUtil.date().toString("yyyy-MM-dd "));
    }


    /**
     * <p>获取文件名称</p>
     *
     * @param template
     * @param entityName
     * @return {@link String}
     * @author luyanan
     * @since 2019/8/29
     */
    protected String fileName(ITemplate template, String entityName) {
        return String.format(template.fileNameFormat(), entityName);
    }

    /**
     * <p>获取输出文件</p>
     *
     * @param template
     * @param fileName
     * @param globalConfig
     * @return {@link File}
     * @author luyanan
     * @since 2019/8/29
     */
    protected File outputFile(ITemplate template, String fileName, GlobalConfig globalConfig) {
        String baseOutPutFile = "";
        if (StrUtil.isNotBlank(globalConfig.getOutPutFile())) {
            baseOutPutFile = baseOutPutFile + "/" + globalConfig.getOutPutFile() + "/";
        }
        if (StrUtil.isNotBlank(globalConfig.getParentPkg())) {
            baseOutPutFile = baseOutPutFile + "/" + globalConfig.getParentPkg().replaceAll("\\.", "/") + "/";
        }
        if (null != this.module()) {
            baseOutPutFile = baseOutPutFile + "/" + this.module() + "/";
        }
        if (null != this.author()) {
            globalConfig.setAuthor(this.author());
        }
        String outPutPath = baseOutPutFile + template.pkg().replaceAll("\\.", "/");
        String outPutFilePath = outPutPath + "/" + fileName;
        outPutFilePath = outPutFilePath.replaceAll("/+", "/");
        return new File(outPutFilePath + template.fileNameSuffix());
    }
}
