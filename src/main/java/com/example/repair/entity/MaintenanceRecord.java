package com.example.repair.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
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
public class MaintenanceRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "FK_workorder_number", type = IdType.INPUT)
    private Long fkWorkorderNumber;

    @TableField("FK_job_number")
    private Long fkJobNumber;

    private String maintenanceRecord;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private Date maintenanceTime;

    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private Date updateTime;

    @Version
    private Integer version;

    @TableLogic
    private Integer deleted;


}
