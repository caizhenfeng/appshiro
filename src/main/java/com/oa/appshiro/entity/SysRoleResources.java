package com.oa.appshiro.entity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.oa.appshiro.Object.AbstractDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Set;

@TableName("sys_role_resources")
@Data
@EqualsAndHashCode(callSuper = false)
public class SysRoleResources extends AbstractDO {
    private Long role_id;
    private Long resources_id;
    @TableField(exist = false)
    private SysResources sysResources;
}
