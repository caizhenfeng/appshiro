package com.oa.appshiro.Shiro.realm;
import com.oa.appshiro.Service.SysRoleService;
import com.oa.appshiro.Service.impl.SysUserServiceImpl;
import com.oa.appshiro.entity.*;
import com.oa.appshiro.enums.UserStatusEnum;
import com.oa.appshiro.enums.UserTypeEnum;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
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
 */
public class ShiroRealm extends AuthorizingRealm {

    @Resource
    private SysUserServiceImpl userService;


    /**
     * 提供账户信息返回认证信息（用户的角色信息集合）
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户的输入的账号.
        String username = (String) token.getPrincipal();
        SysUser sysUser = userService.getByUserName(username);
        if (sysUser == null) {
            throw new UnknownAccountException("账号不存在！");
        }
        if (sysUser.getStatus() != null && UserStatusEnum.DISABLE.getCode().equals(sysUser.getStatus())) {
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
                sysUser.getId(),
                sysUser.getPassword(),
                ByteSource.Util.bytes(username),
                getName()
        );


    }



    /**
     * 权限认证，为当前登录的Subject授予角色和权限（角色的权限信息集合）
     * 需要开启shiro注解
     * 需要验证时才能进入该方法
     *
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Long userId = (Long) SecurityUtils.getSubject().getPrincipal();
        SysUser sysUser = userService.getUserInfoByUserID(userId);

        if (null == sysUser) {
            return info;
        }
        // 赋予角色
        List<SysUserRole> SysUserRoleList =sysUser.getSysUserRole();
        for (SysUserRole UserRole : SysUserRoleList) {
            info.addRole(UserRole.getSysRole().getName());

        }
        System.out.println(info.getRoles());
        // 赋予权限
        Set<String> permissionSet = new HashSet<>();
        for (SysUserRole UserRole : SysUserRoleList) {
            List<SysRoleResources> RoleResourcesList =UserRole.getNodes();
            for(SysRoleResources roleResources:RoleResourcesList){
                String permission = null;
                if (!StringUtils.isEmpty(permission = roleResources.getSysResources().getPermission())) {
                    permissionSet.addAll(Arrays.asList(permission.trim().split(",")));
                }
            }


        }
        info.setStringPermissions(permissionSet);
        return info;
    }
}
