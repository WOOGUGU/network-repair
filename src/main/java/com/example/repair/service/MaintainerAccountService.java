package com.example.repair.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.repair.entity.MaintainerAccount;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ZBWKHH
 * @since 2022-01-07
 */
public interface MaintainerAccountService extends IService<MaintainerAccount> {
    List<MaintainerAccount> getJobNumberAndNameList();
}
