package com.dao.mapper;

import java.util.List;

import com.pojo.KnowledgeFileCustom;


public interface KnowledgeFileCustomMapper {
      
	//根据目录id查询所属文件信息
	 public List<KnowledgeFileCustom> searchKnowledgeFileInfoByFolderId(int folderId);
	 
	 //保存文件信息
	 public void saveKnowledgeFileInfo(KnowledgeFileCustom file);
	 
	 //删除文件信自己
	 public void deleteKnowledgeFileInfo(int fileId);
	 
	 //根据文件id，查询基本信息，用于删除使用
	 public KnowledgeFileCustom searchKnowledgeFileInfoById(int fileId);

}
