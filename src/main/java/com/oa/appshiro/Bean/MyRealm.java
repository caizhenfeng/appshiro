package com.oa.appshiro.Bean;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.context.annotation.Configuration;

public class MyRealm extends AuthorizingRealm {
    /**
     * 执行授权的逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权的逻辑");
        return null;
    }

    /**
     * 执行授权的逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        /**
         * 这里应该调用sql查询数据库对应信息,但是这里我先将数据写死
         */
        String name = "权志龙";
        String password = "123456";
        /**
         * authenticationToken 是由subject.login(token);传过来的参数
         */
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        /**
         * 编写shiro判断逻辑
         * 1,判断用户名
         * 2,判断密码
         */
        if(!token.getUsername().equals(name)){
            /**
             *  如果没有当前用户,我们只需要return null;而shiro底层会抛出 UnknownAccountException异常
             */
            return null;
        }
        return new SimpleAuthenticationInfo("",password,"");
    }
}