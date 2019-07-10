package com.vo;

import com.pojo.UserCustom;

public class UserVO {
       private UserCustom uc;
       
       private String loginName;
       
       private String pass;

	public UserCustom getUc() {
		return uc;
	}

	public void setUc(UserCustom uc) {
		this.uc = uc;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
       
}
