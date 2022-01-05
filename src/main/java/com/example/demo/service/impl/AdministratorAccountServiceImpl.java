package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.domain.AdministratorAccount;
import com.example.demo.service.AdministratorAccountService;
import com.example.demo.mapper.AdministratorAccountMapper;
import org.springframework.stereotype.Service;

/**
* @author WOOGUGU
* @description 针对表【administrator_account】的数据库操作Service实现
* @createDate 2022-01-05 15:44:33
*/
@Service
public class AdministratorAccountServiceImpl extends ServiceImpl<AdministratorAccountMapper, AdministratorAccount>
    implements AdministratorAccountService{

}




