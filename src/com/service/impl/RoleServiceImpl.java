package com.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.mapper.RoleCustomMapper;
import com.pojo.RoleCustom;
import com.service.IRoleService;

@Service("roleService")
public class RoleServiceImpl implements IRoleService {
    
	@Resource(name ="roleCustomMapper")
	private RoleCustomMapper rcm;
	
	//查询所有角色信息，
	public List<RoleCustom> searchAllRoleInfo() {
		List<RoleCustom> role = this.rcm.searchAllRoleInfo();
		return role;
	}

	//新建角色 (保存)
	public void saveNewRoleInfo(RoleCustom rc){
		this.rcm.saveNewRoleInfo(rc);
	}
	
    //修改角色信息
	public void updateRoleInfo(RoleCustom rc){
		this.rcm.updateRoleInfo(rc);
	}
		
	//查询修改角色信息
	public RoleCustom selectForUpdateRoleInfo(int id){
		RoleCustom rc = this.rcm.selectForUpdateRoleInfo(id);
		return rc;
	}
	
	//删除角色绑定权限
	public void deleteRoleAndPrivilege(int rid){
		this.rcm.deleteRoleAndPrivilege(rid);
	}
	
	
    //删除角色
	public void deleteRoleInfo(int id){
		this.rcm.deleteRoleInfo(id);
	}
	
	//根据角色id查询角色信息：角色信息一级其所拥有的权限信息 用于 分配角色-权限 页面使用
	public RoleCustom searchRoleAndPrivilegeInfo(int rid){
		RoleCustom rc = this.rcm.searchRoleAndPrivilegeInfo(rid);
		return rc;
	}
	
	//保存角色-权限绑定信息Map集合中的key存储的是权限id，value存储的是角色id
	public void saveRoleAndPrivilegeInfoByMap(Map roleMap){
		
		this.rcm.saveRoleAndPrivilegeInfoByMap(roleMap);
	}
}
