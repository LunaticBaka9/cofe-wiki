package com.maid_coffee.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.maid_coffee.common.Result;
import com.maid_coffee.service.ShopService;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/file")
public class FileController {
    @Resource
    ShopService shopService;

    @Value("${app.upload.base-url:}")
    private String baseUrl;

    private File getUploadDir(String subDir) throws IOException {
        File uploadDir;
        try {
            File staticDir = ResourceUtils.getFile("classpath:static");
            if (staticDir.exists()) {
                uploadDir = new File(staticDir, subDir);
            } else {
                throw new IOException("static目录不存在");
            }
        } catch (Exception e) {
            File appDir;
            try {
                java.net.URL url = getClass().getProtectionDomain().getCodeSource().getLocation();
                if (url != null) {
                    File jarFile = new File(url.toURI().getPath());
                    appDir = jarFile.getParentFile();
                } else {
                    appDir = null;
                }
            } catch (Exception ex) {
                appDir = null;
            }
            if (appDir == null) {
                appDir = new File(System.getProperty("user.dir"));
            }
            uploadDir = new File(appDir, "static/" + subDir);
        }
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        return uploadDir;
    }

    @PostMapping("/uploadShopCover")
    public Result uploadShopCovers(@RequestParam(required = false) Long shopId,
            @RequestParam("photo") MultipartFile photo,
            HttpServletRequest request) throws IOException {
        if (photo == null || photo.isEmpty()) {
            return Result.error("上传文件为空");
        }
        File uploadDirFile = getUploadDir("upload/shopCovers");

        String original = photo.getOriginalFilename();
        String fileName = System.currentTimeMillis() + "_" + (original != null ? original : "shopCover.jpg");
        Path target = uploadDirFile.toPath().resolve(fileName);
        Files.copy(photo.getInputStream(), target);

        String publicPath = "api/upload/shopCovers/" + fileName;
        // String fullUrl = (baseUrl != null && !baseUrl.isEmpty()) ? baseUrl +
        // publicPath : publicPath;
        shopService.updateCoverPath(shopId, publicPath);
        return Result.success(publicPath);
    }

    @PostMapping("/upload")
    public Result uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        if (file.isEmpty()) {
            return Result.error("上传文件为空");
        }

        String original = file.getOriginalFilename();
        String suffix = FilenameUtils.getExtension(original).toLowerCase();

        String subDir;
        if (suffix.matches("jpg|jpeg|png|gif|bmp|webp|svg|ico")) {
            subDir = "upload/picture";
        } else if (suffix.matches("txt|doc|docx|xls|xlsx|pdf|ppt|pptx|csv")) {
            subDir = "upload/file";
        } else if (suffix.matches("zip|rar|7z|tar|gz")) {
            subDir = "upload/archive";
        } else {
            subDir = "upload/other";
        }

        File uploadDirFile = getUploadDir(subDir);
        String fileName = System.currentTimeMillis() + "_" + (original != null ? original : "file");
        Path target = uploadDirFile.toPath().resolve(fileName);
        Files.copy(file.getInputStream(), target);

        String publicPath = "/static/" + subDir + "/" + fileName;
        String fullUrl = (baseUrl != null && !baseUrl.isEmpty()) ? baseUrl + publicPath : publicPath;

        java.util.Map<String, Object> fileInfo = new java.util.HashMap<>();
        fileInfo.put("url", fullUrl);
        fileInfo.put("name", original);
        fileInfo.put("size", file.getSize());
        fileInfo.put("type", suffix);

        return Result.success(fileInfo);
    }
}