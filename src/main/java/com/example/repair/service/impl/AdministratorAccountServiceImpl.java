package com.example.repair.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.repair.entity.AdministratorAccount;
import com.example.repair.mapper.AdministratorAccountMapper;
import com.example.repair.service.AdministratorAccountService;
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
public class AdministratorAccountServiceImpl extends ServiceImpl<AdministratorAccountMapper, AdministratorAccount> implements AdministratorAccountService {
    @Autowired
    AdministratorAccountMapper administratorAccountMapper;
    @Override
    public int deleteMaintainerBack(Long maintainer_number) {
        return  administratorAccountMapper.deleteMaintainerBack(maintainer_number);
    }
}
