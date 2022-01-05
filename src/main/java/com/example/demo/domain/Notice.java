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
 * @TableName notice
 */
@TableName(value ="notice")
@Data
public class Notice implements Serializable {
    /**
     * 
     */
    @TableId(value = "notice_number", type = IdType.AUTO)
    private Integer notice_number;

    /**
     * 
     */
    @TableField(value = "notice_content")
    private String notice_content;

    /**
     * 
     */
    @TableField(value = "notice_title")
    private String notice_title;

    /**
     * 
     */
    @TableField(value = "release_time")
    private Date release_time;

    /**
     * 
     */
    @TableField(value = "FK_job_number")
    private Integer FK_job_number;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}