package com.example;

import com.example.repair.entity.AdministratorAccount;
import com.example.repair.entity.WorkorderInformation;
import com.example.repair.mapper.AdministratorAccountMapper;
import com.example.repair.service.impl.WorkorderInformationServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class Demo01ApplicationTests {
    @Autowired
    WorkorderInformationServiceImpl wisi;
    @Autowired
    AdministratorAccountMapper administratorAccountMapper;

    @Test
    void contextLoads() {

        List<WorkorderInformation> wisii = wisi.list(null);
        wisii.forEach(System.out::println);
    }

    @Test
    void insertTest() {
        AdministratorAccount administratorAccount = new AdministratorAccount();
        administratorAccount.setJobNumber(9L);
        administratorAccount.setName("user8");
        administratorAccount.setPassport("password");
        administratorAccountMapper.insert(administratorAccount);

    }

    @Test
    void updateTest() {
        AdministratorAccount administratorAccount = new AdministratorAccount();
        administratorAccount.setJobNumber(7L);
        administratorAccount.setName("user88");
        administratorAccount.setPassport("passwor2");
        administratorAccountMapper.updateById(administratorAccount);

    }

    @Test
    void LockTest() {
        AdministratorAccount administratorAccount = new AdministratorAccount();
        AdministratorAccount administratorAccount1 = new AdministratorAccount();
        administratorAccount.setJobNumber(7L);
        administratorAccount.setName("user88");
        administratorAccount.setPassport("passwor2");
        administratorAccount1.setJobNumber(7L);
        administratorAccount1.setName("ad07");
        administratorAccount1.setPassport("passwor01");
        administratorAccountMapper.updateById(administratorAccount1);
        administratorAccountMapper.updateById(administratorAccount);

    }


}
