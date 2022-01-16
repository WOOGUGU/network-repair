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
 *
 * </p>
 *
 * @author ZBWKHH
 * @since 2022-01-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)

public class Notice implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "notice_number", type = IdType.AUTO)
    private Long noticeNumber;

    private String noticeContent;

    private String noticeTitle;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private Date releaseTime;

    @TableField("FK_job_number")
    private Long fkJobNumber;

    @TableLogic
    private Integer deleted;

    @Version
    private Integer version;

    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private Date updateTime;


//    public String getCreateTime() {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        return simpleDateFormat.format(createTime)+"\t"+ CalendarUtils.datOfWeek(createTime);
//    }
//
//    public String getReleaseTime() {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        return simpleDateFormat.format(releaseTime)+"\t"+CalendarUtils.datOfWeek(releaseTime);
//    }
//
//    public String getUpdateTime() {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        return simpleDateFormat.format(updateTime)+"\t"+CalendarUtils.datOfWeek(updateTime);
//    }


}
