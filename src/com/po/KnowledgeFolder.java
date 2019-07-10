package com.po;

import java.io.Serializable;

public class KnowledgeFolder implements Serializable {

	private int id;
	
	private String folderName;
	
	private String folderMemo;
	
	private int level;
	
	private int isLeaf;
	
	private int parentId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public String getFolderMemo() {
		return folderMemo;
	}

	public void setFolderMemo(String folderMemo) {
		this.folderMemo = folderMemo;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(int isLeaf) {
		this.isLeaf = isLeaf;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	
	
}
