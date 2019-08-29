package com.formula.generate.core.template.simple;

import com.formula.generate.core.core.ITemplate;
import com.formula.generate.core.core.TableInfoEntity;
import com.formula.generate.core.core.TemplateConfig;

import java.util.List;
import java.util.Map;

/**
 * @author luyanan
 * @since 2019/8/28
 * <p>manage实现类层模板</p>
 **/
public class ManageImplTemplate implements ITemplate {


    /**
     * <p>是否使用mybatisPlus方法</p>
     *
     * @author luyanan
     * @since 2019/8/28
     */
    private boolean mybatisPlus;

    /**
     * <p>实体类配置</p>
     *
     * @author luyanan
     * @since 2019/8/28
     */
    private TemplateConfig entityConfig;

    /**
     * <p>mapper类配置</p>
     *
     * @author luyanan
     * @since 2019/8/28
     */
    private TemplateConfig mapperConfig;


    private TemplateConfig manageConfig;


    public ManageImplTemplate(boolean mybatisPlus, TemplateConfig entityConfig, TemplateConfig mapperConfig, TemplateConfig manageConfig) {
        this.mybatisPlus = mybatisPlus;
        this.entityConfig = entityConfig;
        this.mapperConfig = mapperConfig;
        this.manageConfig = manageConfig;
    }


    @Override
    public void before(List<TableInfoEntity> tableInfoEntities, TemplateConfig templateConfig) {
        if (mybatisPlus) {
            templateConfig.setImports("com.baomidou.mybatisplus.extension.service.impl.ServiceImpl");
        }
        templateConfig.setImports(entityConfig.getFullClassPkg(),
                mapperConfig.getFullClassPkg(),
                manageConfig.getFullClassPkg());
    }

    @Override
    public void after(Map<String, Object> data) {
        data.put("mybatisPlus", mybatisPlus);
        data.put("entityConfig", entityConfig);
        data.put("mapperConfig", mapperConfig);
        data.put("manageConfig", manageConfig);

    }

    @Override
    public String templatePath() {
        return "templates/manageImpl.ftl";
    }

    @Override
    public String fileNameFormat() {
        return "%sManageImpl";
    }

    @Override
    public String pkg() {
        return "manage.impl";
    }
}
