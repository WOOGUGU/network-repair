package com.example.repair.controller.student;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.repair.entity.StudentAccount;
import com.example.repair.entity.WorkorderInformation;
import com.example.repair.service.StudentAccountService;
import com.example.repair.service.WorkorderInformationService;
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

    // 学生提交工单
    @GetMapping("/student/submitorder")
    public Object submitorder(Integer student_number, Integer contact_information, String workorder_content, String address, String picture_address) {
        if (student_number == null || contact_information == null || workorder_content == null || address == null) {
            return ResultCode.getJson("参数错误！请检查必要上传信息！");
        }

        WorkorderInformation workorderInformation = new WorkorderInformation();
        workorderInformation.setFkStudentNumber(student_number);
        workorderInformation.setContactInformation(contact_information);
        workorderInformation.setWorkorderContent(workorder_content);
        workorderInformation.setAddress(address);
        workorderInformation.setPictureAddress(picture_address);
        workorderInformation.setWorkorderState("1");

        if (workorderInformationService.save(workorderInformation)) {
            return ResultCode.getJson("上传成功！");
        } else {
            return ResultCode.getJson("上传失败！");
        }
    }

    // 学生查看工单列表
    @GetMapping("/student/orderlist")
    public Object orderList(Integer student_number) {
        if (student_number == null) {
            return ResultCode.getJson("学号为空！请重新访问");
        }

        QueryWrapper<WorkorderInformation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("FK_student_number", student_number);
        List<WorkorderInformation> workorderInformationList = workorderInformationService.list(queryWrapper);
        return ResultCode.getJson(workorderInformationList);
    }

    // 学生查看某工单详细信息
    @GetMapping("/student/getorder")
    public Object getOrder(Integer workorder_number){
        if(workorder_number==null){
            return ResultCode.getJson("工单号为空！请重新访问");
        }

        QueryWrapper<WorkorderInformation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("workorder_number", workorder_number);
        List<WorkorderInformation> workorderInformationList = workorderInformationService.list(queryWrapper);
        return ResultCode.getJson(workorderInformationList);
    }

    @GetMapping("/login/student")//测试成功
    public Object login(String student_number, String passport) {
        //判断用户名和密码是否为空或者空串
        if (student_number == null || student_number.equals("")) {
            return ResultCode.getJson("用户名或密码为空");
        }
        if (passport == null || passport.equals("")) {
            return ResultCode.getJson("用户名或密码为空");
        }

        QueryWrapper<StudentAccount> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_number", student_number)
                .eq("passport", passport);//查询有没有对应的人
        StudentAccount studentAccount = studentAccountService.getOne(queryWrapper);
        if (studentAccount == null) {
            return ResultCode.getJson("用户名或密码不正确");
        } else {  //登陆成功
            return ResultCode.getJson("登录成功");
        }
    }
}

