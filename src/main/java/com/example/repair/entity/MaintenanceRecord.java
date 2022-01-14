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
 * 维修记录
 * </p>
 *
 * @author ZBWKHH
 * @since 2022-01-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "MaintenanceRecord对象", description = "维修记录")
public class MaintenanceRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "FK_workorder_number", type = IdType.INPUT)
    private Long fkWorkorderNumber;

    @TableField("FK_job_number")
    private Long fkJobNumber;

    private String maintenanceRecord;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date maintenanceTime;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @Version
    private Integer version;

    @TableLogic
    private Integer deleted;


    public String getCreateTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return simpleDateFormat.format(createTime)+"\t"+ CalendarUtils.datOfWeek(createTime);
    }

    public String getUpdateTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(updateTime)+"\t"+CalendarUtils.datOfWeek(updateTime);
    }

    public String getMaintenanceTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(maintenanceTime)+"\t"+CalendarUtils.datOfWeek(maintenanceTime);
    }
}
