package com.example.repair.controller.notice;

import com.example.repair.entity.Notice;
import com.example.repair.service.impl.NoticeServiceImpl;
import com.example.repair.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class noticeController {
    @Autowired
    NoticeServiceImpl noticeService;
    //获得最新一条公告
    @GetMapping("/notice/getnewestnotice")
    public Object getNewestNotice(){

        Notice notice = noticeService.getNewestNotice();
        return ResultCode.getJson(notice);
    }
}
