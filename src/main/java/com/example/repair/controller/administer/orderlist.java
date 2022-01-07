package com.example.repair.controller.administer;

import com.example.repair.entity.WorkorderInformation;
import com.example.repair.service.impl.WorkorderInformationServiceImpl;
import com.example.repair.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 管理员获得工单列表
 *
 * @author WOOGUGU
 */

@RestController
@RequestMapping("/administer")
public class orderlist {
    @Autowired
    WorkorderInformationServiceImpl workorderInformationService;

    @GetMapping("/orderlist")
    public Object RequestMapping() {
        List<WorkorderInformation> workorderInformationList = workorderInformationService.list(null);
        return ResultCode.getJson(workorderInformationList);
    }
}
