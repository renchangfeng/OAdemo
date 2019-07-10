package com.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.utils.IContants;

@Controller
public class DownloadController {
	
		@RequestMapping("/fileDownload")
		public ResponseEntity<byte[]> fileDownload(HttpSession session, String realName, String orginName, String filePath) throws Exception {
		        byte[] body = null;
		        ServletContext sc = session.getServletContext();
		        String path = sc.getRealPath(IContants.PREFIX + filePath);
		        String downloadFile = path + "/" + realName;
		        //  /files/abc.txt：所要下载文件的地址
		        //InputStream in = sc.getResourceAsStream(downloadFile);
		        InputStream in = new FileInputStream(downloadFile);
		        body = new byte[in.available()];
		        in.read(body);
		        //创建响应头
		        HttpHeaders headers = new HttpHeaders();
		        String downloadFileName = URLEncoder.encode(orginName, "utf-8");        
		        headers.add("Content-Disposition", "attachment;filename=" + downloadFileName);
		        //设置状态信息:HttpStatus.OK表示服务器响应码200，表示服务器正常响应客户端请求
		        HttpStatus statusCode = HttpStatus.OK;
		        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(body, headers, statusCode);
		        
		        return response;

	}
}
