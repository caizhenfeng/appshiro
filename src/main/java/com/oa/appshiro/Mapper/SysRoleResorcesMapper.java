package com.oa.appshiro.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.oa.appshiro.entity.SysRole;
import com.oa.appshiro.entity.SysRoleResources;
import com.oa.appshiro.entity.SysUser;
import com.oa.appshiro.entity.SysUserRole;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Set;

/**
 * @author zhenfengCai
 * @create 2020-04-29 9:48
 */
@Mapper
public interface SysRoleResorcesMapper extends BaseMapper<SysRoleResources> {

    @Select("select role_id,resources_id from sys_role_resources where role_id=#{role_id}")
    @Results(id="RoleResourcesMap",value={
            @Result(column="role_id",property="role_id"),
            @Result(column="resources_id",property="resources_id"),
            @Result(column="resources_id",property="sysResources",
                    many=@Many(select="com.oa.appshiro.Mapper.SysResourcesMapper.findResourcesById"))
    })
    public List<SysRoleResources> findResorcesByRoleid(@Param("role_id") Long role_id);
}
