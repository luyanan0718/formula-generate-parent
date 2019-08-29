package com.formula.generate.core.core;

import cn.hutool.core.util.StrUtil;
import com.formula.generate.core.config.GlobalConfig;

import java.util.List;
import java.util.Map;

/**
 * @author luyanan
 * @since 2019/8/24
 * <p>模板</p>
 **/
public interface ITemplate {


    /**
     * <p>前置操作</p>
     *
     * @param tableInfoEntitys
     * @param config
     * @return {@link }
     * @author luyanan
     * @since 2019/8/28
     */
    default void before(List<TableInfoEntity> tableInfoEntitys, TemplateConfig config) {

    }


    /**
     * <p>执行中</p>
     *
     * @param tableInfoEntity
     * @param config
     * @return {@link }
     * @author luyanan
     * @since 2019/8/28
     */
    default void handler(TableInfoEntity tableInfoEntity, TemplateConfig config) {

    }


    /**
     * <p>后置结果处理</p>
     *
     * @param data
     * @return {@link }
     * @author luyanan
     * @since 2019/8/28
     */
    default void after(Map<String, Object> data) {

    }


    /**
     * <p>获取配置文件</p>
     *
     * @param globalConfig
     * @param module
     * @param entityName
     * @return {@link TemplateConfig}
     * @author luyanan
     * @since 2019/8/29
     */
    default TemplateConfig config(GlobalConfig globalConfig, String module, String entityName) {
        TemplateConfig config = new TemplateConfig();
        String classPkg = globalConfig.getParentPkg() + ".";
        if (StrUtil.isNotBlank(module)) {
            classPkg = classPkg + module + ".";
        }
        classPkg = classPkg + "." + pkg();
        classPkg = classPkg.replaceAll("\\.+", ".");
        config.setClassPkg(classPkg);
        // 类名
        config.setClassName(className(entityName));
        return config;
    }


    /**
     * <p>模板路径</p>
     *
     * @return {@link String}
     * @author luyanan
     * @since 2019/8/26
     */
    String templatePath();


    /**
     * <p>文件名Format</p>
     *
     * @return {@link String}
     * @author luyanan
     * @since 2019/8/27
     */
    String fileNameFormat();


    /**
     * <p>文件名后缀</p>
     *
     * @return {@link String}
     * @author luyanan
     * @since 2019/8/27
     */
    default String fileNameSuffix() {
        return ".java";
    }


    /**
     * <p>包路径</p>
     *
     * @return {@link String}
     * @author luyanan
     * @since 2019/8/28
     */
    String pkg();


    /**
     * <p>类名</p>
     *
     * @param entityName
     * @return {@link String}
     * @author luyanan
     * @since 2019/8/28
     */
    default String className(String entityName) {
        return String.format(fileNameFormat(), entityName);
    }


}
