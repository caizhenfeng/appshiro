
package com.oa.appshiro.entity;
import com.oa.appshiro.Object.BaseConditionVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper = false)
public class ResourceConditionVO extends BaseConditionVO {
    private String type;
}

