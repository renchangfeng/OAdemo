package com.pojo;

import java.io.Serializable;
import java.util.List;

import com.po.Privilege;

public class PrivilegeCustom extends Privilege{
   
    private PrivilegeCustom parent;
    //直接子节点集合
    private List<PrivilegeCustom> childrenList;
    /*所有属性的set、get方法*/
    //工具方法：判断当前节点的子点节的层次，用于显示不同样式

	public PrivilegeCustom getParent() {
		return parent;
	}
	public void setParent(PrivilegeCustom parent) {
		this.parent = parent;
	}
	public List<PrivilegeCustom> getChildrenList() {
		return childrenList;
	}
	public void setChildrenList(List<PrivilegeCustom> childrenList) {
		this.childrenList = childrenList;
	}
	
    //判断一级节点是否有两级节点
    public boolean judgeMenuLevel(List<PrivilegeCustom> children){
        boolean flag = false;
        if(children != null && children.size() > 0){
             for(PrivilegeCustom pc : children){
			 	if(pc.getLevel() == 2){
			 	       flag = true;
			 	       break;
			 	}
             }
         }		
         return flag;
    }
    
   
}
