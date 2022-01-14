package com.example.repair.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.example.repair.util.CalendarUtils;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author ZBWKHH
 * @since 2022-01-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "AdministratorAccount对象", description = "")
public class AdministratorAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "job_number", type = IdType.INPUT)
    private Long jobNumber;

    private String name;

    private String passport;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    @TableLogic
    private Integer deleted;

    @Version
    private Integer version;

    public String getCreateTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return simpleDateFormat.format(createTime)+"\t"+ CalendarUtils.datOfWeek(createTime);
    }

    public String getUpdateTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(updateTime)+"\t"+CalendarUtils.datOfWeek(updateTime);
    }
}
