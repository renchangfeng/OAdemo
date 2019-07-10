package com.pojo;

import java.io.Serializable;
import java.util.List;

import com.po.User;

public class UserCustom extends User implements Serializable{//序列化接口，将session存入硬盘中
   
	private DepartmentCustom deptCustom;
   
    private List<RoleCustom> roleCustom;

    //该集合仅用于判断当前用户的权限列表中是否拥有系统权限使用，不进行映射
    private List<PrivilegeCustom> privilegeList;
    
	public List<PrivilegeCustom> getPrivilegeList() {
		return privilegeList;
	}

	public void setPrivilegeList(List<PrivilegeCustom> privilegeList) {
		this.privilegeList = privilegeList;
	}

	
	
	public DepartmentCustom getDeptCustom() {
		return deptCustom;
		
	}

	public void setDeptCustom(DepartmentCustom deptCustom) {
		this.deptCustom = deptCustom;
	}

	public List<RoleCustom> getRoleCustom() {
		return roleCustom;
	}

	public void setRoleCustom(List<RoleCustom> roleCustom) {
		this.roleCustom = roleCustom;
	}
    
	public boolean hasPrivilegeForId(int pid){
	    boolean flag = false;
	    
	    if(this.getIsAdmin() == 0){
	       flag = true;	
	    }else{
	    	if(this.privilegeList != null && this.privilegeList.size() > 0){
	        	for(PrivilegeCustom pc : this.privilegeList){
	        		if(pc.getId() == pid){
	        			flag = true;
	        			break;
	        		}
	        	}
	        }
	    }
        
		
        return flag;
	}
	//判断角色是否拥有url
	public boolean hasPrivilegeForUrl(String url){
		boolean flag = false;
		System.out.println("hasPrivilegeForURL11->purl: " + url);
		if(this.getIsAdmin() == 0){
			flag = true;
		}else{
			if(privilegeList != null && privilegeList.size()>0){
				for(PrivilegeCustom pc: this.privilegeList){
					if(pc.getUrl() != null && pc.getUrl().equals(url)){
						flag = true;
						break;
					}
				}
			}
		}
		System.out.println("角色是否拥有url: " + flag);
		return flag;
	}
}
