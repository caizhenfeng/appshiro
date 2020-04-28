/**
 * MIT License
 * Copyright (c) 2018 yadong.zhang
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.oa.appshiro.Shiro.realm;


import com.oa.appshiro.Service.SysUserService;
import com.oa.appshiro.entity.User;
import com.oa.appshiro.enums.UserStatusEnum;
import com.oa.appshiro.enums.UserTypeEnum;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Shiro-密码输入错误的状态下重试次数的匹配管理
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/24 14:37
 * @since 1.0
 */
public class ShiroRealm extends AuthorizingRealm {

    @Resource
    private SysUserService userService;
    //@Resource
    //private SysResourcesService resourcesService;
    //@Resource
    //private SysRoleService roleService;

    /**
     * 提供账户信息返回认证信息（用户的角色信息集合）
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户的输入的账号.
        String username = (String) token.getPrincipal();
        User user = userService.getByUserName(username);
        if (user == null) {
            throw new UnknownAccountException("账号不存在！");
        }
        if (user.getStatus() != null && UserStatusEnum.DISABLE.getCode().equals(user.getStatus())) {
            throw new LockedAccountException("帐号已被锁定，禁止登录！");
        }
        // principal参数使用用户Id，方便动态刷新用户权限
        //new SimpleAuthenticationInfo(第一个参数username,第二个字段是user.getPassword(),第三个字段是realm)
        //看了几篇文章介绍说，这块对比逻辑是先对比username，但是username肯定是相等的，所以真正对比的是password。从这里传入的
        //password（这里是从数据库获取的）和token（filter中登录时生成的）中的password做对比，如果相同就允许登录，不相同就抛出
        //异常。如果验证成功，最终这里返回的信息authenticationInfo 的值与传入的第一个字段的值相同（我这里传的是user对象）。
        //ByteSource.Util.bytes(username),盐值加密，username是唯一值，该参数作用：如果用户不一样，密码一样，通过加盐出来的密码也是不一样的
//realmName:当前Realm对象的name,调用父类的getName()
        return new SimpleAuthenticationInfo(
                user.getId(),
                user.getPassword(),
                ByteSource.Util.bytes(username),
                getName()
        );


    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 权限认证，为当前登录的Subject授予角色和权限（角色的权限信息集合）
     */
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        // 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
//        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//
//        Long userId = (Long) SecurityUtils.getSubject().getPrincipal();
//
//        // 赋予角色
//        List<Role> roleList = roleService.listRolesByUserId(userId);
//        for (Role role : roleList) {
//            info.addRole(role.getName());
//        }
//
//        // 赋予权限
//        List<Resources> resourcesList = null;
//        User user = userService.getByPrimaryKey(userId);
//        if (null == user) {
//            return info;
//        }
//        // ROOT用户默认拥有所有权限
//        if (UserTypeEnum.ROOT.toString().equalsIgnoreCase(user.getUserType())) {
//            resourcesList = resourcesService.listAll();
//        } else {
//            resourcesList = resourcesService.listByUserId(userId);
//        }
//
//        if (!CollectionUtils.isEmpty(resourcesList)) {
//            Set<String> permissionSet = new HashSet<>();
//            for (Resources resources : resourcesList) {
//                String permission = null;
//                if (!StringUtils.isEmpty(permission = resources.getPermission())) {
//                    permissionSet.addAll(Arrays.asList(permission.trim().split(",")));
//                }
//            }
//            info.setStringPermissions(permissionSet);
//        }
//        return info;
//    }

}
