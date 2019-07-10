package com.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.mapper.KnowledgeFileCustomMapper;
import com.pojo.KnowledgeFileCustom;
import com.service.IKnowledgeFileService;

@Service("knowledgeFileService")
public class KnowledgeFileServiceImpl implements IKnowledgeFileService{

	 @Resource(name = "knowledgeFileCustomMapper")
	 private KnowledgeFileCustomMapper dao;
	
	//根据目录id查询所属文件信息
	 public List<KnowledgeFileCustom> searchKnowledgeFileInfoByFolderId(int folderId){
		 List<KnowledgeFileCustom> fileList = this.dao.searchKnowledgeFileInfoByFolderId(folderId);
		 return fileList;
	 }
	 
	 //保存文件信息
	 public void saveKnowledgeFileInfo(KnowledgeFileCustom file){
		 this.dao.saveKnowledgeFileInfo(file);
	 }
	 
	 //删除文件信自己
	 public void deleteKnowledgeFileInfo(int fileId){
		 this.dao.deleteKnowledgeFileInfo(fileId);
	 }
	 
	 //根据文件id，查询基本信息，用于删除使用
	 public KnowledgeFileCustom searchKnowledgeFileInfoById(int fileId){
		 KnowledgeFileCustom file = this.dao.searchKnowledgeFileInfoById(fileId);
		 return file;
	 }
}
