package com.pojo;

import java.io.Serializable;
import java.util.List;

import com.po.KnowledgeFolder;

public class KnowledgeFolderCustom extends KnowledgeFolder implements Serializable {

	private KnowledgeFolderCustom parent;
	
	private List<KnowledgeFolderCustom> childrenList;

	public KnowledgeFolderCustom getParent() {
		return parent;
	}

	public void setParent(KnowledgeFolderCustom parent) {
		this.parent = parent;
	}

	public List<KnowledgeFolderCustom> getChildrenList() {
		return childrenList;
	}

	public void setChildrenList(List<KnowledgeFolderCustom> childrenList) {
		this.childrenList = childrenList;
	}
	
	
}
