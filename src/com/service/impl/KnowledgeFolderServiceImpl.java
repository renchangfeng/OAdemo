package com.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.mapper.KnowledgeFolderCustomMapper;
import com.pojo.KnowledgeFolderCustom;
import com.service.IKnowledgeFolderService;

@Service("knowledgeFolderService")
public class KnowledgeFolderServiceImpl implements IKnowledgeFolderService {

	@Resource(name = "knowledgeFolderCustomMapper")
	private KnowledgeFolderCustomMapper kfcm;
	
	public KnowledgeFolderCustom searchRootInfo() {
		KnowledgeFolderCustom kfc = this.kfcm.searchRootInfo();
		return kfc;
	}

  	//新建目录
  	public void saveFolderInfo(KnowledgeFolderCustom kfc){
  		this.kfcm.saveFolderInfo(kfc);
  	}

}
