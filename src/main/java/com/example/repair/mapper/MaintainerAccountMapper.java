package com.example.repair.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.repair.entity.MaintainerAccount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author ZBWKHH
 * @since 2022-01-07
 */
@Mapper
public interface MaintainerAccountMapper extends BaseMapper<MaintainerAccount> {
    @Select("select job_number, name,sex,contact_information from maintainer_account")
    List<MaintainerAccount> selectJobNumberAndName();
}
