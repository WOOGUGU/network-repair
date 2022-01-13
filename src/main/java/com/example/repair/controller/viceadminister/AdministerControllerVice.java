package com.example.repair.controller.viceadminister;


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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdministerControllerVice {
    @Autowired
    WorkorderInformationServiceImpl workorderInformationService;
    @Autowired
    PreliminarySchemeServiceImpl preliminarySchemeService;
    @Autowired
    MaintainerAccountServiceImpl maintainerAccountService;
    @Autowired
    AdministratorAccountServiceImpl administratorAccountService;

    @RequestMapping({"/", "/vicead/welcome"})
    public String Index(Model model) {
        Map<String, String> map = new HashMap<String, String>();

        map.put("tip", "首页");
        map.put("username", "username");
        map.put("password", "password");
        map.put("remember", "记住我");
        map.put("btn", "登录");
        model.addAttribute("login", map);
        return "index";
    }

    @RequestMapping("/login/viceadminister")
    public String login(Long jobnumber, String passport, Model model, HttpServletRequest request) {

        if (jobnumber == null || "".equals(passport)) {
            model.addAttribute("msg", ResultCode.getJson("0", "用户名或密码为空"));
            return "forword:/vicead/welcome";
        }

        QueryWrapper<AdministratorAccount> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("job_number", jobnumber)
                .eq("passport", passport);
        AdministratorAccount administratorAccount = administratorAccountService.getOne(queryWrapper);
        if (administratorAccount == null) {
            model.addAttribute("msg", ResultCode.getJson("0", "用户名或密码错误"));
            return "forward:/vicead/welcome";
        } else {
            request.getSession().setAttribute("jobNumber", jobnumber);
            request.getSession().setAttribute("AdName", administratorAccount.getName());
            model.addAttribute("msg", ResultCode.getJson("1", "登录成功！"));
            return "redirect:/viceadminister/maintainerList";
        }
    }

    @GetMapping("/viceadminister/maintainerList")
    public String maintainerList(Model model) {
        List<MaintainerAccount> maintainerAccountList = maintainerAccountService.list(null);
        model.addAttribute("msg", ResultCode.getJson(maintainerAccountList));
        return "emp/maintainerlist";
    }
    @GetMapping("/viceadminister/delmaintainer")
    public String deleteMatainer(Long jobNumber,Model model) {
        if (jobNumber == null) {
            model.addAttribute("msg", ResultCode.getJson(ResponseCode.ParamLost.value, "0", "缺少必要参数"));
        }
        maintainerAccountService.removeById(jobNumber);
        return "redirect:/viceadminister/maintainerList";
    }


    @GetMapping("/viceadminister/workorderList")
    public String workorderList(Model model) {
        QueryWrapper<WorkorderInformation> queryWrapper = new QueryWrapper();
        queryWrapper.eq("workorder_State", "1");
        List<WorkorderInformation> workorderInformationList = workorderInformationService.list(queryWrapper);
        model.addAttribute("msg", ResultCode.getJson(workorderInformationList));
        return "orders/orderlist";
    }

    @GetMapping("/viceadminister/logout")
    public String AdministerLogout(HttpSession session) {
        session.removeAttribute("AdName");
        session.removeAttribute("jobNumber");
        return "redirect:/vicead/welcome";
    }


    @RequestMapping("/viceadminister/preliminary")
    public String submitPreliminary(Long workorderNumber,
                                    Long maintainer_number,
                                    String preliminary_porgram,
                                    Model model,
                                    HttpSession session) {
        PreliminaryScheme preliminaryScheme = new PreliminaryScheme();
        preliminaryScheme.setFkJobNumber((Long) session.getAttribute("jobNumber"));
        preliminaryScheme.setFkMaintainerAccount(maintainer_number);
        preliminaryScheme.setPreliminaryProgram(preliminary_porgram);
        preliminaryScheme.setFkWorkorderNumber(workorderNumber);
        try {
            preliminarySchemeService.save(preliminaryScheme);
        }catch (Exception e){
            return "redirect:/viceadminister/workorderList";
        }

        QueryWrapper<WorkorderInformation> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("workorder_number", workorderNumber);
        WorkorderInformation workorderInformation = workorderInformationService.getOne(queryWrapper);
        workorderInformation.setWorkorderState("2");
        workorderInformationService.updateById(workorderInformation);
        return "redirect:/viceadminister/workorderList";

    }



    @GetMapping("/viceadminister/getorder")
    public String preliminaryGetWorkOrder(Long workorderNumber, Model model) {
        if (workorderNumber == null) {
            model.addAttribute("msg", ResultCode.getJson(ResponseCode.ParamLost.value, "0", "缺少必要参数"));
        }
        QueryWrapper<WorkorderInformation> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("workorder_number", workorderNumber);
        WorkorderInformation workorderInformation = workorderInformationService.getOne(queryWrapper);
        List<MaintainerAccount> maintainerAccountList = maintainerAccountService.list(null);
        model.addAttribute("msg", ResultCode.getJson(workorderInformation));
        model.addAttribute("maintainers", maintainerAccountList);
        return "orders/update";
    }
}

