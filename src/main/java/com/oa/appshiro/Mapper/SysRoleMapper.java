package com.oa.appshiro.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.oa.appshiro.entity.SysRole;
import com.oa.appshiro.entity.SysUser;
import org.apache.ibatis.annotations.*;

import java.util.Set;

/**
 * @author zhenfengCai
 * @create 2020-04-28 19:19
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
@Select("select id,name,description,available from sys_role where id=#{roleid}")
    public SysRole findRoleByRoleid(@Param("roleid") Long roleid);
}
