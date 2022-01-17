package com.example.repair.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.repair.entity.MaintainerAccount;
import com.example.repair.mapper.MaintainerAccountMapper;
import com.example.repair.service.MaintainerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ZBWKHH
 * @since 2022-01-07
 */
@Service
public class MaintainerAccountServiceImpl extends ServiceImpl<MaintainerAccountMapper, MaintainerAccount> implements MaintainerAccountService {
    @Autowired
    MaintainerAccountMapper maintainerAccountMapper;

    @Override
    public List<MaintainerAccount> maintainerlist() {
        return maintainerAccountMapper.selectBasicInformation();
    }
}
