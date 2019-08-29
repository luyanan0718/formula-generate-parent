package  com.formula.generate.example.user.service.impl;

import com.formula.generate.example.user.service.SysRoleService ;
import com.formula.generate.example.user.manage.SysRoleManage ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 系统 角色表 serviceImpl
 * </p>
 *
 * @author luyanan
 * @since 2019-08-29 
*/
@Service
public class SysRoleServiceImpl  implements SysRoleService {


   @Autowired
   private SysRoleManage sysRoleManage;


}
