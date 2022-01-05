package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.domain.StudentAccount;
import com.example.demo.service.StudentAccountService;
import com.example.demo.mapper.StudentAccountMapper;
import org.springframework.stereotype.Service;

/**
* @author WOOGUGU
* @description 针对表【student_account(学生账户表)】的数据库操作Service实现
* @createDate 2022-01-05 15:44:33
*/
@Service
public class StudentAccountServiceImpl extends ServiceImpl<StudentAccountMapper, StudentAccount>
    implements StudentAccountService{

}




