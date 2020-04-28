package com.oa.appshiro.Shiro.Config;
import com.oa.appshiro.Shiro.credentials.RetryLimitCredentialsMatcher;
import com.oa.appshiro.Shiro.realm.ShiroRealm;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro配置类
 */
@Configuration

public class ShiroConfig {
    // 1、创建ShiroFilterFactoryBean,该Bean是入口，可以拦截所有资源
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        // 设置登录跳转页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        /**
         * Shiro内置过滤器：实现权限相关的拦截
         *      常用过滤器：
         *          anon（认证用）：无需认证（登录）即可访问
         *          authc（认证用）：必须认证才可访问
         *          user（少用）：使用rememberMe功能可以访问
         *          perms（授权用）：必须得到资源权限才可访问
         *          role（授权用）：必须得到角色权限才可访问
         */
        Map<String, String> filterMap = new LinkedHashMap<>();

        // 放行登录请求
        filterMap.put("/doLogin", "anon");
        filterMap.put("/shiro/log_in.do", "anon");

        // 配置退出过滤器，退出代码Shiro已经实现
        filterMap.put("/shiro/logout.do", "logout");

        // 过滤链定义，从上向下顺序执行，一般将/*放在最下边
        filterMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    // 2、创建DefaultWebSecurityManager,shiro安全管理器
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("shiroRealm") ShiroRealm myRealm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        // 关联Realm
        defaultWebSecurityManager.setRealm(myRealm);
        return defaultWebSecurityManager;
    }
    @Bean(name = "shiroRealm")
    //@Qualifier注解来指明注入的实例
    public ShiroRealm shiroRealm(@Qualifier("credentialsMatcher") RetryLimitCredentialsMatcher matcher) {
        ShiroRealm shiroRealm = new ShiroRealm();
        shiroRealm.setCredentialsMatcher(credentialsMatcher());
        return shiroRealm;
    }
    /**
     * 凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     * 所以我们需要修改下doGetAuthenticationInfo中的代码;
     * ）
     *
     * @return
     */
    @Bean(name = "credentialsMatcher")
    public RetryLimitCredentialsMatcher credentialsMatcher() {
        return new RetryLimitCredentialsMatcher();
    }

}