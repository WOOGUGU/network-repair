package com.example.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 学生账户表
 * @TableName student_account
 */
@TableName(value ="student_account")
@Data
public class StudentAccount implements Serializable {
    /**
     * 
     */
    @TableId(value = "student_number")
    private Integer student_number;

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