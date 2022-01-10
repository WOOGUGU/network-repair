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
    @Autowired//1.获取所有的Service
    PreliminarySchemeService preliminarySchemeService;
    @Autowired
    MaintenanceRecordService maintenanceRecordService;
    @Autowired
    MaintainerAccountService maintainerAccountService;
    @Autowired
    WorkorderInformationService workorderInformationService;

    @GetMapping("/maintainer/preliminarylist")//请求方法是get          维修员获得自己的工单
    public JSONObject getPreliminaryListByMaintainerNumber(Long maintainer_number ){
        if (maintainer_number == null){ return ResultCode.getJson(ResponseCode.ParamLost.value,"参数缺失");}

        QueryWrapper<PreliminaryScheme> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("FK_Maintainer_Account",maintainer_number);//工号=？
        List<PreliminaryScheme> preliminarySchemeList=preliminarySchemeService.list(queryWrapper);//查询工号是该工人的所有工单

        return ResultCode.getJson(preliminarySchemeList);

    }
    @GetMapping("/maintainer/maintenance")//已测试成功    维修员填写工单记录
    public JSONObject fillWorkorder (Long workorder_number,String maintenance_record,Long maintainer_number)
    {
        if (workorder_number == null || maintainer_number == null)//工单详细记录可以不填？个人认为，但是工单号和工人号必须填
        {
                return ResultCode.getJson(ResponseCode.ParamLost.value,"参数缺失");
        }

        MaintenanceRecord maintenanceRecord=new MaintenanceRecord();//创建一个新对象
        maintenanceRecord.setFkWorkorderNumber(workorder_number);
        maintenanceRecord.setMaintenanceRecord(maintenance_record);
        maintenanceRecord.setFkJobNumber(maintainer_number);//set
        if (maintenanceRecordService.save(maintenanceRecord))//判断是否成功 默认参数是布尔，后期可以改动
        {
            return ResultCode.requestSucesse();
        }
        else
        {
            return ResultCode.requestFail();
        }
    }
    @GetMapping("/maintainer/preliminary")//测试成功   维修员查看某个工单
    public JSONObject getOneWorkorder(Long workorder_number){
        if (workorder_number == null)
            return ResultCode.getJson(ResponseCode.ParamLost.value,"参数缺失");

        QueryWrapper<WorkorderInformation> queryWrapper= new QueryWrapper<>();
        queryWrapper.eq("workorder_number",workorder_number);
        WorkorderInformation workorderInformation=workorderInformationService.getOne(queryWrapper);
        if (workorderInformation != null)
        return ResultCode.getJson(workorderInformation);
        else
            return ResultCode.getJson("没有此工单");
    }


    @GetMapping("login/maintainer")//测试成功  登录
    public JSONObject login(Long jobnumber, String passport){

        if (jobnumber == null||jobnumber.equals("")){//判断用户名和密码是否为空或者空串
            return ResultCode.getJson("用户名或密码为空");

        }
        if (passport == null||passport.equals("")){
            return ResultCode.getJson("用户名或密码为空");

        }

        QueryWrapper<MaintainerAccount> queryWrapper= new QueryWrapper<>();
        queryWrapper.eq("job_number",jobnumber)
                    .eq("passport",passport);//查询有没有对应的人
        MaintainerAccount maintainerAccount = maintainerAccountService.getOne(queryWrapper);
         if (maintainerAccount == null)//要是没查到
         {
             return ResultCode.requestFail();
         }
         else
         {  //登陆成功
             return ResultCode.requestSucesse();
         }
    }



}
