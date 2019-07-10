package com.dao.mapper;

import java.util.List;

import com.pojo.DepartmentCustom;

public interface DepartmentCustomMapper {
     
	//查询所有部门信息
	public List<DepartmentCustom> searchAllDept();
	
	//查询子部门的信息
	public DepartmentCustom searchChildrenDeptByParentId(int id);
	
	//查询所有一级部门信息
	public List<DepartmentCustom> searchAllFirstDeptInformation();
	
	//保存新建部门信息
	public void saveNewDeptInformation(DepartmentCustom dc);
	
	//修改部门信息
	public void updateDeptInformation(DepartmentCustom dc);
	
	//查询部门信息，用于修改
	public DepartmentCustom selectbasicDeptInfoForUpdate(int id);
	
	//删除部门信息
	public void deleteBasicDept(int id);
}
