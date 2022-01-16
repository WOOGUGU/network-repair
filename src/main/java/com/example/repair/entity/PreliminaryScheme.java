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
 * 初步方案
 * </p>
 *
 * @author ZBWKHH
 * @since 2022-01-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PreliminaryScheme implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "FK_workorder_number", type = IdType.INPUT)
    private Long fkWorkorderNumber;

    @TableField("FK_job_number")
    private Long fkJobNumber;

    @TableField("FK_Maintainer_Account")
    private Long fkMaintainerAccount;

    private String preliminaryProgram;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private Date preliminarTime;

    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private Date updateTime;

    @TableLogic
    private Integer deleted;

    @Version
    private Integer version;


}
