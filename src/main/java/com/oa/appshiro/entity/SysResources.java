package com.oa.appshiro.entity;
import com.baomidou.mybatisplus.annotations.TableName;
import com.oa.appshiro.Object.AbstractDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Transient;
import java.util.List;

@TableName("sys_resources")
@Data
@EqualsAndHashCode(callSuper = false)
public class SysResources extends AbstractDO {
    private String name;
    private String type;
    private String url;
    private String permission;
    private Long parentId;
    private Integer sort;
    private Boolean external;
    private Boolean available;
    private String icon;


}
