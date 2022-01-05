package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.domain.MaintainerAccount;
import com.example.demo.service.MaintainerAccountService;
import com.example.demo.mapper.MaintainerAccountMapper;
import org.springframework.stereotype.Service;

/**
* @author WOOGUGU
* @description 针对表【maintainer_account】的数据库操作Service实现
* @createDate 2022-01-05 15:44:33
*/
@Service
public class MaintainerAccountServiceImpl extends ServiceImpl<MaintainerAccountMapper, MaintainerAccount>
    implements MaintainerAccountService{

}




