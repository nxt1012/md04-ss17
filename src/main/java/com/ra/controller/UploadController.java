package com.ra.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@PropertySource("classpath:/config.properties")
public class UploadController {
    @Value("${upload_path}")
    private String uploadPath;

    @GetMapping("/upload")
    public String upload() {
        return "upload";
    }

    @PostMapping("/upload")
    public String processUpload(@RequestParam("fileImage") MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();
        File file = new File(uploadPath+fileName);
        if (!file.exists()) {
            file.mkdir();
        }
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "/admin";
    }
}
