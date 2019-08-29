package  ${classPkg};

<#list   config.imports as import >
import ${import} ;
</#list>
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * ${entity.comment} serviceImpl
 * </p>
 *
 * @author ${author}
 * @since ${since}
*/
<#list  config.annotations as annotation>
${annotation}
</#list>
@Service
public class ${className}  implements ${serviceConfig.className} {


   @Autowired
   private ${manageConfig.className} ${manageConfig.className?uncap_first};


}
