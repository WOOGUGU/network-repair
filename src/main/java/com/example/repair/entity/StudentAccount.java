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
 * 学生账户表
 * </p>
 *
 * @author ZBWKHH
 * @since 2022-01-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "StudentAccount对象", description = "学生账户表")
public class StudentAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "student_number", type = IdType.INPUT)
    private Long studentNumber;

    private String name;

    private String passport;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
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
