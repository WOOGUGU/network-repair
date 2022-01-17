package com.example.repair.controller.student;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.repair.entity.StudentAccount;
import com.example.repair.entity.WorkorderInformation;
import com.example.repair.service.StudentAccountService;
import com.example.repair.service.WorkorderInformationService;
import com.example.repair.util.ResponseCode;
import com.example.repair.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 学生Controller
 *
 * @author WOOGUGU
 */

@RestController
public class studentController {
    @Autowired
    WorkorderInformationService workorderInformationService;
    @Autowired
    StudentAccountService studentAccountService;

    // 学生提交评价
    @PostMapping("/student/evaluate")
    public Object evaluate(
            Long student_number,
            Long workorder_number,
            float maintenance_satisfaction,
            String evaluation
    ) {
        if (student_number == null || workorder_number == null) {
            return ResultCode.getJson(ResponseCode.ParamLost.value, "0", "缺少必要参数！");
        }

        WorkorderInformation workorderInformation = new WorkorderInformation();
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("workorder_number", workorder_number);
        workorderInformation = workorderInformationService.getOne(queryWrapper);
        //只有待评价的订单才可以评价
        if (workorderInformation.getFkStudentNumber().equals(student_number) && "1".equals(workorderInformation.getEvaluationStatus())) {
            workorderInformation.setMaintenanceSatisfaction(maintenance_satisfaction);
            workorderInformation.setEvaluation(evaluation);
            workorderInformation.setEvaluationStatus("2");
            workorderInformationService.updateById(workorderInformation);
            return ResultCode.getJson("1");
        } else {
            return ResultCode.getJson(ResponseCode.FAIL.value, "0", "用户非法或当前阶段无法评价");
        }
        //根据工单号查出来，看看是不是这个本人，然后更新信息，如果不是本人则”无权限“
    }

    // 学生提交工单
    @PostMapping("/student/submitorder")
    public Object submitorder(
            Long student_number,
            String contact_information,
            String workorder_content,
            String address,
            String picture_address
    ) {
        if (student_number == null || contact_information == null || workorder_content == null || address == null) {
            return ResultCode.getJson(ResponseCode.ParamLost.value, "0", "缺少必要参数！");
        }

        WorkorderInformation workorderInformation = new WorkorderInformation();
        workorderInformation.setFkStudentNumber(student_number);
        workorderInformation.setContactInformation(contact_information);
        workorderInformation.setWorkorderContent(workorder_content);
        workorderInformation.setAddress(address);
        workorderInformation.setPictureAddress(picture_address);
        workorderInformation.setWorkorderState("1");

        if (workorderInformationService.save(workorderInformation)) {
            return ResultCode.getJson("1");
        } else {
            return ResultCode.getJson(ResponseCode.INTERNAL_SERVER_ERROR.value, "0", "添加失败");
        }
    }

    // 学生查看工单列表
    @GetMapping("/student/orderlist")
    public Object orderList(Long student_number) {
        if (student_number == null) {
            return ResultCode.getJson(ResponseCode.ParamLost.value, "0", "学号为空！请重新访问");
        }

        QueryWrapper<WorkorderInformation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("FK_student_number", student_number)
                .orderByDesc("initiation_time");
        List<WorkorderInformation> workorderInformationList = workorderInformationService.list(queryWrapper);
        return ResultCode.getJson(workorderInformationList);
    }

    // 学生查看某工单详细信息
    @GetMapping("/student/getorder")
    public Object getOrder(Long workorder_number) {
        if (workorder_number == null) {
            return ResultCode.getJson(ResponseCode.ParamLost.value, "0", "工单号为空");
        }

        QueryWrapper<WorkorderInformation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("workorder_number", workorder_number);
        List<WorkorderInformation> workorderInformationList = workorderInformationService.list(queryWrapper);
        return ResultCode.getJson(workorderInformationList);
    }

    @PostMapping("/login/student")//测试成功
    public Object login(Long student_number, String passport) {
        //判断用户名和密码是否为空或者空串
        if (student_number == null || "".equals(passport)) {
            return ResultCode.getJson(ResponseCode.ParamLost.value, "0", "用户名或密码为空");
        }

        QueryWrapper<StudentAccount> queryWrapper = new QueryWrapper<>();
        //查询有没有对应的人
        queryWrapper
                .eq("student_number", student_number)
                .eq("passport", passport);
        StudentAccount studentAccount = studentAccountService.getOne(queryWrapper);
        if (studentAccount == null) {
            return ResultCode.getJson(ResponseCode.IndexLost.value, "0", "用户不存在");
        } else {  //登陆成功
            return ResultCode.getJson("1", "用户存在");
        }
    }
}

