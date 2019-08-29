package  ${classPkg};

<#list   config.imports as import >
import ${import} ;
</#list>
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * ${entity.comment} controller
 * </p>
 *
 * @author ${author}
 * @since ${since}
*/
<#list  config.annotations as annotation>
 ${annotation}
</#list>
<#if swagger ==  true>
@Api(description = "${entity.comment}")
</#if>
@RestController
@RequestMapping("${config.requestMapping}")
public class ${className} {


    @Autowired
    private ${serviceConfig.className} ${serviceConfig.className?uncap_first};

}
