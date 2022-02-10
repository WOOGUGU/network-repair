package com.example.repair.controller.file;

import com.example.repair.util.ResultCode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件上传（图片）
 *
 * @author WOOGUGU
 */

@RestController
public class FileController {

    @PostMapping(value = "/fileUpload")
    public Object fileUpload(
            @RequestParam(value = "file") MultipartFile file
    ) {
        if (file.isEmpty()) {
            System.out.println("请选择图片");
        }
        // 文件名
        String fileName = file.getOriginalFilename();
        // 后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 新文件名
        fileName = UUID.randomUUID() + suffixName;
        // 上传后的路径
        String filePath = System.getProperty("user.dir") + "/images/" + fileName;
        File dest = new File(filePath);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("保存图片至==>" + filePath);
        //返回图片名称
        return ResultCode.getJson(fileName);
    }
}