package com.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class CommonUtils {
	
	//用于存储文件类型图标
		private static Map<String, String> fileTypePic = new HashMap<String, String>();
		
		static{
			fileTypePic.put(".bmp", "bmp.gif");
			fileTypePic.put(".chm", "chm.gif");
			fileTypePic.put(".cvs", "cvs.gif");
			fileTypePic.put(".default", "default.gif");
			fileTypePic.put(".doc", "doc.gif");
			fileTypePic.put(".docx", "doc.gif");
			fileTypePic.put(".xls", "excel.gif");
			fileTypePic.put(".xlsx", "excel.gif");
			fileTypePic.put(".ppt", "ppt.gif");
			fileTypePic.put(".pptx", "ppt.gif");
			fileTypePic.put(".pdf", "pdf.gif");
			fileTypePic.put(".txt", "txt.gif");
			fileTypePic.put(".rar", "rar.gif");
			fileTypePic.put(".zip", "zip.gif");
			fileTypePic.put(".jpg", "jpeg.gif");
			fileTypePic.put(".jpeg", "jpeg.gif");
			fileTypePic.put(".sql", "sql.gif");
			fileTypePic.put(".xml", "xml.gif");
			fileTypePic.put(".psd", "psd.gif");
			fileTypePic.put(".vsd", "vsd.gif");
			fileTypePic.put(".rtf", "rtf.gif");
			fileTypePic.put(".swf", "swf.gif");
			fileTypePic.put(".ini", "ini.gif");
			fileTypePic.put(".mdb", "mdb.gif");
		}
    
	 //截取URL

	public static String cutUrl(String pris){
		
		//saveRolePrivilegeUI.action?userid=3
		if(pris != null && !pris.trim().equals("")){
			//去掉路径
		    int index = pris.lastIndexOf("/");
		    if(index != -1){
		    	pris = pris.substring(index + 1);
		    }
			
		    //去掉参数
			index = pris.indexOf("?");
			if(index != -1){
				pris = pris.substring(0,index);
			}
			//去掉扩展名
			index = pris.indexOf(".");
			if(index != -1){
				pris = pris.substring(0, index);
			}
			//去掉UI
			if(pris.endsWith("UI")){
				pris = pris.substring(0, pris.length()-2);
			}
			
		}
//		System.out.println("pris的：" + pris);
		return pris;
	}
	
	//文件单位转换
	public static String getFileSize(long originFileSize){
		long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;
        String fileSize;
        if (originFileSize >= gb) {
        	fileSize = String.format("%.1f GB", (float) originFileSize / gb);
        } else if (originFileSize >= mb) {
            float f = (float) originFileSize / mb;
            fileSize = String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
        } else if (originFileSize >= kb) {
            float f = (float) originFileSize / kb;
            fileSize = String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
        } else
        	fileSize = String.format("%d B", originFileSize);
        return fileSize;
	}
	
	//获取上传文件类型图片
	public static String getUploadFilePic(String expendName){
		String pic = fileTypePic.get(expendName);
		if(pic == null){
			pic = fileTypePic.get(".default"); //如果文件类型未知，则返回系统默认类型
		}
		return pic;
	}
	
	//生成新的文件名
	public static String createNewFileName(String expendName){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		StringBuffer temp = new StringBuffer();
		temp.append(sdf.format(date));
		temp.append(UUID.randomUUID());
		temp.append(expendName);
		return temp.toString();
	}
	
	//获取文件扩展名
	public static String getExpandName(String fileName){
		String expandName = ".txt";
		if(fileName != null && !fileName.trim().equals("")){
			expandName = fileName.substring(fileName.lastIndexOf("."));
		}
		return expandName;
	}
	
	//返回文件创建时间，类型为String
	public static String createTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		return sdf.format(date);
	}
}
