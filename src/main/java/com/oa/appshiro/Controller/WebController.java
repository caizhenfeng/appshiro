package com.oa.appshiro.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhenfengCai
 * @create 2020-04-23 17:04
 */
@Controller()
@RequestMapping("/home")
public class WebController {

    @RequestMapping("login.do")
    public String log_in(){
        return "login";
    }
}
