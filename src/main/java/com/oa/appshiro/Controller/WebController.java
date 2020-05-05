package com.oa.appshiro.Controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhenfengCai
 * @create 2020-04-23 17:04
 */
@Controller()

public class WebController {
    @RequestMapping("/login")
    public String log_in(){
        return "login";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

}
