package com.oa.appshiro.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.oa.appshiro.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhenfengCai
 * @create 2020-04-27 15:42
 */
@Mapper
public interface SysUserMapper extends BaseMapper<User> {
}
