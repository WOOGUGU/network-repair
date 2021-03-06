package com.example.repair.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.repair.entity.Notice;
import com.example.repair.mapper.NoticeMapper;
import com.example.repair.service.NoticeService;
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
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {
    @Autowired
    NoticeMapper noticeMapper;


    @Override
    public Notice getNewestNotice() {
        return noticeMapper.newestNotice();
    }
}
