package com.example.repair.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.repair.entity.WorkorderInformation;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ZBWKHH
 * @since 2022-01-07
 */
public interface WorkorderInformationService extends IService<WorkorderInformation> {
    WorkorderInformation getlatestorder(Long studentNumber);
}
