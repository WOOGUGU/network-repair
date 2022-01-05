package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.domain.Notice;
import com.example.demo.service.NoticeService;
import com.example.demo.mapper.NoticeMapper;
import org.springframework.stereotype.Service;

/**
* @author WOOGUGU
* @description 针对表【notice】的数据库操作Service实现
* @createDate 2022-01-05 15:44:33
*/
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice>
    implements NoticeService{

}




