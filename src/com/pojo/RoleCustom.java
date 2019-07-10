package com.pojo;

import java.io.Serializable;
import java.util.List;

import com.po.Role;

public class RoleCustom extends Role implements Serializable{
       
	private List<PrivilegeCustom> privilegeList;

	public List<PrivilegeCustom> getPrivilegeList() {
		return privilegeList;
	}

	public void setPrivilegeList(List<PrivilegeCustom> privilegeList) {
		this.privilegeList = privilegeList;
	}
	
	//判断角色是否拥有此权限
	public boolean hasPrivilegeForId(int rid){
		boolean flag = false;
		
		if(this.privilegeList != null && this.privilegeList.size()>0){
			 for(PrivilegeCustom pc : this.privilegeList){
				 if(pc.getId() == rid){
					 flag = true;
					 break;
				 }
			 }
		}
		
		return flag;
	}
}
