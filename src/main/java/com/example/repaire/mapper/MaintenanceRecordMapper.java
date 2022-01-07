package com.example.repaire.mapper;

import com.example.repaire.entity.MaintenanceRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 维修记录 Mapper 接口
 * </p>
 *
 * @author ZBWKHH
 * @since 2022-01-07
 */
@Mapper
public interface MaintenanceRecordMapper extends BaseMapper<MaintenanceRecord> {

}
