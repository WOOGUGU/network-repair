package com.example;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.repair.entity.MaintainerAccount;
import com.example.repair.entity.Notice;
import com.example.repair.mapper.MaintainerAccountMapper;
import com.example.repair.service.AdministratorAccountService;
import com.example.repair.service.impl.MaintainerAccountServiceImpl;
import com.example.repair.service.impl.NoticeServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


@SpringBootTest
class Demo01ApplicationTests {
    @Autowired
    AdministratorAccountService administratorAccountService;
    @Autowired
    MaintainerAccountServiceImpl maintainerAccountService;
    @Autowired
    MaintainerAccountMapper maintainerAccountMapper;
    @Autowired
    NoticeServiceImpl noticeService;

    @Test
    void contextLoads() {
        String path = System.getProperty("user.dir");
        System.out.println(path);
    }

    @Test
    void insertTest() {

    }

    @Test
    void updateTest() {


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
    @Test
    public void Datashow() {
        List<MaintainerAccount> maintainerAccountList = maintainerAccountService.list(null);
        maintainerAccountList.forEach(System.out::println);
    }

    @Test
    public void getWeekOfDate() {

        Date date = new Date();
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        System.out.println(w);
    }

    @Test
    public void noticeTest() {
        System.out.println(noticeService.getNewestNotice());
    }

    @Test
    public void JsonTest() {
        Notice notice = noticeService.getById(1L);
        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(notice));
//        jsonObject.put("release_time")

    }


}
