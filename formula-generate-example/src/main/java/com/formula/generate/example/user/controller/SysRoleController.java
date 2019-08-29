package  com.formula.generate.example.user.controller;

import com.formula.generate.example.user.service.SysRoleService ;
import io.swagger.annotations.Api ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 系统 角色表 controller
 * </p>
 *
 * @author luyanan
 * @since 2019-08-29 
*/
@Api(description = "系统 角色表")
@RestController
@RequestMapping("sys/role")
public class SysRoleController {


    @Autowired
    private SysRoleService sysRoleService;

}
