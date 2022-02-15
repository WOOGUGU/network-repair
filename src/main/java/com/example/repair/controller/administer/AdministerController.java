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
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 管理员Controller
 *
 * @author WOOGUGU
 */
@CrossOrigin
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

    // 管理员获取待处理工单
    @GetMapping("/administer/orderlist")
    public Object orderList() {
        QueryWrapper<WorkorderInformation> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("workorder_state", 1)
                .orderByDesc("initiation_time");
        List<WorkorderInformation> workorderInformationList = workorderInformationService.list(queryWrapper);
        return ResultCode.getJson(workorderInformationList);
    }

    // 管理员获取待维修工单
    @GetMapping("/administer/orderlist2")
    public Object orderList2() {
        QueryWrapper<WorkorderInformation> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("workorder_state", 2)
                .orderByDesc("initiation_time");
        List<WorkorderInformation> workorderInformationList = workorderInformationService.list(queryWrapper);
        return ResultCode.getJson(workorderInformationList);
    }

    // 管理员获已完成工单
    @GetMapping("/administer/orderlist3")
    public Object orderList3() {
        QueryWrapper<WorkorderInformation> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("workorder_state", 3)
                .orderByDesc("initiation_time");
        List<WorkorderInformation> workorderInformationList = workorderInformationService.list(queryWrapper);
        return ResultCode.getJson(workorderInformationList);
    }

    // 管理员获得某工单详细信息
    @GetMapping("/administer/getorder")
    public Object getOrder(
            @RequestParam Long workorder_number
    ) {
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
        QueryWrapper<MaintainerAccount> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("create_time");
        List<MaintainerAccount> maintainerAccountList = maintainerAccountService.maintainerlist();
        return ResultCode.getJson(maintainerAccountList);
    }

    // 管理员填写或选择初步方案
    @PostMapping("/administer/preliminary")
    public Object preliminary(
            @RequestParam Long workorder_number,
            @RequestParam String preliminary_porgram,
            @RequestParam Long administrator_number,
            @RequestParam Long maintainer_number
    ) {
        QueryWrapper<PreliminaryScheme> preQueryWrapper = new QueryWrapper<>();
        preQueryWrapper.eq("FK_workorder_number", workorder_number);
        if (preliminarySchemeService.getOne(preQueryWrapper) == null) {

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

        } else {
            return ResultCode.getJson(ResponseCode.hasNotAccess.value, "0", "该工单已被管理员处理");
        }
    }

    // 管理员添加维修员
    @PostMapping("/administer/addmaintainer")
    public Object addmaintainer(
            @RequestParam Long job_number,
            @RequestParam String name,
            @RequestParam String passport,
            @RequestParam String sex,
            @RequestParam String contact_information
    ) {
        if (job_number == null || "".equals(passport) || "".equals(name) || "".equals(sex) || "".equals(contact_information)) {
            return ResultCode.getJson(ResponseCode.ParamLost.value, "0", "缺少必要参数");
        }

        MaintainerAccount maintainerAccount = new MaintainerAccount();
        maintainerAccount.setJobNumber(job_number);
        maintainerAccount.setName(name);
        maintainerAccount.setPassport(passport);
        maintainerAccount.setSex(sex);
        maintainerAccount.setContactInformation(contact_information);

        if (maintainerAccountService.save(maintainerAccount)) {
            return ResultCode.getJson("1");
        } else {
            return ResultCode.getJson(ResponseCode.INTERNAL_SERVER_ERROR.value, "0", "添加失败");
        }
    }

    // 管理员删除维修员
    @DeleteMapping("/administer/deletemaintainer")
    public Object deletemaintainer(
            @RequestParam Long administer_number,
            @RequestParam String passport,
            @RequestParam Long maintainer_number
    ) {
        if (administer_number == null || "".equals(passport) || maintainer_number == null) {
            return ResultCode.getJson(ResponseCode.ParamLost.value, "0", "缺少必要参数");
        }

        QueryWrapper<AdministratorAccount> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("job_number", administer_number)
                .eq("passport", passport);
        AdministratorAccount administratorAccount = administratorAccountService.getOne(queryWrapper);
        if (administratorAccount == null) {
            return ResultCode.getJson(ResponseCode.IndexLost.value, "0", "管理员认证失败");
        } else {
            if (maintainerAccountService.removeById(maintainer_number)) {
                return ResultCode.getJson("1", "成功移除维修员：" + maintainer_number);
            } else {
                return ResultCode.getJson(ResponseCode.INTERNAL_SERVER_ERROR.value, "0", "移除失败");
            }
        }
    }

    //恢复已删除的维修工信息
    @PostMapping("administer/deletemaintainerback")
    public Object deleteMaintainerBack(
            @RequestParam Long maintainer_number
    ) {
        if (maintainer_number == null) {
            return ResultCode.getJson(ResponseCode.ParamLost.value, "缺少维修工号！");
        }

        if (administratorAccountService.deleteMaintainerBack(maintainer_number) == 1) {
            return ResultCode.getJson("1", "成功恢复！");
        } else {
            return ResultCode.getJson(ResponseCode.SqlConfigError.value, "0", "恢复失败！");
        }

    }

    // 登入
    @PostMapping("/login/administer")
    public Object login(
            @RequestParam Long jobnumber,
            @RequestParam String passport
    ) {
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
            administratorAccount.setPassport(null);
            return ResultCode.getJson(administratorAccount, "用户存在");
        }
    }
}

