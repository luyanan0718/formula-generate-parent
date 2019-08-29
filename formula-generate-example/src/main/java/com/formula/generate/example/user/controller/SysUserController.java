package  com.formula.generate.example.user.controller;

import com.formula.generate.example.user.service.SysUserService ;
import io.swagger.annotations.Api ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 系统用户表 controller
 * </p>
 *
 * @author luyanan
 * @since 2019-08-29 
*/
@Api(description = "系统用户表")
@RestController
@RequestMapping("sys/user")
public class SysUserController {


    @Autowired
    private SysUserService sysUserService;

}
