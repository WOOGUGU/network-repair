package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.domain.WorkorderInformation;
import com.example.demo.service.WorkorderInformationService;
import com.example.demo.mapper.WorkorderInformationMapper;
import org.springframework.stereotype.Service;

/**
* @author WOOGUGU
* @description 针对表【workorder_information】的数据库操作Service实现
* @createDate 2022-01-05 15:44:33
*/
@Service
public class WorkorderInformationServiceImpl extends ServiceImpl<WorkorderInformationMapper, WorkorderInformation>
    implements WorkorderInformationService{

}




