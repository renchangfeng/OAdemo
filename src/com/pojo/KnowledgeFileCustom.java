package com.pojo;

import java.io.Serializable;

import com.po.KnowledgeFile;

public class KnowledgeFileCustom extends KnowledgeFile implements Serializable {
  
	private KnowledgeFolderCustom folder;

	public KnowledgeFolderCustom getFolder() {
		return folder;
	}

	public void setFolder(KnowledgeFolderCustom folder) {
		this.folder = folder;
	}

	
}
