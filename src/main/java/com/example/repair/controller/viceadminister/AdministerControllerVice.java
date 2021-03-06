package com.example.repair.controller.viceadminister;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.repair.entity.*;
import com.example.repair.service.impl.*;
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
    @Autowired
    NoticeServiceImpl noticeService;

    @RequestMapping({"/", "/vicead/welcome"})
    public String Index(Model model) {

        //-------------------------------------------------登录--------------------------------------------------
        Map<String, String> map = new HashMap<String, String>();
        //进入登陆页面
        map.put("tip", "首页");
        map.put("username", "username");
        map.put("password", "password");
        map.put("remember", "记住我");
        map.put("btn", "登录");
        model.addAttribute("login", map);
        return "index";
    }

    //收到登录请求，如果登录成功，跳转到员工列表页
    @RequestMapping("/login/viceadminister")
    public String login(String jobnumber, String passport, Model model, HttpServletRequest request) {
        if (jobnumber == null || "".equals(passport)) {
            model.addAttribute("msg", ResultCode.getJson("0", "用户名或密码为空"));
            return "forward:/";
        }
        Long jobNumber;
        try {
            jobNumber = Long.parseLong(jobnumber);
        } catch (NumberFormatException e) {
            model.addAttribute("msg", ResultCode.getJson("0", "用户名或密码错误，请输入数字账号！"));
            return "forward:/";
        }

        QueryWrapper<AdministratorAccount> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("job_number", jobNumber)
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

    //注销功能
    @GetMapping("/viceadminister/logout")
    public String AdministerLogout(HttpSession session) {
        session.removeAttribute("AdName");
        session.removeAttribute("jobNumber");
        return "redirect:/vicead/welcome";
    }

    //-------------------------------------------------维修工--------------------------------------------------
    //员工页（维修工页面）
    @GetMapping("/viceadminister/maintainerList")
    public String maintainerList(Model model) {
        List<MaintainerAccount> maintainerAccountList = maintainerAccountService.list(null);
        model.addAttribute("msg", ResultCode.getJson(maintainerAccountList));
        return "emp/maintainerlist";
    }

    //删除维修工
    @GetMapping("/viceadminister/delmaintainer")
    public String deleteMatainer(Long jobNumber, Model model) {
        if (jobNumber == null) {
            model.addAttribute("msg", ResultCode.getJson(ResponseCode.ParamLost.value, "0", "缺少必要参数"));
        }
        maintainerAccountService.removeById(jobNumber);
        return "redirect:/viceadminister/maintainerList";
    }

    // -------------------------------------------------工单---------------------------------------------------

    //获得所有工单并跳转到相应页面
    @GetMapping("/viceadminister/workorderList")
    public String workorderList(Model model) {
        QueryWrapper<WorkorderInformation> queryWrapper = new QueryWrapper();
        queryWrapper.eq("workorder_State", "1");
        List<WorkorderInformation> workorderInformationList = workorderInformationService.list(queryWrapper);
        model.addAttribute("msg", ResultCode.getJson(workorderInformationList));
        return "orders/orderlist";
    }


    //预处理
    @RequestMapping("/viceadminister/preliminary")
    public String submitPreliminary(Long workorderNumber,
                                    Long maintainer_number,
                                    String preliminary_porgram,
                                    Model model,
                                    HttpSession session) {

        Long jobNumber = Long.parseLong(String.valueOf(session.getAttribute("jobNumber")));
        PreliminaryScheme preliminaryScheme = new PreliminaryScheme();
        preliminaryScheme.setFkJobNumber(jobNumber);
        preliminaryScheme.setFkMaintainerAccount(maintainer_number);
        preliminaryScheme.setPreliminaryProgram(preliminary_porgram);
        preliminaryScheme.setFkWorkorderNumber(workorderNumber);
        try {
            preliminarySchemeService.save(preliminaryScheme);
        } catch (Exception e) {
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


    //获得某工单
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

    // -------------------------------------------------公告---------------------------------------------------
    @RequestMapping("/viceadminister/noticeList")
    public String getNoticeList(Model model) {
        List<Notice> noticeList = noticeService.list(null);
        model.addAttribute("msg", ResultCode.getJson(noticeList));
        return "notice/noticelist";

    }

    @RequestMapping("/viceadminister/goaddnotice")
    public String goaddNotice(Long noticeNumber, Model model) {
        return "notice/add";

    }


    @RequestMapping("/viceadminister/addnotice")
    public String addNotice(Long jobNumber,
                            String noticeContent,
                            String noticeTitle,
                            Model model) {
        Notice notice = new Notice();
        notice.setNoticeTitle(noticeTitle);
        notice.setNoticeContent(noticeContent);
        notice.setFkJobNumber(jobNumber);
        if (noticeService.save(notice)) {
            model.addAttribute("msg", ResultCode.getJson("1", "上传成功"));
            return "redirect:/viceadminister/noticeList";
        } else {
            model.addAttribute("msg", ResultCode.getJson("0", "上传失败"));
            return "redirect:/viceadminister/noticeList";
        }

    }
}

