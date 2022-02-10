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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    //维修员获得自己的工单
    @GetMapping("/maintainer/preliminarylist")
    public JSONObject preliminaryList(
            @RequestParam Long maintainer_number
    ) {
        if (maintainer_number == null) {
            return ResultCode.getJson(ResponseCode.ParamLost.value, "0", "缺少必要参数");
        }

        QueryWrapper<PreliminaryScheme> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("FK_Maintainer_Account", maintainer_number)
                .orderByDesc("preliminar_time");
        List<PreliminaryScheme> preliminarySchemeList = preliminarySchemeService.list(queryWrapper);

        return ResultCode.getJson(preliminarySchemeList);
    }

    //维修员填写工单记录
    @PostMapping("/maintainer/maintenance")
    public JSONObject fillWorkorder(
            @RequestParam Long workorder_number,
            @RequestParam String maintenance_record,
            @RequestParam Long maintainer_number
    ) {
        if (workorder_number == null || maintainer_number == null) {
            return ResultCode.getJson(ResponseCode.ParamLost.value, "0", "缺少必要参数");
        }

        MaintenanceRecord maintenanceRecord = new MaintenanceRecord();
        maintenanceRecord.setFkWorkorderNumber(workorder_number);
        maintenanceRecord.setMaintenanceRecord(maintenance_record);
        maintenanceRecord.setFkJobNumber(maintainer_number);//先新建一个
        if (maintenanceRecordService.save(maintenanceRecord)) {//要是插入成功
            QueryWrapper<WorkorderInformation> queryWrapper = new QueryWrapper<>();
            queryWrapper//要把对应的属于自己的，预处理的工单更新  把工单状态变成3
                    .eq("workorder_number", workorder_number)
                    .eq("workorder_state", "2");
            WorkorderInformation workorderInformation = workorderInformationService.getOne(queryWrapper);
            workorderInformation.setWorkorderState("3");
            workorderInformationService.updateById(workorderInformation);
            if (workorderInformation == null) {

                ResultCode.getJson("该订单还未预处理");
            }

            return ResultCode.getJson("1");
        } else {
            return ResultCode.getJson(ResponseCode.INTERNAL_SERVER_ERROR.value, "0", "添加失败");
        }
    }

    //维修员查看某个工单
    @GetMapping("/maintainer/preliminary")
    public JSONObject getOneWorkorder(
            @RequestParam Long workorder_number
    ) {
        if (workorder_number == null) {
            return ResultCode.getJson(ResponseCode.ParamLost.value, "0", "缺少必要参数");
        }

        QueryWrapper<WorkorderInformation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("workorder_number", workorder_number);
        WorkorderInformation workorderInformation = workorderInformationService.getOne(queryWrapper);
        if (workorderInformation != null) {
            return ResultCode.getJson(workorderInformation);
        } else {
            return ResultCode.getJson(ResponseCode.IndexLost.value, "0", "没有此工单");
        }
    }

    //登录
    @PostMapping("login/maintainer")
    public JSONObject login(
            @RequestParam Long jobnumber,
            @RequestParam String passport
    ) {
        if (jobnumber == null || "".equals(passport)) {
            return ResultCode.getJson("用户名或密码为空");
        }

        QueryWrapper<MaintainerAccount> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("job_number", jobnumber)
                .eq("passport", passport);

        MaintainerAccount maintainerAccount = maintainerAccountService.getOne(queryWrapper);
        if (maintainerAccount == null) {
            return ResultCode.getJson(ResponseCode.IndexLost.value, "0", "用户不存在");
        } else {
            return ResultCode.getJson("1", "用户存在");
        }
    }
}
