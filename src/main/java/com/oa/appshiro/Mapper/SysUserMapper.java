package com.oa.appshiro.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.oa.appshiro.entity.SysUser;
import org.apache.ibatis.annotations.*;

/**
 * @author zhenfengCai
 * @create 2020-04-27 15:42
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    @Select("select id,username," +
            "password,nickname,mobile,email,qq,birthday" +
            ",gender,avatar,user_type,reg_ip,last_login_ip," +
            "last_login_time,login_count,remark,status"+
            " from sys_user "+
            "where id=#{userid}")
    @Results(id="userMap",value={
            @Result(column="id",property="id"),
            @Result(column="id",property="SysUserRole",
                    many=@Many(select="com.oa.appshiro.Mapper.SysUserRoleMapper.findUserRolesById"))
    })
    public SysUser getUserByUserID(@Param("userid") Long userid);
}
