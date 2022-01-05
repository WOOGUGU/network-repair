package com.example.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName workorder_information
 */
@TableName(value ="workorder_information")
@Data
public class WorkorderInformation implements Serializable {
    /**
     * 
     */
    @TableId(value = "workorder_number", type = IdType.AUTO)
    private Integer workorder_number;

    /**
     * 
     */
    @TableField(value = "initiation_time")
    private Date initiation_time;

    /**
     * 
     */
    @TableField(value = "contact_information")
    private Integer contact_information;

    /**
     * 
     */
    @TableField(value = "address")
    private String address;

    /**
     * 
     */
    @TableField(value = "workorder_content")
    private String workorder_content;

    /**
     * 
     */
    @TableField(value = "picture_address")
    private String picture_address;

    /**
     * 1：待预处理；2：待维修；3：工单完成
     */
    @TableField(value = "workorder_state")
    private String workorder_state;

    /**
     * 
     */
    @TableField(value = "FK_student_number")
    private Integer FK_student_number;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}