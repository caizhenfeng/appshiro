package com.oa.appshiro.Service;

import com.oa.appshiro.Object.AbstractService;
import com.oa.appshiro.entity.SysUser;

/**
 * @author zhenfengCai
 * @create 2020-04-28 19:10
 */
public interface SysUserService extends AbstractService<SysUser,Long> {
    public SysUser getByUserName(String userName);
    public SysUser getOneByEntity(SysUser entity);
    public SysUser getUserInfoByUserID(Long userid);
}
