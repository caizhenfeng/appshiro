package com.oa.appshiro.Controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhenfengCai
 * @create 2020-04-22 13:37
 */
@RestController
public class hellow {
    //@RequiresPermissions("role:kaoqing")
    @RequiresRoles("role:kaoqing")
    @GetMapping("/dept/{id}")
    public String getDept(@PathVariable("id") Integer id){
        return "Gitub"+id;
    }

}
