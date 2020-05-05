package com.oa.appshiro;

import com.oa.appshiro.Mapper.SysUserMapper;
import com.oa.appshiro.Service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class AppshiroApplicationTests {
    private static final transient Logger log = LoggerFactory.getLogger(AppshiroApplicationTests.class);
    @Autowired
private SysUserService sysUserService;

    @Test
    public void test01() {
        sysUserService.getUserInfoByUserID(new Long(2));
    }


}
