package  com.formula.generate.example.user.manage.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl ;
import com.formula.generate.example.user.mapper.SysUserMapper ;
import com.formula.generate.example.user.manage.SysUserManage ;
import com.formula.generate.example.user.entity.SysUserEntity ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 系统用户表 manageImpl
 * </p>
 *
 * @author luyanan
 * @since 2019-08-29 
*/
@Service
public class SysUserManageImpl extends ServiceImpl<SysUserMapper, SysUserEntity> implements SysUserManage {

}
