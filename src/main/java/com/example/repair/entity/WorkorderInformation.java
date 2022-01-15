package com.example.repair.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author ZBWKHH
 * @since 2022-01-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)

public class WorkorderInformation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "workorder_number", type = IdType.AUTO)
    private Long workorderNumber;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date initiationTime;

    private String contactInformation;

    private String address;

    private String workorderContent;

    private String pictureAddress;

    private String fixedTime;

    private String evaluationStatus;

    private Float maintenanceSatisfaction;

    private String evaluation;

    private String workorderState;

    @TableField("FK_student_number")
    private Long fkStudentNumber;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableLogic
    private Integer deleted;

    @Version
    private Integer version;


}
