package com.oa.appshiro.Service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.oa.appshiro.Mapper.SysUserMapper;
import com.oa.appshiro.Service.SysUserService;
import com.oa.appshiro.entity.SysResources;
import com.oa.appshiro.entity.SysRole;
import com.oa.appshiro.entity.SysUser;
import com.oa.appshiro.entity.SysUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhenfengCai
 * @create 2020-04-27 15:43
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    /**
     * 根据用户名查找
     *
     * @param userName
     * @return
     */
    public SysUser getByUserName(String userName) {
        SysUser sysUser = new SysUser();
        sysUser.setUsername(userName);
        return getOneByEntity(sysUser);
    }
    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果时抛出异常，查询条件使用等号
     *
     * @param entity
     * @return
     */
    public SysUser getOneByEntity(SysUser entity) {
        //判断传进来的参数值是否不为空值，如果为空就抛出异常throw new IllegalArgumentException(msg)，代码如果不捕捉处理这个异常，代码不往下执行，不为空代码继续向下执行。
        Assert.notNull(entity, "User不可为空！");
        List<SysUser> list=new ArrayList<SysUser>();
        list = sysUserMapper.selectList(
                new EntityWrapper<SysUser>().eq("username", entity.getUsername()));
        return 1 != list.size() ? null : list.get(0);
    }

    //根据用户的ID查找用户所对应的信息结果如下：
    //角色对应的多个角色，每个角色对应的多个操作
   /* SysUser(username=admin, password=gXp2EbyZ+sB/A6QUMhiUJQ==, nickname=管理员, mobile=15151551516, email=843977358@qq.com, qq=843977358, birthday=null, gender=null, avatar=null, user_type=ADMIN, reg_ip=0:0:0:0:0:0:0:1, last_login_ip=0:0:0:0:0:0:0:1, last_login_time=Thu May 17 21:08:30 CST 2018, login_count=13, remark=null, status=1,
            roles=[SysUserRole(user_id=2, role_id=2, sysRole=SysRole(name=role:admin, description=管理员, available=true),
    nodes=[SysRoleResources(role_id=2, resources_id=null, sysResources=SysResources(name=数据监控, type=menu, url=, permission=, parentId=null, sort=3, external=false, available=true, icon=fa fa-heartbeat)),
    SysRoleResources(role_id=2, resources_id=null, sysResources=SysResources(name=Druid监控, type=menu, url=/druid/index.html, permission=druid, parentId=null, sort=1, external=true, available=true, icon=)),
    SysRoleResources(role_id=2, resources_id=null, sysResources=SysResources(name=用户列表, type=menu, url=/users, permission=users, parentId=null, sort=1, external=false, available=true, icon=null)),
    SysRoleResources(role_id=2, resources_id=null, sysResources=SysResources(name=新增用户, type=button, url=null, permission=user:add, parentId=null, sort=2, external=false, available=true, icon=null)),
    SysRoleResources(role_id=2, resources_id=null, sysResources=SysResources(name=系统配置, type=menu, url=null, permission=null, parentId=null, sort=2, external=false, available=true, icon=fa fa-cogs)),
    SysRoleResources(role_id=2, resources_id=null, sysResources=SysResources(name=资源管理, type=menu, url=/resources, permission=resources, parentId=null, sort=1, external=false, available=true, icon=null)),
    SysRoleResources(role_id=2, resources_id=null, sysResources=SysResources(name=新增资源, type=button, url=null, permission=resource:add, parentId=null, sort=2, external=false, available=true, icon=null)),
    SysRoleResources(role_id=2, resources_id=null, sysResources=SysResources(name=角色管理, type=menu, url=/roles, permission=roles, parentId=null, sort=2, external=false, available=true, icon=)),
    SysRoleResources(role_id=2, resources_id=null, sysResources=SysResources(name=新增角色, type=button, url=null, permission=role:add, parentId=null, sort=2, external=false, available=true, icon=null)),
    SysRoleResources(role_id=2, resources_id=null, sysResources=SysResources(name=用户管理, type=menu, url=null, permission=null, parentId=null, sort=1, external=false, available=true, icon=fa fa-users))
            ]‘node结束
	)’第一个权限结束
	]’roles结束
)‘对象结束*/
    public SysUser getUserInfoByUserID(Long userid){
        SysUser user=sysUserMapper.getUserByUserID(userid);
        return user;
    }

    @Override
    public List<SysUser> listAll() {
        return null;
    }

    @Override
    public List<SysUser> listByEntity(SysUser entity) {
        return null;
    }

    @Override
    public SysUser insert(SysUser entity) {
        return null;
    }

    @Override
    public void insertList(List<SysUser> entities) {

    }

    @Override
    public boolean removeByPrimaryKey(Long primaryKey) {
        return false;
    }

    @Override
    public boolean update(SysUser entity) {
        return false;
    }

    @Override
    public boolean updateSelective(SysUser entity) {
        return false;
    }

    public SysUser getByPrimaryKey(Long userId){
        Assert.notNull(userId, "PrimaryKey不可为空！");
        List<SysUser> list=new ArrayList<SysUser>();
        list = sysUserMapper.selectList(
                new EntityWrapper<SysUser>().eq("id",userId));
        return 1 != list.size() ? null : list.get(0);

    }
    public void updateUserLastLoginInfo(SysUser sysUser){
        sysUserMapper.updateAllColumnById(sysUser);
    }
}
