package com.example.repaire.service.impl;

import com.example.repaire.entity.StudentAccount;
import com.example.repaire.mapper.StudentAccountMapper;
import com.example.repaire.service.StudentAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
