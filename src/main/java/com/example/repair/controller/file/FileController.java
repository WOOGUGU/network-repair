package com.example.repair.controller.file;

import com.example.repair.util.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 文件上传（图片）
 *
 * @author WOOGUGU
 */

@Slf4j
@RestController
public class FileController {

    @PostMapping(value = "/fileUpload")
    public Object fileUpload(
            @RequestParam(value = "file") MultipartFile file
    ) {
        if (file.isEmpty()) {
            log.error("无图片");
        }
        // 文件名
        String fileName = file.getOriginalFilename();
        // 后缀名
        assert fileName != null;
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
        log.info("保存图片至==>" + filePath);
        //返回图片名称
        return ResultCode.getJson("https://cn-cd-dx-6.natfrp.cloud:19751/" + fileName);
    }

    @PostMapping("/filesUpload")
    public Object filesUpload(
            @RequestParam(value = "files") MultipartFile[] files
    ) {
        List<String> list = new ArrayList<>();
        String fileName, suffixName, filePath;
        for (int i = 0; i < files.length; i++) {
            fileName = files[i].getOriginalFilename();
            assert fileName != null;
            suffixName = fileName.substring(fileName.lastIndexOf("."));
            fileName = UUID.randomUUID() + suffixName;
            filePath = System.getProperty("user.dir") + "/images/" + fileName;
            java.io.File dest = new java.io.File(filePath);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                files[i].transferTo(dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
            log.info("保存图片至==>" + filePath);
            list.add("https://cn-cd-dx-6.natfrp.cloud:19751/" + fileName);
        }
        return ResultCode.getJson(list);
    }
}
