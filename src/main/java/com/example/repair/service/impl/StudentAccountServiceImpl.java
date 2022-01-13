package com.example.repair.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.repair.entity.StudentAccount;
import com.example.repair.mapper.StudentAccountMapper;
import com.example.repair.service.StudentAccountService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 学生账户表 服务实现类
 * </p>
 *
 * @author ZBWKHH
 * @since 2022-01-07
 */
@Service
public class StudentAccountServiceImpl extends ServiceImpl<StudentAccountMapper, StudentAccount> implements StudentAccountService {

}
