package com.po;

import java.io.Serializable;

public class KnowledgeFile implements Serializable {
         
	private int id;
	
	private String realName;
	
	private String originName;
	
	private String encodeName;
	
	private String filePath;
	
	private String filePic;
	
	private String fileMemo;
	
	private String fileCreatetime;
	
	private String fileSize;
	
	private int folderId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

	public String getEncodeName() {
		return encodeName;
	}

	public void setEncodeName(String encodeName) {
		this.encodeName = encodeName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFilePic() {
		return filePic;
	}

	public void setFilePic(String filePic) {
		this.filePic = filePic;
	}

	public String getFileMemo() {
		return fileMemo;
	}

	public void setFileMemo(String fileMemo) {
		this.fileMemo = fileMemo;
	}

	public String getFileCreatetime() {
		return fileCreatetime;
	}

	public void setFileCreatetime(String fileCreatetime) {
		this.fileCreatetime = fileCreatetime;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public int getFolderId() {
		return folderId;
	}

	public void setFolderId(int folderId) {
		this.folderId = folderId;
	}
	
	
}
