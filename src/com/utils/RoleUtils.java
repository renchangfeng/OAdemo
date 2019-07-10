package com.utils;

import java.util.List;

import com.pojo.RoleCustom;
import com.pojo.UserCustom;

public final class RoleUtils{
    
	 //创建角色下拉框
	 public static String selectBoxForRoles(List<RoleCustom> rc,UserCustom uc){
		 StringBuffer stb = new StringBuffer("<select id=\"roleIdList\" name=\"roleIdList\" multiple=\"true\" size=\"10\" class=\"SelectStyle\" onchange=\"setRoleInfo(this.value)\">");
		 
		 setRoleList(rc,stb,uc);
		 stb.append("</select>");
		 return stb.toString();
	 }
	 
	 //循环<option>,并把用户已拥有角色注明
	 public static void setRoleList(List<RoleCustom> rc,StringBuffer stbb,UserCustom uc){
		    
		   List<RoleCustom> roleCustom = uc.getRoleCustom();
		   if(rc != null && rc.size()>0){
			   for(RoleCustom role:rc){
				   stbb.append("<option value=\"" + role.getRid() + "\"");
				   if(roleCustom != null && roleCustom.size() > 0){
					   for(RoleCustom roleCtm : roleCustom){
						   if(role.getRid() == roleCtm.getRid()){
							   stbb.append("selected");
						   }
					   }
				   }
				   stbb.append(">");
				   stbb.append(role.getRname());
				   stbb.append("</option>");
		
			   }
		   }
		   
		   //第二种方法。加个boolean锁。思路清晰
	/*	   if(roleList != null && roleList.size() > 0){
				List<RoleCustom> userRolesList = uc.getRoleList();
				for(RoleCustom rc : roleList){
					boolean flag = false;
					if(userRolesList != null && userRolesList.size() > 0){
						for(RoleCustom userRole : userRolesList){
							if(rc.getId() == userRole.getId()){
								select.append("<option value='" + rc.getId() + "' selected>" + rc.getRoleName() + "</option>");
							    flag = true;
							    break;
							}
						}
					}
					if(!flag){
						select.append("<option value='" + rc.getId() + "'>" + rc.getRoleName() + "</option>");
					}
				}
			}
		
		
		    select.append("</select>");    */
		
		
		   

	 }
	 
}
