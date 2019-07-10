package com.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.pojo.PrivilegeCustom;
import com.pojo.RoleCustom;

public final class PrivilegeUtils {
    
	//去重重复权限
	 public static List<PrivilegeCustom> clearSamePrivilege(List<RoleCustom> roleList){
		 //list用于存储去重后的集合
		 List<PrivilegeCustom> newPrivilegeList = null;
		 if(roleList !=null && roleList.size()>0){
			 //set集合用来去重
			 //需要重写hashcode和equals
			 Set<PrivilegeCustom> priviSet = new HashSet<PrivilegeCustom>();
			 newPrivilegeList = new ArrayList<PrivilegeCustom>();
			 
			 for(RoleCustom rc :roleList){
				 List<PrivilegeCustom> privs = rc.getPrivilegeList();
				 if(privs != null && privs.size() >0){
					 for(PrivilegeCustom pc : privs){
						if(priviSet.add(pc)){
							newPrivilegeList.add(pc);
						}
					 }
				 }
			 }
		 }
		 
		 return newPrivilegeList;
	 }
	 
	 //判断传入url，是否为公共权限
	    public static boolean judgePublicPrivilege(String url,List<PrivilegeCustom> privilegeList){
	    	boolean flag = false;
	    	 
	    	if(privilegeList != null && privilegeList.size() > 0){
	    		if(url != null && !url.trim().equals("")){
	    			for(PrivilegeCustom pc : privilegeList){
	    				 if(pc.getUrl() != null && pc.getUrl().equals(url)){
	    					 flag = true;
	    					 break;
	    				 }
	    			}
	    		}
	    	}
	    	
	    	return flag;
	    }
	    
	    
	 //用于生成权限分配页面中的树形控件
	 public static String createPrivilegeTree(PrivilegeCustom pc,RoleCustom rc){
		 StringBuffer tree = new StringBuffer();
		 createTreeNode(pc,rc,tree);
		 
		 return tree.toString();
	 }
	 
	 //生成树节点 (当前节点)
	 public static void createTreeNode(PrivilegeCustom currentNode,RoleCustom rc,StringBuffer tree){
		  //当前节点
		  tree.append("<li id=\"li_" + currentNode.getId() + "\">");
		  if(currentNode.getLevel() == 0){
			  tree.append(currentNode.getPrivilegeName());
		  }else if(currentNode.getLevel() != 0){
			   
			   if(currentNode.getIsLeaf() == 0 &&  rc.hasPrivilegeForId(currentNode.getId()) ){
				   tree.append("<span class=\"folder\"><input type=\"checkbox\" checked name=\"resourceIdList\" value=\"");
				   tree.append(currentNode.getId());
				   tree.append("\" id=\"cb_");
				   tree.append(currentNode.getId());
				   tree.append("\" onclick='doChecked(\"li_");
				   tree.append(currentNode.getId());
				   tree.append("\", this.checked)'/><label for=\"cb_");
				   tree.append(currentNode.getId());
				   tree.append("\">");
				   tree.append(currentNode.getPrivilegeName());
				   tree.append("</label></span>");
			   }else if(currentNode.getIsLeaf() == 0 &&  !rc.hasPrivilegeForId(currentNode.getId()) ){
				   tree.append("<span class=\"folder\"><input type=\"checkbox\" name=\"resourceIdList\" value=\"");
				   tree.append(currentNode.getId());
				   tree.append("\" id=\"cb_");
				   tree.append(currentNode.getId());
				   tree.append("\" onclick='doChecked(\"li_");
				   tree.append(currentNode.getId());
				   tree.append("\", this.checked)'/><label for=\"cb_");
				   tree.append(currentNode.getId());
				   tree.append("\">");
				   tree.append(currentNode.getPrivilegeName());
				   tree.append("</label></span>");
			   }else if(currentNode.getIsLeaf() == 1 &&  rc.hasPrivilegeForId(currentNode.getId()) ){
				   tree.append("<span class=\"file\"><input type=\"checkbox\" checked name=\"resourceIdList\" value=\"");
				   tree.append(currentNode.getId());
				   tree.append("\" id=\"cb_");
				   tree.append(currentNode.getId());
				   tree.append("\" onclick='doChecked(\"li_");
				   tree.append(currentNode.getId());
				   tree.append("\", this.checked)'/><label for=\"cb_");
				   tree.append(currentNode.getId());
				   tree.append("\">");
				   tree.append(currentNode.getPrivilegeName());
				   tree.append("</label></span>");
			   }else if(currentNode.getIsLeaf() == 1 &&  !rc.hasPrivilegeForId(currentNode.getId()) ){
				   tree.append("<span class=\"file\"><input type=\"checkbox\" name=\"resourceIdList\" value=\"");
				   tree.append(currentNode.getId());
				   tree.append("\" id=\"cb_");
				   tree.append(currentNode.getId());
				   tree.append("\" onclick='doChecked(\"li_");
				   tree.append(currentNode.getId());
				   tree.append("\", this.checked)'/><label for=\"cb_");
				   tree.append(currentNode.getId());
				   tree.append("\">");
				   tree.append(currentNode.getPrivilegeName());
				   tree.append("</label></span>");
			   }
		  }
		  
		  //查询子节点
		  
		  List<PrivilegeCustom> childrenList = currentNode.getChildrenList();
		  if(childrenList != null && childrenList.size()>0){
			  tree.append("<ul>");
			    for(PrivilegeCustom child : childrenList){
			    	createTreeNode(child,rc,tree);
			    }
			  
			  tree.append("</ul>");
			 
		  }
		  
	 }
}
