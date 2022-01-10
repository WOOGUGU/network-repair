package com.example.repair.controller.administer;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.repair.entity.AdministratorAccount;
import com.example.repair.entity.MaintainerAccount;
import com.example.repair.entity.PreliminaryScheme;
import com.example.repair.entity.WorkorderInformation;
import com.example.repair.service.AdministratorAccountService;
import com.example.repair.service.MaintainerAccountService;
import com.example.repair.service.PreliminarySchemeService;
import com.example.repair.service.impl.WorkorderInformationServiceImpl;
import com.example.repair.util.ResponseCode;
import com.example.repair.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 管理员Controller
 *
 * @author WOOGUGU
 */

@RestController
public class AdministerController {
    @Autowired
    WorkorderInformationServiceImpl workorderInformationService;
    @Autowired
    PreliminarySchemeService preliminarySchemeService;
    @Autowired
    MaintainerAccountService maintainerAccountService;
    @Autowired
    AdministratorAccountService administratorAccountService;

    // 管理员获取工单和初步方案
    @GetMapping("/administer/orderlist")
    public Object orderList() {
        QueryWrapper<WorkorderInformation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("workorder_state", 1);
        List<WorkorderInformation> workorderInformationList = workorderInformationService.list(queryWrapper);
        return ResultCode.getJson(workorderInformationList);
    }

    // 管理员获得某工单详细信息
    @GetMapping("/administer/getorder")
    public Object getOrder(Long workorder_number) {
        if (workorder_number == null) {
            return ResultCode.getJson(ResponseCode.ParamLost.value,"缺少必要参数");
        }

        QueryWrapper<WorkorderInformation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("workorder_number", workorder_number);
        List<WorkorderInformation> workorderInformationList = workorderInformationService.list(queryWrapper);
        return ResultCode.getJson(workorderInformationList);
    }

    // 管理员获得全部维修人员信息
    @GetMapping("/administer/maintainerList")
    public Object maintainerList() {
        List<MaintainerAccount> maintainerAccountList = maintainerAccountService.list(null);
        return ResultCode.getJson(maintainerAccountList);
    }

    // 管理员填写或选择初步方案
    @GetMapping("/administer/preliminary")
    public Object preliminary(Long workorder_number, String preliminary_porgram, Long administrator_number, Long maintainer_number) {
        if (workorder_number == null || administrator_number == null || maintainer_number == null) {
            return ResultCode.getJson(ResponseCode.ParamLost.value,"缺少必要参数");
        }

        PreliminaryScheme preliminaryScheme = new PreliminaryScheme();
        preliminaryScheme.setFkWorkorderNumber(workorder_number);
        preliminaryScheme.setFkMaintainerAccount(maintainer_number);
        preliminaryScheme.setFkJobNumber(administrator_number);
        preliminaryScheme.setPreliminaryProgram(preliminary_porgram);
        if (preliminarySchemeService.save(preliminaryScheme)) {
            return ResultCode.requestSucesse();
        } else {
            return ResultCode.requestFail();
        }
    }

    // 登入
    @GetMapping("/login/administer")
    public Object login(Long jobnumber, String passport) {

        if (jobnumber == null || jobnumber.equals("")) {//判断用户名和密码是否为空或者空串
            return ResultCode.getJson(ResponseCode.FAIL.value,"1","用户名或密码为空");

        }
        if (passport == null || passport.equals("")) {
            return ResultCode.getJson(ResponseCode.FAIL.value,"1","用户名或密码为空");

        }

        QueryWrapper<AdministratorAccount> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("job_number", jobnumber)
                .eq("passport", passport);//查询有没有对应的人
        AdministratorAccount administratorAccount = administratorAccountService.getOne(queryWrapper);
        if (administratorAccount == null)//要是没查到
        {
            return ResultCode.requestFail();
        } else {  //登陆成功
            return ResultCode.requestSucesse();

        }
    }
}

