package com.example.repair.controller.student;

import com.example.repair.entity.WorkorderInformation;
import com.example.repair.service.WorkorderInformationService;
import com.example.repair.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 学生Controller
 *
 * @author WOOGUGU
 */

@RestController
public class studentController {
    @Autowired
    WorkorderInformationService workorderInformationService;

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
}
