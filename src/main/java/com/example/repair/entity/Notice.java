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
 * 
 * </p>
 *
 * @author ZBWKHH
 * @since 2022-01-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Notice对象", description="")
public class Notice implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "notice_number", type = IdType.AUTO)
    private Integer noticeNumber;

    private String noticeContent;

    private String noticeTitle;

    private Date releaseTime;

    @TableField("FK_job_number")
    private Integer fkJobNumber;

    @TableLogic
    private Date deleted;

    @Version
    private Date version;

    @TableField(fill = FieldFill.INSERT)
    private Integer createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Integer updateTime;


}
