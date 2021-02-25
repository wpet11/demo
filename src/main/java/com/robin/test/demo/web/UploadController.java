package com.robin.test.demo.web;

import com.robin.test.demo.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/company")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {

        String result = uploadService.upload(file.getInputStream());
        return result;
    }

    @GetMapping("/a")
    public String aaa(){
        return "ok";
    }
}
