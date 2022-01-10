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
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@ApiModel(value="WorkorderInformation对象", description="")
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

        @ApiModelProperty(value = "1：待预处理；2：待维修；3：工单完成")
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
