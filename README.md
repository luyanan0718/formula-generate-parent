# 代码生成器,

##  优点

 高扩展性,几乎可以定制你的各种需要

## 已经实现

- 数据库文档模板生成
- 基于controller/service/manage/mapper 层的代码生成,可配置是否使用swagger和mybatisPlus
##  预计实现
- 基于web的模板生成
  - 可以多数据源
  - 在线预览或者修改模板
  
- 基于maven插件的代码生成
- 支持多种数据库

## 使用文档

### 使用现有模板

```java
         //  设置数据源
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/manage?characterEncoding=utf8&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("rootroot");
        GenerateBootstrap bootstrap = new GenerateBootstrap();
        // 配置全局配置文件
        GlobalConfig globalConfig = new GlobalConfig();
        bootstrap.setGlobalConfig(globalConfig
                // 作者
                .setAuthor("luyanan")
                // 是否输出到当前项目
                .setOutPutFileInThisProject()
                // 指定的目录
//                .setOutPutFile("D:/")
                // 父包路径
                .setParentPkg("com.formula.generate.example"));
        bootstrap
                .setDataSource(dataSource)
                // 设置模板引擎
                .setTemplateEngine(new FreemarkerTemplateEngine())
                // 包含的表
                .setInclude("sys_user", "sys_role")
                // 需要排除的表
//                .setExclude("sys_.*")
                // 设置模板组
                // 代码生成
                .setTemplateGroups(new SimpleTemplateGroup(true, true))
                //  数据库文档生成
//                .setTemplateGroups(new SimpleDocTemplateGroup())
                .execute();
```

###    创建模板

####  1. 创建模板

1. 实现 ITemplate

   ```java
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
   
   ```

2. 实现相应的方法

####  2. 创建模板组

1.  实现 ITemplateGroup

```java
package com.formula.generate.core.template.simple;

import com.formula.generate.core.config.GlobalConfig;
import com.formula.generate.core.core.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luyanan
 * @since 2019/8/26
 * <p>默认的模板分组</p>
 **/
public class SimpleTemplateGroup extends AbstractTemplateGroup {
    private boolean swagger = false;
    private boolean mybatisPlus = false;

    public SimpleTemplateGroup(boolean swagger, boolean mybatisPlus) {
        this.swagger = swagger;
        this.mybatisPlus = mybatisPlus;
    }

    public SimpleTemplateGroup() {
    }

    @Override
    public String name() {
        return this.getClass().getName();
    }

    @Override
    public List<ITemplate> templates(GlobalConfig globalConfig, TableInfoEntity tableInfoEntity) {
        List<ITemplate> templates = new ArrayList<>();
        EntityTemplate entityTemplate = new EntityTemplate(swagger, mybatisPlus);
        TemplateConfig entityConfig = entityTemplate.config(globalConfig, module(), tableInfoEntity.getEntityName());
        templates.add(entityTemplate);
        MapperTemplate mapperTemplate = new MapperTemplate(entityConfig, mybatisPlus);
        templates.add(mapperTemplate);
        TemplateConfig mapperConfig = mapperTemplate.config(globalConfig, module(), tableInfoEntity.getEntityName());
        // mapper  xml
        MapperXmlTemplate mapperXmlTemplate = new MapperXmlTemplate(entityConfig, mapperConfig);
        templates.add(mapperXmlTemplate);
        //  mansage层 模板
        ManageTemplate manageTemplate = new ManageTemplate();
        TemplateConfig manageConfig = manageTemplate.config(globalConfig, module(), tableInfoEntity.getEntityName());
        templates.add(manageTemplate);
        ManageImplTemplate manageImplTemplate = new ManageImplTemplate(mybatisPlus, entityConfig, mapperConfig, manageConfig);
        templates.add(manageImplTemplate);
        // service层
        ServiceTemplate serviceTemplate = new ServiceTemplate();
        TemplateConfig serviceConfig = serviceTemplate.config(globalConfig, module(), tableInfoEntity.getEntityName());
        templates.add(serviceTemplate);
        ServiceImplTemplate serviceImplTemplate = new ServiceImplTemplate(manageConfig, serviceConfig);
        templates.add(serviceImplTemplate);
        //  controller层
        ControllerTemplate controllerTemplate = new ControllerTemplate(serviceConfig, swagger);
        templates.add(controllerTemplate);

        return templates;
    }


    @Override
    public String module() {
        return "user";
    }




}

```

2. 实现相关的方法

#### 3. 使用创建的模板组即可

```java
      //  设置数据源
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/manage?characterEncoding=utf8&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("rootroot");
        GenerateBootstrap bootstrap = new GenerateBootstrap();
        // 配置全局配置文件
        GlobalConfig globalConfig = new GlobalConfig();
        bootstrap.setGlobalConfig(globalConfig
                // 作者
                .setAuthor("luyanan")
                // 是否输出到当前项目
                .setOutPutFileInThisProject()
                // 指定的目录
//                .setOutPutFile("D:/")
                // 父包路径
                .setParentPkg("com.formula.generate.example"));
        bootstrap
                .setDataSource(dataSource)
                // 设置模板引擎
                .setTemplateEngine(new FreemarkerTemplateEngine())
                // 包含的表
                .setInclude("sys_user", "sys_role")
                // 需要排除的表
//                .setExclude("sys_.*")
                // 设置模板组
                .setTemplateGroups(创建的模板组)

                .execute();
```

