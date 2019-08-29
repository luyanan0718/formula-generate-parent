package  com.formula.generate.example.user.manage.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl ;
import com.formula.generate.example.user.manage.SysRoleManage ;
import com.formula.generate.example.user.mapper.SysRoleMapper ;
import com.formula.generate.example.user.entity.SysRoleEntity ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 系统 角色表 manageImpl
 * </p>
 *
 * @author luyanan
 * @since 2019-08-29 
*/
@Service
public class SysRoleManageImpl extends ServiceImpl<SysRoleMapper, SysRoleEntity> implements SysRoleManage {

}
