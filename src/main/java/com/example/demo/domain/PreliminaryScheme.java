package com.example.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 初步方案
 * @TableName preliminary_scheme
 */
@TableName(value ="preliminary_scheme")
@Data
public class PreliminaryScheme implements Serializable {
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
    @TableField(value = "preliminary_program")
    private String preliminary_program;

    /**
     * 
     */
    @TableField(value = "preliminar_time")
    private Date preliminar_time;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}