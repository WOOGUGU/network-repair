package com.example;

import com.example.repair.entity.AdministratorAccount;
import com.example.repair.mapper.MaintainerAccountMapper;
import com.example.repair.service.AdministratorAccountService;
import com.example.repair.service.impl.MaintainerAccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class Demo01ApplicationTests {
    @Autowired
    AdministratorAccountService administratorAccountService;
    @Autowired
    MaintainerAccountServiceImpl maintainerAccountService;
    @Autowired
    MaintainerAccountMapper maintainerAccountMapper;

    @Test
    void contextLoads() {
        maintainerAccountService.getJobNumberAndNameList();
    }

    @Test
    void insertTest() {
        AdministratorAccount administratorAccount = new AdministratorAccount();
        administratorAccount.setJobNumber(9L);
        administratorAccount.setName("user8");
        administratorAccount.setPassport("password");
        administratorAccountService.save(administratorAccount);

    }

    @Test
    void updateTest() {
        List<AdministratorAccount> administratorAccountList = new ArrayList<>();
        AdministratorAccount administratorAccount = new AdministratorAccount();
        AdministratorAccount administratorAccount1 = new AdministratorAccount();

        administratorAccount.setJobNumber(9L);
        administratorAccount.setName("ad08");
        administratorAccount.setPassport("123456");

        administratorAccount1.setJobNumber(10L);
        administratorAccount1.setName("ad04");
        administratorAccount1.setPassport("123456");


        administratorAccountList.add(administratorAccount);
        administratorAccountList.add(administratorAccount1);


        administratorAccountService.updateBatchById(administratorAccountList);

    }
//    @Test
//    void LockTest() {
//        AdministratorAccount administratorAccount=new AdministratorAccount();
//        AdministratorAccount administratorAccount1=new AdministratorAccount();
//        administratorAccount.setJobNumber(7L);
//        administratorAccount.setName("user88");
//        administratorAccount.setPassport("passwor2");
//        administratorAccount1.setJobNumber(7L);
//        administratorAccount1.setName("ad07");
//        administratorAccount1.setPassport("passwor01");
//        administratorAccountMapper.updateById(administratorAccount1);
//        administratorAccountMapper.updateById(administratorAccount);
//
//    }


}
