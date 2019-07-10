package com.pojo;

import java.io.Serializable;
import java.util.List;

import com.po.Department;

public class DepartmentCustom extends Department implements Serializable{
     
	 //上级部门
	private DepartmentCustom parent;
	 
	private List<DepartmentCustom> childrenList;

	public DepartmentCustom getParent() {
		return parent;
	}

	public void setParent(DepartmentCustom parent) {
		this.parent = parent;
	}

	public List<DepartmentCustom> getChildrenList() {
		return childrenList;
	}

	public void setChildrenList(List<DepartmentCustom> childrenList) {
		this.childrenList = childrenList;
	}
	 
}
