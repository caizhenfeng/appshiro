package com.oa.appshiro.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.oa.appshiro.entity.SysRole;
import com.oa.appshiro.entity.SysUserRole;
import org.apache.ibatis.annotations.*;

import java.util.Set;

/**
 * @author zhenfengCai
 * @create 2020-04-29 13:14
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
    @Select("select role_id,user_id from sys_user_role where user_id=#{id}")
    @Results(id="RoleResorces",value={
            @Result(column="role_id", property="role_id"),
            @Result(column="role_id", property="sysRole",
                    many=@Many(select="com.oa.appshiro.Mapper.SysRoleMapper.findRoleByRoleid")),
            @Result(column="role_id",property="nodes",
                    many=@Many(select="com.oa.appshiro.Mapper.SysRoleResorcesMapper.findResorcesByRoleid"))
    })
    public Set<SysUserRole> findUserRolesById(@Param("id") Long id);
}
