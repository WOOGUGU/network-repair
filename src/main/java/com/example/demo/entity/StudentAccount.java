package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 学生账号
 *
 * @author WOOGUGU
 */

@Data
@Getter
@Setter
@ToString
@TableName("student_account")
public class StudentAccount {
    @TableId()
    private long studentNumber;
    private String name;
    private String passport;
}
