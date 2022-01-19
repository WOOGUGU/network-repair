package com.example.repair.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.repair.entity.AdministratorAccount;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author ZBWKHH
 * @since 2022-01-07
 */
@Mapper
public interface AdministratorAccountMapper extends BaseMapper<AdministratorAccount> {
        public  int deleteMaintainerBack(Long maintainer_number);
}
