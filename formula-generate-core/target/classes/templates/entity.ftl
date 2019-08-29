package  ${classPkg};

<#list   config.imports as import >
import ${import} ;
</#list>
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * <p>
 * ${entity.comment}
 * </p>
 *
 * @author ${author}
 * @since ${since}
*/
<#list  config.annotations as annotation>
${annotation}
</#list>
<#-- swagger 注解 -->
<#if  config.swagger ==  true>
@ApiModel(value = "${entity.comment}")
</#if>
<#-- mybatis plus 注解 -->
<#if  config.mybatisPlus ==  true>
@TableName("${entity.tableName}")
</#if>
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ${className} implements Serializable{

private static final long serialVersionUID = 1L;

<#list  entity.tableFieldEntityList as tableFieldEntity>
    /**
    * ${tableFieldEntity.comment}
    */
<#-- swagger 注解 -->
    <#if  config.swagger ==  true>
    @ApiModelProperty(value = "${tableFieldEntity.comment}")
    </#if>
<#-- mybatis plus 注解 -->
    <#if  config.mybatisPlus ==  true>
        <#if tableFieldEntity.primaryKey == true >
        <#-- 自增-->
            <#if tableFieldEntity.increment == true>
    @TableId(type = IdType.AUTO)
            <#else >
    @TableId(type = IdType.INPUT)
            </#if>
        <#else >
    @TableField(value = "${tableFieldEntity.jdbcFieldName}")
        </#if>
    </#if>
    private ${tableFieldEntity.columnType} ${tableFieldEntity.columnName};

</#list>


}
