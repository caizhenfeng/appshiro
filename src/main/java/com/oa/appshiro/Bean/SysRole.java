package com.oa.appshiro.Bean;

import java.util.Date;
import java.io.Serializable;

/**
 * (SysRole)实体类
 *
 * @author makejava
 * @since 2020-04-24 12:28:10
 */
public class SysRole implements Serializable {
    private static final long serialVersionUID = 241139878526711141L;
    private Object id;
    /**
    * 角色名
    */
    private String name;
    
    private String description;
    
    private Object available;
    /**
    * 添加时间
    */
    private Date createTime;
    /**
    * 更新时间
    */
    private Date updateTime;


    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getAvailable() {
        return available;
    }

    public void setAvailable(Object available) {
        this.available = available;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}