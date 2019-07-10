package com.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pojo.RoleCustom;


public interface IRoleService {
    
    //查询所有角色信息
	public List<RoleCustom> searchAllRoleInfo();
	
	//新建角色 (保存)
	public void saveNewRoleInfo(RoleCustom rc);
	
	 //修改角色信息
	public void updateRoleInfo(RoleCustom rc);
	
	//查询修改角色信息
	public RoleCustom selectForUpdateRoleInfo(int id);
	
	//删除角色绑定权限
	public void deleteRoleAndPrivilege(int rid);
	
    //删除角色
	public void deleteRoleInfo(int id);
	
	//根据角色id查询角色信息：角色信息一级其所拥有的权限信息 用于 分配角色-权限 页面使用
	public RoleCustom searchRoleAndPrivilegeInfo(int rid);
	
	//保存角色-权限绑定信息Map集合中的key存储的是权限id，value存储的是角色id
	public void saveRoleAndPrivilegeInfoByMap(Map roleMap);
}
