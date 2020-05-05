package com.oa.appshiro.entity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.oa.appshiro.Object.AbstractDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@TableName("sys_user_role")
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUserRole extends AbstractDO {
    private Long user_id;
    private Long role_id;
    @TableField(exist = false)
    private SysRole sysRole;
    @TableField(exist = false)
    private List<SysRoleResources> nodes;

}
