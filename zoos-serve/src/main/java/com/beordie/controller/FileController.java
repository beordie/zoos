package com.beordie.controller;

import com.beordie.common.Result;
import com.beordie.config.CommonConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @Description //TODO
 * @Version 1
 * @Date 2023/3/2 12:00
 * @User beordie
 */
@Slf4j
@RestController
@RequestMapping("image")
public class FileController {
    @Autowired
    private CommonConfig config;

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public Result upload(@RequestParam("photo") MultipartFile image,
                         @RequestParam("type") String type,
                         HttpServletRequest request){
        String originalFilename = image.getOriginalFilename();
        String fileName = getPath(type, originalFilename);
        try {
            image.transferTo(new File(config.getUploadDirectory() + fileName));
        } catch (IOException e) {
            log.error(e.getMessage());
            return Result.failed();
        }
        return new Result(fileName);
    }

    private String getPath(String type, String originalFilename) {
        String fileName = UUID.randomUUID().toString().replace("-","")
                + originalFilename.substring(originalFilename.lastIndexOf("."));

        switch (type) {
            case "zoo":
                return "/image/zoo/" + fileName;
            case "photo":
                return "/image/photo/" + fileName;
            case "user":
                return "/image/user/" + fileName;
            default:
                return "";
        }
    }
}
