package com.oa.appshiro.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.oa.appshiro.Until.PasswordUtil;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.io.Serializable;
@TableName("sys_user")
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 807616950799285040L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    private String username;
    /**
    * 登录密码
    */
    private String password;
    /**
    * 昵称
    */
    private String nickname;
    /**
    * 手机号
    */
    private String mobile;
    /**
    * 邮箱地址
    */
    private String email;
    /**
    * QQ
    */
    private String qq;
    /**
    * 生日
    */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date birthday;
    /**
    * 性别
    */
    private String gender;
    /**
    * 头像地址
    */
    private String avatar;
    /**
    * 超级管理员、管理员、普通用户
    */
    private String user_type;
    /**
    * 注册IP
    */
    private String reg_ip;
    /**
    * 最近登录IP
    */
    private String last_login_ip;
    /**
    * 最近登录时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date last_login_time;
    /**
    * 登录次数
    */
    private Integer login_count;
    /**
    * 用户备注
    */
    private String remark;
    /**
    * 用户状态
    */
    private String status;
    /**
    * 注册时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date create_time;
    /**
    * 更新时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date update_time;




}