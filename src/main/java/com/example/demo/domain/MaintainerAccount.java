package com.example.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName maintainer_account
 */
@TableName(value ="maintainer_account")
@Data
public class MaintainerAccount implements Serializable {
    /**
     * 
     */
    @TableId(value = "job_number")
    private Integer job_number;

    /**
     * 
     */
    @TableField(value = "name")
    private String name;

    /**
     * 
     */
    @TableField(value = "passport")
    private String passport;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}