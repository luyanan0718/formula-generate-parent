package  com.formula.generate.example.user.service.impl;

import com.formula.generate.example.user.service.SysUserService ;
import com.formula.generate.example.user.manage.SysUserManage ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 系统用户表 serviceImpl
 * </p>
 *
 * @author luyanan
 * @since 2019-08-29 
*/
@Service
public class SysUserServiceImpl  implements SysUserService {


   @Autowired
   private SysUserManage sysUserManage;


}
