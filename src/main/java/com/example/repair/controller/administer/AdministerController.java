package com.example.repair.controller.administer;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.repair.entity.AdministratorAccount;
import com.example.repair.entity.MaintainerAccount;
import com.example.repair.entity.PreliminaryScheme;
import com.example.repair.entity.WorkorderInformation;
import com.example.repair.service.impl.AdministratorAccountServiceImpl;
import com.example.repair.service.impl.MaintainerAccountServiceImpl;
import com.example.repair.service.impl.PreliminarySchemeServiceImpl;
import com.example.repair.service.impl.WorkorderInformationServiceImpl;
import com.example.repair.util.ResponseCode;
import com.example.repair.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    PreliminarySchemeServiceImpl preliminarySchemeService;
    @Autowired
    MaintainerAccountServiceImpl maintainerAccountService;
    @Autowired
    AdministratorAccountServiceImpl administratorAccountService;

    // 管理员获取工单
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
            return ResultCode.getJson(ResponseCode.ParamLost.value, "0", "缺少必要参数");
        }

        QueryWrapper<WorkorderInformation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("workorder_number", workorder_number);
        List<WorkorderInformation> workorderInformationList = workorderInformationService.list(queryWrapper);
        return ResultCode.getJson(workorderInformationList);
    }

    // 管理员获得全部维修人员基本信息
    @GetMapping("/administer/maintainerlist")
    public Object maintainerList() {
        List<MaintainerAccount> maintainerAccountList = maintainerAccountService.maintainerlist();
        return ResultCode.getJson(maintainerAccountList);
    }

    // 管理员填写或选择初步方案
    @PostMapping("/administer/preliminary")
    public Object preliminary(
            Long workorder_number,
            String preliminary_porgram,
            Long administrator_number,
            Long maintainer_number
    ) {
        if (workorder_number == null || administrator_number == null || maintainer_number == null) {
            return ResultCode.getJson(ResponseCode.ParamLost.value, "0", "缺少必要参数");
        }

        PreliminaryScheme preliminaryScheme = new PreliminaryScheme();
        preliminaryScheme.setFkWorkorderNumber(workorder_number);
        preliminaryScheme.setFkMaintainerAccount(maintainer_number);
        preliminaryScheme.setFkJobNumber(administrator_number);
        preliminaryScheme.setPreliminaryProgram(preliminary_porgram);
        if (preliminarySchemeService.save(preliminaryScheme)) {

            QueryWrapper<WorkorderInformation> queryWrapper = new QueryWrapper<>();
            queryWrapper
                    .eq("workorder_number", workorder_number);
            WorkorderInformation workorderInformation = workorderInformationService.getOne(queryWrapper);
            workorderInformation.setWorkorderState("2");
            workorderInformationService.updateById(workorderInformation);

            return ResultCode.getJson("1");
        } else {
            return ResultCode.getJson(ResponseCode.INTERNAL_SERVER_ERROR.value, "0", "添加失败");
        }
    }

    // 管理员添加维修员
    @PostMapping("/administer/addmaintainer")
    public Object addmaintainer(
            Long job_number,
            String name,
            String passport
    ) {
        if (job_number == null || "".equals(passport) || "".equals(name)) {
            return ResultCode.getJson(ResponseCode.ParamLost.value, "0", "用户名或密码为空");
        }

        MaintainerAccount maintainerAccount = new MaintainerAccount();
        maintainerAccount.setJobNumber(job_number);
        maintainerAccount.setName(name);
        maintainerAccount.setPassport(passport);

        if (maintainerAccountService.save(maintainerAccount)) {
            return ResultCode.getJson("1");
        } else {
            return ResultCode.getJson(ResponseCode.INTERNAL_SERVER_ERROR.value, "0", "添加失败");
        }
    }

    // 登入
    @PostMapping("/login/administer")
    public Object login(Long jobnumber, String passport) {

        if (jobnumber == null || "".equals(passport)) {
            return ResultCode.getJson(ResponseCode.ParamLost.value, "0", "用户名或密码为空");
        }

        QueryWrapper<AdministratorAccount> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("job_number", jobnumber)
                .eq("passport", passport);
        AdministratorAccount administratorAccount = administratorAccountService.getOne(queryWrapper);
        if (administratorAccount == null) {
            return ResultCode.getJson(ResponseCode.IndexLost.value, "0", "用户不存在");
        } else {
            return ResultCode.getJson("1", "用户存在");
        }
    }
}

