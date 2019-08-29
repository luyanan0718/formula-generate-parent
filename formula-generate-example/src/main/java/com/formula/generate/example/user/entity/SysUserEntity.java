package  com.formula.generate.example.user.entity;

import java.util.Date ;
import  io.swagger.annotations.ApiModelProperty ;
import io.swagger.annotations.ApiModel ;
import com.baomidou.mybatisplus.annotation.* ;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * <p>
 * 系统用户表
 * </p>
 *
 * @author luyanan
 * @since 2019-08-29 
*/
@ApiModel(value = "系统用户表")
@TableName("sys_user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysUserEntity implements Serializable{

private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @ApiModelProperty(value = "id")
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
    * 邮箱
    */
    @ApiModelProperty(value = "邮箱")
    @TableField(value = "email")
    private String email;

    /**
    * 用户名
    */
    @ApiModelProperty(value = "用户名")
    @TableField(value = "user_name")
    private String userName;

    /**
    * 密码
    */
    @ApiModelProperty(value = "密码")
    @TableField(value = "pass_word")
    private String passWord;

    /**
    * 盐
    */
    @ApiModelProperty(value = "盐")
    @TableField(value = "salt")
    private String salt;

    /**
    * 手机号
    */
    @ApiModelProperty(value = "手机号")
    @TableField(value = "phone")
    private String phone;

    /**
    * 状态 100:冻结   101:正常
    */
    @ApiModelProperty(value = "状态 100:冻结   101:正常")
    @TableField(value = "status")
    private Integer status;

    /**
    * 删除标识 100:删除  101:正常
    */
    @ApiModelProperty(value = "删除标识 100:删除  101:正常")
    @TableField(value = "del_flag")
    private Integer delFlag;

    /**
    * 创建人id
    */
    @ApiModelProperty(value = "创建人id")
    @TableField(value = "create_by")
    private Long createBy;

    /**
    * 创建时间
    */
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time")
    private Date createTime;

    /**
    * 修改人id
    */
    @ApiModelProperty(value = "修改人id")
    @TableField(value = "update_by")
    private Long updateBy;

    /**
    * 修改时间
    */
    @ApiModelProperty(value = "修改时间")
    @TableField(value = "update_time")
    private Date updateTime;



}
