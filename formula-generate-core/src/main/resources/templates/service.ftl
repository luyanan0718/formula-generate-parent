package  ${classPkg};

<#list   config.imports as import >
import ${import} ;
</#list>
/**
 * <p>
 * ${entity.comment} service
 * </p>
 *
 * @author ${author}
 * @since ${since}
*/
<#list  config.annotations as annotation>
${annotation}
</#list>
public interface ${className}  {

}
