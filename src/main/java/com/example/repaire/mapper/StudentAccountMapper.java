package com.example.repaire.mapper;

import com.example.repaire.entity.StudentAccount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 学生账户表 Mapper 接口
 * </p>
 *
 * @author ZBWKHH
 * @since 2022-01-07
 */
@Mapper
public interface StudentAccountMapper extends BaseMapper<StudentAccount> {

}
