package com.example.repair.controller.maintainer;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.repair.entity.MaintainerAccount;
import com.example.repair.entity.MaintenanceRecord;
import com.example.repair.entity.PreliminaryScheme;
import com.example.repair.entity.WorkorderInformation;
import com.example.repair.service.MaintainerAccountService;
import com.example.repair.service.MaintenanceRecordService;
import com.example.repair.service.PreliminarySchemeService;
import com.example.repair.service.WorkorderInformationService;
import com.example.repair.util.ResponseCode;
import com.example.repair.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class maintainerController {
    @Autowired
    PreliminarySchemeService preliminarySchemeService;
    @Autowired
    MaintenanceRecordService maintenanceRecordService;
    @Autowired
    MaintainerAccountService maintainerAccountService;
    @Autowired
    WorkorderInformationService workorderInformationService;

    //请求方法是get          维修员获得自己的工单
    @GetMapping("/maintainer/preliminarylist")
    public JSONObject getPreliminaryListByMaintainerNumber(Long maintainer_number) {
        if (maintainer_number == null) {
            return ResultCode.getJson(ResponseCode.ParamLost.value, "参数缺失");
        }

        QueryWrapper<PreliminaryScheme> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("FK_Maintainer_Account", maintainer_number);
        List<PreliminaryScheme> preliminarySchemeList = preliminarySchemeService.list(queryWrapper);

        return ResultCode.getJson(preliminarySchemeList);
    }

    //已测试成功    维修员填写工单记录
    @GetMapping("/maintainer/maintenance")
    public JSONObject fillWorkorder(
            Long workorder_number,
            String maintenance_record,
            Long maintainer_number
    ) {
        if (workorder_number == null || maintainer_number == null) {
            return ResultCode.getJson(ResponseCode.ParamLost.value, "参数缺失");
        }

        MaintenanceRecord maintenanceRecord = new MaintenanceRecord();
        maintenanceRecord.setFkWorkorderNumber(workorder_number);
        maintenanceRecord.setMaintenanceRecord(maintenance_record);
        maintenanceRecord.setFkJobNumber(maintainer_number);
        if (maintenanceRecordService.save(maintenanceRecord)) {
            return ResultCode.requestSucesse();
        } else {
            return ResultCode.requestFail();
        }
    }

    //测试成功   维修员查看某个工单
    @GetMapping("/maintainer/preliminary")
    public JSONObject getOneWorkorder(Long workorder_number) {
        if (workorder_number == null) {
            return ResultCode.getJson(ResponseCode.ParamLost.value, "参数缺失");
        }

        QueryWrapper<WorkorderInformation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("workorder_number", workorder_number);
        WorkorderInformation workorderInformation = workorderInformationService.getOne(queryWrapper);
        if (workorderInformation != null) {
            return ResultCode.getJson(workorderInformation);
        } else {
            return ResultCode.getJson("没有此工单");
        }
    }

    //测试成功  登录
    @GetMapping("login/maintainer")
    public JSONObject login(Long jobnumber, String passport) {
        if (jobnumber == null || jobnumber.equals("")) {
            return ResultCode.getJson("用户名或密码为空");
        }
        if (passport == null || passport.equals("")) {
            return ResultCode.getJson("用户名或密码为空");
        }

        QueryWrapper<MaintainerAccount> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("job_number", jobnumber)
                .eq("passport", passport);
        MaintainerAccount maintainerAccount = maintainerAccountService.getOne(queryWrapper);
        if (maintainerAccount == null) {
            return ResultCode.requestFail();
        } else {
            return ResultCode.requestSucesse();
        }
    }
}
