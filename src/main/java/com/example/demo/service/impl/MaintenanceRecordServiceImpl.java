package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.domain.MaintenanceRecord;
import com.example.demo.service.MaintenanceRecordService;
import com.example.demo.mapper.MaintenanceRecordMapper;
import org.springframework.stereotype.Service;

/**
* @author WOOGUGU
* @description 针对表【maintenance_record(维修记录)】的数据库操作Service实现
* @createDate 2022-01-05 15:44:33
*/
@Service
public class MaintenanceRecordServiceImpl extends ServiceImpl<MaintenanceRecordMapper, MaintenanceRecord>
    implements MaintenanceRecordService{

}




