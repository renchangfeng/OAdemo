package com.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.exception.CustomException;
import com.pojo.KnowledgeFileCustom;
import com.pojo.KnowledgeFolderCustom;
import com.service.IKnowledgeFileService;
import com.service.IKnowledgeFolderService;
import com.utils.CommonUtils;
import com.utils.FileuploadUtils;
import com.utils.IContants;
import com.utils.KnowledgeUtils;


@Controller
public class KnowledgeController {

	 @Resource(name = "knowledgeFolderService")
	 private IKnowledgeFolderService folderService;
	
	 @Resource(name = "knowledgeFileService")
	 private IKnowledgeFileService fileService;
	 
	 //跳转文件下载页面
	 @RequestMapping("/searchKnowledge")
	 public ModelAndView searchKnowledge(){
		 ModelAndView mav = new ModelAndView();
		 //生成树形的根节点
		 KnowledgeFolderCustom kfc = this.folderService.searchRootInfo();
		 //生成树形控件
	     String tree = KnowledgeUtils.createFileDownloadAndDelete(kfc);
		 mav.addObject("treeData", tree);
		 mav.setViewName(IContants.PREFIX + "jsp/System_Knowledge/knowledgeListUI.jsp");
		 return mav;
	 }
	 
		//跳转到创建目录页面
		@RequestMapping("/saveFolderUI")
		public ModelAndView saveFolderUI(){
			ModelAndView mav = new ModelAndView();
			//获取树的根节点
			KnowledgeFolderCustom root = this.folderService.searchRootInfo();
			//生成树形控件
			String tree = KnowledgeUtils.createTreeForSaveFolder(root);
			mav.addObject("treeData", tree);
			mav.setViewName(IContants.PREFIX + "jsp/System_Knowledge/saveFolderUI.jsp");
			return mav;
		}
		
		//保存目录信息
		@RequestMapping("/saveFolder")
		public ModelAndView saveFolder(KnowledgeFolderCustom folder){
			ModelAndView mav = new ModelAndView();
			String message = "";
			try{
				this.folderService.saveFolderInfo(folder);
				message = "【提示信息】 保存目录成功";
			}catch(Exception e){
				e.printStackTrace();
				message = "【提示信息】 保存目录失败";
				throw new CustomException(message);
			}
			mav.addObject("message", message);
			mav.setViewName("saveFolderUI.action");
			return mav;
		}
		
	 
	 //跳转上传文件目录
	 @RequestMapping("saveFileUI")
	 public ModelAndView saveFileUI(){
		 ModelAndView mav = new ModelAndView();
		 //生成树形的根节点
		 KnowledgeFolderCustom kfc = this.folderService.searchRootInfo();
		 //生成树形控件
	     String tree = KnowledgeUtils.createFileUpload(kfc);
		 mav.addObject("treeData", tree);
		 mav.setViewName(IContants.PREFIX + "jsp/System_Knowledge/saveFileUI.jsp");
		 return mav;
	 }
	 
	 //保存文件信息
	 @RequestMapping("saveFile")
	 public ModelAndView saveFile(MultipartFile upload,String fileMemo,int folderId,HttpSession session){
		 ModelAndView mav = new ModelAndView();
		 String message = "";
		 ServletContext sc = session.getServletContext();
		 try{
			 String originName = upload.getOriginalFilename();
			 String expendName = CommonUtils.getExpandName(originName);
			 String realName = CommonUtils.createNewFileName(expendName);
			 String filePath = "upload";
			 String realPath = sc.getRealPath(IContants.PREFIX + filePath )  +  "/";
		     boolean flag = FileuploadUtils.fileupload(upload, realPath, realName);
		     //保存文件信息
		     if(flag){
		    	 String encodeName = URLEncoder.encode(originName, "utf-8");
		    	 String filePic = CommonUtils.getUploadFilePic(expendName);
				 String fileSize = CommonUtils.getFileSize(upload.getSize());
				 String fileCreateTime = CommonUtils.createTime();
				 KnowledgeFileCustom file = new KnowledgeFileCustom();
		         file.setOriginName(originName);
		         file.setEncodeName(encodeName);
		         file.setRealName(realName);
		         file.setFilePath(filePath);
		         file.setFilePic(filePic);
		         file.setFileSize(fileSize);
		         file.setFileCreatetime(fileCreateTime);
		         file.setFileMemo(fileMemo);
		         file.setFolderId(folderId);
		         this.fileService.saveKnowledgeFileInfo(file);
		         message = "【提示信息】文件上传成功!";
		     }else{
		    	 message = "【提示信息】文件上传失败!";
		     }
		 
		 }catch(Exception e){
			 e.printStackTrace();
			 message = "【提示信息】文件上传抛出异常!";
			 throw new CustomException(message);
		 }
		 mav.addObject("message", message);
		 mav.setViewName("searchKnowledge.action");
		 return mav;
	 }
	 
	 //根据目录id，查询所属文件信息
	 @RequestMapping("searchFiles")
	 public @ResponseBody List<KnowledgeFileCustom>  searchFiles(int folderId){
		List<KnowledgeFileCustom> fileList = this.fileService.searchKnowledgeFileInfoByFolderId(folderId);
		return fileList;
		
	 }
	 
	//跳转到删除文件页面
	@RequestMapping("/delFileUI")
	public ModelAndView delFileUI(){
		ModelAndView mav = new ModelAndView();
		//获取树的根节点
		KnowledgeFolderCustom root = this.folderService.searchRootInfo();
		//生成树形控件
		String tree = KnowledgeUtils.createFileDownloadAndDelete(root);
		mav.addObject("treeData", tree);
		mav.setViewName(IContants.PREFIX + "jsp/System_Knowledge/delFileListUI.jsp");
		return mav;
	}
	
	//删除文件信息
	@RequestMapping("/delFile")
	public @ResponseBody int delFile(int fileId, HttpSession session){
		int flag = 0;
		boolean delFileInfoflag = false;
		ServletContext sc = session.getServletContext();
		//查询待删除文件的信息，先删除文件再删除记录
		KnowledgeFileCustom file = this.fileService.searchKnowledgeFileInfoById(fileId);
		String path = sc.getRealPath(IContants.PREFIX + file.getFilePath()) + "/";
		File delFile = new File(path + file.getRealName());
		//判断文件是否存在，即大于等于1
		if(delFile.exists()){
			delFileInfoflag = delFile.delete();
			if(delFileInfoflag){
				this.fileService.deleteKnowledgeFileInfo(fileId);
				flag = 1;
			}
		}
		return flag;
	}
}
