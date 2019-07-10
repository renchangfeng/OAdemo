package com.utils;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public final class FileuploadUtils {

	public static boolean fileupload(MultipartFile upload,String path,String fileName){
		boolean flag = false;
		
		File file = new File(path + fileName);
		
		try {
			upload.transferTo(file);
			flag = true;
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return flag;
	}
}
