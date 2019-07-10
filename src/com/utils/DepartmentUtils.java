package com.utils;

import java.util.List;

import com.pojo.DepartmentCustom;

public final class DepartmentUtils {
      
	
	//查询所有一级节点部门，用于新建
	public static String selectFirstDept(List<DepartmentCustom> treeNode){
		StringBuffer stb = new StringBuffer("<select name=\"parentDeptId\" class=\"SelectStyle\" >" + 
				                            "<option value=\"0\" selected=\"selected\">请选择部门</option>" );
		selectForOption(treeNode,stb);
		stb.append("</select>");
		return stb.toString();
		
	}
	//创建下拉列表，用于新建
	public static void selectForOption(List<DepartmentCustom> tdNode,StringBuffer stb){
		
		if(tdNode != null && tdNode.size() > 0){
			for(DepartmentCustom dc : tdNode){
				String prefix = "";
				for(int i=1;i<dc.getLevel()*2;i++){
					prefix = prefix + "&nbsp;&nbsp;";
				}
				stb.append("<option value=\"");
				stb.append(dc.getId());
				stb.append("\">");
				stb.append(prefix + "┠" + dc.getDname());
				stb.append("</option>");
				
				if(dc.getChildrenList()!=null && dc.getChildrenList().size()>0){
					List<DepartmentCustom> childNode = dc.getChildrenList();
					selectForOption(childNode,stb);
				}
				
		   }
		
	  } 
	 		
	}
	
	//查询所有一级节点部门，用于修改（不包含要修改的信息及其子部门）
	public static String selectFirstDeptForUpdate(List<DepartmentCustom> treeNode,int deptId,int parentId){
		StringBuffer stb = new StringBuffer("<select name=\"parentDeptId\" class=\"SelectStyle\" >" + 
				                            "<option value=\"0\" >请选择部门</option>" );
		RemovePointNode(treeNode,deptId);
		selectForOptionForUpdate(treeNode,stb,parentId);
		stb.append("</select>");

		return stb.toString();
		
	}
	
	//删除选中的部门及其子部门的信息，在select中
	public static void RemovePointNode(List<DepartmentCustom> treeNode,int deptId){
		DepartmentCustom dept = null;
		if(treeNode != null && treeNode.size() > 0){
			for(DepartmentCustom dc : treeNode){
				//如果是查询的部门，将部门及其子部门都不遍历。
				if(dc.getId() == deptId){
					dept = dc;
				}else{
					if(dc.getChildrenList() != null && dc.getChildrenList().size() > 0){
						List<DepartmentCustom> children = dc.getChildrenList();
						RemovePointNode(children,deptId);
					}
				}
			}
			if(dept!=null){
				treeNode.remove(dept);
			}
		}
	}
	
	//创建下拉列表，用于修改（不包含要修改的信息及其子部门）
	public static void selectForOptionForUpdate(List<DepartmentCustom> tdNode,StringBuffer stb,int parentId){
		
		//自己犯的错误，如果放在外面就是全局变量，所有一级节点都会有selected
		//String suffix = "";
		if(tdNode != null && tdNode.size() > 0){
			DepartmentCustom department = null;
			for(DepartmentCustom dc : tdNode){
				String prefix = "";
				String suffix = "";
				for(int i=1;i<dc.getLevel()*2;i++){
					prefix = prefix + "&nbsp;&nbsp;";
				}
				
				if(dc.getId() == parentId){
					System.out.println("parentId: " + parentId );
					suffix = "selected";
				}
				stb.append("<option value=\"" + dc.getId() + "\"" + suffix +">");
				stb.append(prefix + "┠" + dc.getDname());
				stb.append("</option>");
				//处理子节点
				List<DepartmentCustom> childNode = dc.getChildrenList();
				if(childNode != null && childNode.size()>0){
					selectForOptionForUpdate(childNode,stb,parentId);
				}	
		   }
			 tdNode.remove(department);
	   } 
	}
		
}
