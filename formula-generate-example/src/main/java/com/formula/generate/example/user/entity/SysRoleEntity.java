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
 * 系统 角色表
 * </p>
 *
 * @author luyanan
 * @since 2019-08-29 
*/
@ApiModel(value = "系统 角色表")
@TableName("sys_role")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysRoleEntity implements Serializable{

private static final long serialVersionUID = 1L;

    /**
    * 
    */
    @ApiModelProperty(value = "")
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
    * 角色名称
    */
    @ApiModelProperty(value = "角色名称")
    @TableField(value = "role_name")
    private String roleName;

    /**
    * 备注
    */
    @ApiModelProperty(value = "备注")
    @TableField(value = "remark")
    private String remark;

    /**
    * 创建人
    */
    @ApiModelProperty(value = "创建人")
    @TableField(value = "create_by")
    private Long createBy;

    /**
    * 创建时间
    */
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time")
    private Date createTime;

    /**
    * 修改人
    */
    @ApiModelProperty(value = "修改人")
    @TableField(value = "update_by")
    private Long updateBy;

    /**
    * 修改时间
    */
    @ApiModelProperty(value = "修改时间")
    @TableField(value = "update_time")
    private Date updateTime;



}
