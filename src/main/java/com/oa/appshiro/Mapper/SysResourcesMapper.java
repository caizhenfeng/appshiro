package com.oa.appshiro.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.oa.appshiro.entity.SysResources;
import com.oa.appshiro.entity.SysRoleResources;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author zhenfengCai
 * @create 2020-04-29 9:46
 */
@Mapper
public interface SysResourcesMapper extends BaseMapper<SysResources> {
@Select("select id,name,type,url,permission,parent_id,sort,external,available,icon from sys_resources where id=#{resourcesId}")
    public SysResources findResourcesById(@Param("resourcesId") Long resourcesId);
}

