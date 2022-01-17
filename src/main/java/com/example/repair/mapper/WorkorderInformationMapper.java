package com.example.repair.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import java.sql.Timestamp;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.repair.entity.WorkorderInformation;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author ZBWKHH
 * @since 2022-01-07
 */
@Mapper
public interface WorkorderInformationMapper extends BaseMapper<WorkorderInformation> {
    WorkorderInformation getByFkStudentNumberAndMaxInitiationTime(
            @Param("fkStudentNumber") Long fkStudentNumber
    );

}
