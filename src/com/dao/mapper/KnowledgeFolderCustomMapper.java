package com.dao.mapper;

import com.pojo.KnowledgeFolderCustom;


public interface KnowledgeFolderCustomMapper {
      
	//查询根节点
  	public KnowledgeFolderCustom searchRootInfo();

  	//新建目录
  	public void saveFolderInfo(KnowledgeFolderCustom kfc);
}
