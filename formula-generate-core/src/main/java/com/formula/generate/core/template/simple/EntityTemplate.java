package com.formula.generate.core.template.simple;

import cn.hutool.core.util.StrUtil;
import com.formula.generate.core.core.TableFieldEntity;
import com.formula.generate.core.core.TableInfoEntity;
import com.formula.generate.core.core.ITemplate;
import com.formula.generate.core.core.TemplateConfig;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author luyanan
 * @since 2019/8/26
 * <p>实体类模板</p>
 **/
public class EntityTemplate implements ITemplate {

    private boolean swagger = false;
    private boolean mybatisPlus = false;

    String[] swaggerImport = {"io.swagger.annotations.ApiModel"
            , " io.swagger.annotations.ApiModelProperty"};
    String[] swaggerAnnotation = {};

    /**********************  Mybatis PLus  ******************************************/
    String[] mybatisPlusImport = {"com.baomidou.mybatisplus.annotation.*"};
    String[] mybatisPlusAnnotation = {};

    public EntityTemplate(boolean swagger, boolean mybatisPlus) {
        this.swagger = swagger;
        this.mybatisPlus = mybatisPlus;
    }

    public EntityTemplate() {
    }


    @Override
    public void before(List<TableInfoEntity> tableInfoEntities, TemplateConfig config) {
        config.put("swagger", this.swagger);
        config.put("mybatisPlus", mybatisPlus);
        if (swagger) {
            config.setImports(swaggerImport);
            config.setAnnotations(swaggerAnnotation);
        }
        if (mybatisPlus) {
            config.setImports(mybatisPlusImport);
            config.setAnnotations(mybatisPlusAnnotation);
        }

    }


    @Override
    public void handler(TableInfoEntity tableInfoEntity, TemplateConfig config) {

        List<String> tableFieldImports = tableInfoEntity
                .getTableFieldEntityList()
                .stream()
                .map(TableFieldEntity::getColumnImport)
                .filter(a -> StrUtil.isNotBlank(a))
                .collect(Collectors.toList());

        config.setImports(tableFieldImports);
    }

    @Override
    public String templatePath() {
        return "templates/entity.ftl";
    }

    @Override
    public String fileNameFormat() {
        return "%sEntity";
    }

    @Override
    public String pkg() {
        return "entity";
    }
}
