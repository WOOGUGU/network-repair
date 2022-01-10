package com.example.repair.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@ApiModel(value="PreliminaryScheme对象", description="初步方案")
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
    private Date preliminarTime;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableLogic
    private Integer deleted;

    @Version
    private Integer version;


}
