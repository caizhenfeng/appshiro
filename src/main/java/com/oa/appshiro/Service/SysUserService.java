package com.oa.appshiro.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.oa.appshiro.Mapper.SysUserMapper;
import com.oa.appshiro.entity.User;
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
public class SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    /**
     * 根据用户名查找
     *
     * @param userName
     * @return
     */
    public User getByUserName(String userName) {
        User user = new User();
        user.setUsername(userName);
        return getOneByEntity(user);
    }
    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果时抛出异常，查询条件使用等号
     *
     * @param entity
     * @return
     */
    public User getOneByEntity(User entity) {
        //判断传进来的参数值是否不为空值，如果为空就抛出异常throw new IllegalArgumentException(msg)，代码如果不捕捉处理这个异常，代码不往下执行，不为空代码继续向下执行。
        Assert.notNull(entity, "User不可为空！");
        List<User> list=new ArrayList<User>();
        list = sysUserMapper.selectList(
                new EntityWrapper<User>().eq("username", entity.getUsername()));
        return 1 != list.size() ? null : list.get(0);
    }
    public User getByPrimaryKey(Long userId){
        Assert.notNull(userId, "PrimaryKey不可为空！");
        List<User> list=new ArrayList<User>();
        list = sysUserMapper.selectList(
                new EntityWrapper<User>().eq("id",userId));
        return 1 != list.size() ? null : list.get(0);

    }
    public void updateUserLastLoginInfo(User user){
        sysUserMapper.updateAllColumnById(user);
    }
}
