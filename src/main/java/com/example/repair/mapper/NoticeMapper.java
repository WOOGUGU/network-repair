package com.example.repair.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.repair.entity.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author ZBWKHH
 * @since 2022-01-07
 */
@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {
    //获得最近发布的公告
    @Select("SELECT * FROM notice WHERE release_time = (SELECT MAX(release_time)  FROM notice)")
    Notice newestNotice();
}
