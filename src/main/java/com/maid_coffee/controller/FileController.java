package com.maid_coffee.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.maid_coffee.service.ShopService;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;


@RestController
public class FileController {

	@Resource
	ShopService shopService;

	@PostMapping("/upload")
	public String upload(String nickname, MultipartFile photo, HttpServletRequest request) throws IOException{
		String path = request.getServletContext().getRealPath("/files");
		saveFile(photo, path);
		return "上传成功"; 
	}
    
	@PostMapping("/upload/shop-cover")
	public String uploadShopCover(HttpServletRequest request, Integer shopId) throws IOException {
		MultipartFile file = null;
		if (request instanceof org.springframework.web.multipart.MultipartHttpServletRequest mreq) {
			file = mreq.getFile("file");
			if (file == null) {
				file = mreq.getFile("photo");
			}
			if (file == null) {
				java.util.Iterator<String> names = mreq.getFileNames();
				if (names.hasNext()) {
					file = mreq.getFile(names.next());
				}
			}
		}
		if (file == null || file.isEmpty()) {
			return "文件为空";
		}
		if (shopId == null) {
			return "缺少 shopId";
		}
		String relativeDir = "/img/shop/cover/";
		String realDir = request.getServletContext().getRealPath(relativeDir);
		if (realDir == null) {
			realDir = request.getServletContext().getRealPath("/files") + File.separator + "shop" + File.separator + "cover" + File.separator;
		}
		File dir = new File(realDir);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		String original = file.getOriginalFilename();
		String ext = "";
		if (original != null && original.contains(".")) {
			ext = original.substring(original.lastIndexOf('.'));
		}
		String filename = "shop_image_" + shopId + "_240x240" + ext;
		File dest = new File(dir, filename);
		file.transferTo(dest);

		String scheme = request.getHeader("X-Forwarded-Proto");
		if (scheme == null) {
			scheme = request.getScheme();
		}
		String hostHeader = request.getHeader("X-Forwarded-Host");
		String host = hostHeader != null ? hostHeader : request.getServerName();
		int port = request.getServerPort();
		String portPart = "";
		if (hostHeader == null) {
			if (!("http".equalsIgnoreCase(scheme) && port == 80) && !("https".equalsIgnoreCase(scheme) && port == 443)) {
				portPart = ":" + port;
			}
		}
		String context = request.getContextPath();
		if (context == null) context = "";
		String baseUrl = scheme + "://" + host + portPart + context;

		String publicPath = "api" + relativeDir + filename; // api/img/shop/cover/filename
		// 写入数据库 coverPath 字段
		shopService.updateCoverPath(shopId, publicPath);

		return baseUrl + publicPath;
	}
    
	public void saveFile(MultipartFile photo, String path) throws IOException{
		File upDir = new File(path);
		if(!upDir.exists()){
			upDir.mkdir();
		}
		File file = new File(path + photo.getOriginalFilename());
		photo.transferTo(file);
	}
}
