package com.formula.generate.core.template.simple;

import com.formula.generate.core.core.ITemplate;
import com.formula.generate.core.core.TableInfoEntity;
import com.formula.generate.core.core.TemplateConfig;

import java.util.List;

/**
 * @author luyanan
 * @since 2019/8/28
 * <p>生成Mapper文件</p>
 **/
public class MapperTemplate implements ITemplate {

    private TemplateConfig entityConfig = null;

    private boolean mybatisPlus = false;


    public MapperTemplate(TemplateConfig entityConfig, boolean mybatisPlus) {
        this.entityConfig = entityConfig;
        this.mybatisPlus = mybatisPlus;
    }

    public MapperTemplate(TemplateConfig entityConfig) {
        this.entityConfig = entityConfig;
    }

    @Override
    public void before(List<TableInfoEntity> tableInfoEntities, TemplateConfig config) {

        config.put("mybatisPlus", mybatisPlus);
        config.put("entityConfig", this.entityConfig);
        if (mybatisPlus) {
            config.setImports("com.baomidou.mybatisplus.core.mapper.BaseMapper");
        }
        config.setImports(this.entityConfig.getFullClassPkg());
        config.setImports("org.apache.ibatis.annotations.Mapper");
        config.setAnnotations("@Mapper");
    }


    @Override
    public String templatePath() {
        return "templates/mapper.ftl";
    }

    @Override
    public String fileNameFormat() {
        return "%sMapper";
    }

    @Override
    public String pkg() {
        return "mapper";
    }
}
