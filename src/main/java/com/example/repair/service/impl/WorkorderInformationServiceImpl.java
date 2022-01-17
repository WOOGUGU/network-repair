package com.example.repair.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.repair.entity.WorkorderInformation;
import com.example.repair.mapper.WorkorderInformationMapper;
import com.example.repair.service.WorkorderInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ZBWKHH
 * @since 2022-01-07
 */
@Service
public class WorkorderInformationServiceImpl extends ServiceImpl<WorkorderInformationMapper, WorkorderInformation>
        implements WorkorderInformationService {
    @Autowired
    WorkorderInformationMapper workorderInformationMapper;

    @Override
    public WorkorderInformation getlatestorder(Long studentNumber) {
        return workorderInformationMapper.getByFkStudentNumberAndMaxInitiationTime(studentNumber);
    }
}
