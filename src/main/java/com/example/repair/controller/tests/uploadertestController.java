package com.example.repair.controller.tests;

import com.example.repair.util.QiniuUtils;
import com.example.repair.util.ResultCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Controller
public class uploadertestController {
    @Autowired
    QiniuUtils qiniuUtils;


    @GetMapping("/test/wel")
    public String goInex(Model model) {

        model.addAttribute("jsondata", ResultCode.getJson("http://r5hpwa7fv.hb-bkt.clouddn.com/39e7971b-ef41-4fa8-beca-f5c9f43a624b.png"));
        return "pages/page";
    }

    @PostMapping("/test/upload")
    public String goInex(@RequestParam("file") MultipartFile file, Model model) {
        String originalFileName = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + "." + StringUtils.substringAfterLast(originalFileName, ".");
        boolean upload = qiniuUtils.upload(file, fileName);
        model.addAttribute("jsondata", ResultCode.getJson("http://" + QiniuUtils.url + "/" + fileName));//测试用例使用http
        return "pages/page";


    }
}
