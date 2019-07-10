package com.service;

import com.pojo.KnowledgeFolderCustom;

public interface IKnowledgeFolderService {

	//查询根节点
  	public KnowledgeFolderCustom searchRootInfo();

  	//新建目录
  	public void saveFolderInfo(KnowledgeFolderCustom kfc);
}
