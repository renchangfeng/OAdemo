package com.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.mapper.DepartmentCustomMapper;
import com.pojo.DepartmentCustom;
import com.service.IDepartmentService;

@Service("deptService")
public class DepartmentServiceImpl implements IDepartmentService{

	@Resource(name="departmentCustomMapper")
	private DepartmentCustomMapper dao;
	
	public List<DepartmentCustom> searchAllDept() {
		List<DepartmentCustom> deptList = this.dao.searchAllDept();
		return deptList;
	}
    
	public DepartmentCustom searchChildrenDeptByParentId(int id){
		DepartmentCustom dc = this.dao.searchChildrenDeptByParentId(id);
		return dc;
	}
	
	//查询所有一级部门信息
	public List<DepartmentCustom> searchAllFirstDeptInformation(){
		
		List<DepartmentCustom> dc = this.dao.searchAllFirstDeptInformation();
		return dc;
	}
	
	public void saveNewDeptInformation(DepartmentCustom dc){
		
		 this.dao.saveNewDeptInformation(dc);
	}
	
	//修改部门信息
	public void updateDeptInformation(DepartmentCustom dc){
		
		this.dao.updateDeptInformation(dc);
	}
	
	//查询部门信息，用于修改
	public DepartmentCustom selectbasicDeptInfoForUpdate(int id){
		DepartmentCustom dc = this.dao.selectbasicDeptInfoForUpdate(id);
		
		return dc;
	}
	
	//删除部门信息
	public void deleteBasicDept(int id){
		this.dao.deleteBasicDept(id);
	}
}
