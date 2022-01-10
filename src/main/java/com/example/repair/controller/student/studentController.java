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
    @GetMapping("/student/evaluate")
    public Object submitorder(
            Long student_number,
            Long workorder_number,
            float maintenance_satisfaction,
            String evaluation
    ) {
        if (student_number == null || workorder_number == null) {
            return ResultCode.getJson(ResponseCode.ParamLost.value, "缺少必要参数！");
        }

        QueryWrapper queryWrapper = new QueryWrapper<>();
        WorkorderInformation workorderInformation = new WorkorderInformation();
        queryWrapper.eq("workorder_number", workorder_number);
        workorderInformation = workorderInformationService.getOne(queryWrapper);
        if (workorderInformation.getFkStudentNumber().equals(student_number) && workorderInformation.getEvaluationStatus().equals("1"))//只有待评价的订单才可以评价
        {
            workorderInformation.setMaintenanceSatisfaction(maintenance_satisfaction);
            workorderInformation.setEvaluation(evaluation);
            workorderInformation.setEvaluationStatus("2");
            workorderInformationService.updateById(workorderInformation);
            return ResultCode.requestSucesse();
        } else {
            return ResultCode.Fail("没有权限或订单已评价");
        }
        //根据工单号查出来，看看是不是这个本人，然后更新信息，如果不是本人返回400，加上”无权限“
    }

    // 学生提交工单
    @GetMapping("/student/submitorder")
    public Object submitorder(
            Long student_number,
            String contact_information,
            String workorder_content,
            String address,
            String picture_address
    ) {
        if (student_number == null || contact_information == null || workorder_content == null || address == null) {
            return ResultCode.getJson(ResponseCode.ParamLost.value, "缺少必要参数！");
        }

        WorkorderInformation workorderInformation = new WorkorderInformation();
        workorderInformation.setFkStudentNumber(student_number);
        workorderInformation.setContactInformation(contact_information);
        workorderInformation.setWorkorderContent(workorder_content);
        workorderInformation.setAddress(address);
        workorderInformation.setPictureAddress(picture_address);
        workorderInformation.setWorkorderState("1");

        if (workorderInformationService.save(workorderInformation)) {
            return ResultCode.requestSucesse();
        } else {
            return ResultCode.requestFail();
        }
    }

    // 学生查看工单列表
    @GetMapping("/student/orderlist")
    public Object orderList(Long student_number) {
        if (student_number == null) {
            return ResultCode.Fail("学号为空！请重新访问");
        }

        QueryWrapper<WorkorderInformation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("FK_student_number", student_number);
        List<WorkorderInformation> workorderInformationList = workorderInformationService.list(queryWrapper);
        return ResultCode.getJson(workorderInformationList);
    }

    // 学生查看某工单详细信息
    @GetMapping("/student/getorder")
    public Object getOrder(Long workorder_number) {
        if (workorder_number == null) {
            return ResultCode.Fail("工单号为空");
        }

        QueryWrapper<WorkorderInformation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("workorder_number", workorder_number);
        List<WorkorderInformation> workorderInformationList = workorderInformationService.list(queryWrapper);
        return ResultCode.getJson(workorderInformationList);
    }

    @GetMapping("/login/student")//测试成功
    public Object login(Long student_number, String passport) {
        //判断用户名和密码是否为空或者空串
        if (student_number == null || student_number.equals("")) {
            return ResultCode.Fail("用户名或密码为空");
        }
        if (passport == null || passport.equals("")) {
            return ResultCode.Fail("用户名或密码为空");
        }

        QueryWrapper<StudentAccount> queryWrapper = new QueryWrapper<>();
        //查询有没有对应的人
        queryWrapper
                .eq("student_number", student_number)
                .eq("passport", passport);
        StudentAccount studentAccount = studentAccountService.getOne(queryWrapper);
        if (studentAccount == null) {
            return ResultCode.requestFail();
        } else {  //登陆成功
            return ResultCode.requestSucesse();
        }
    }
}

