package com.po;

import java.io.Serializable;

public class Role implements Serializable{
     private int rid;
     
     private String rname;
     
     private String rmemo;

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public String getRmemo() {
		return rmemo;
	}

	public void setRmemo(String rmemo) {
		this.rmemo = rmemo;
	}
}
