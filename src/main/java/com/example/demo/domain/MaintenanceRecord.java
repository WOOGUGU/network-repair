package com.example.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 维修记录
 * @TableName maintenance_record
 */
@TableName(value ="maintenance_record")
@Data
public class MaintenanceRecord implements Serializable {
    /**
     * 
     */
    @TableId(value = "FK_workorder_number")
    private Integer FK_workorder_number;

    /**
     * 
     */
    @TableField(value = "FK_job_number")
    private Integer FK_job_number;

    /**
     * 
     */
    @TableField(value = "maintenance_record")
    private String maintenance_record;

    /**
     * 
     */
    @TableField(value = "maintenance_time")
    private Date maintenance_time;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}