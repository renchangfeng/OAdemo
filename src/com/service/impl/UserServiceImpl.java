package com.service.impl;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.mapper.UserCustomMapper;
import com.pojo.PrivilegeCustom;
import com.pojo.UserCustom;
import com.service.IUserService;
import com.utils.PrivilegeUtils;
import com.vo.UserVO;

@Service("userService")
public class UserServiceImpl implements IUserService {
     
	@Resource(name="userCustomMapper")
	private UserCustomMapper rcm;
	//查询用户所有信息，包括部门信息以及角色信息
	public List<UserCustom> searchAllUserInfoAndAllInfo(){
		 List<UserCustom> uc = this.rcm.searchAllUserInfoAndAllInfo();
		 return uc;
	}
	
	//新建用户信息
	public void saveNewUserInfo(UserCustom uc){
		this.rcm.saveNewUserInfo(uc);
	}
	
	//检验登录名唯一性
	public int checkLoginNameForOnly(UserCustom uc){
		int i = this.rcm.checkLoginNameForOnly(uc);
		return i;
	}
	
	//修改用户信息
	public void updateUserInfoById(UserCustom uc){
		this.rcm.updateUserInfoById(uc);
	}
	
	//修改个人设置信息，在个人设置界面
	public void updatePersonalInfoByUc(UserCustom uc){
		this.rcm.updatePersonalInfoByUc(uc);
	}
	
	//查询用户信息 用于修改 一个
	public UserCustom searchUserInfoByUpdate(int id){
		UserCustom uc = this.rcm.searchUserInfoByUpdate(id);
		return uc;
	}
	
	//删除用户信息
    public void deleteUserInfoById(int id){
    	this.rcm.deleteUserInfoById(id);
    }
    
    //初始化密码
    public void saveInitPassword(UserCustom uc){
        this.rcm.saveInitPassword(uc);
    }
    
    //修改密码  用于————个人设置
    public void updatePasswordInfoByUc(UserCustom uc){
    	this.rcm.updatePasswordInfoByUc(uc);
    }
    
    //查询用户所拥有的角色
    public UserCustom searchUserForRolesInfoById(int id){
    	UserCustom uc = this.rcm.searchUserForRolesInfoById(id);
    	return uc;
    }
    
    //绑定用户————角色信息
    public void saveUserAndRoleInfoForMap(Map userRoleList){
    	this.rcm.saveUserAndRoleInfoForMap(userRoleList);
    }
    
    //绑定用户----角色信息用于删除
    public void deleteRoleInfoForId(int uid){
    	this.rcm.deleteRoleInfoForId(uid);
    }
    
    //系统登陆
    public UserCustom syslogin(UserVO vo){
    	UserCustom uc = this.rcm.syslogin(vo);
    	if(uc != null){
    		//在业务逻辑层去重权限
    		List<PrivilegeCustom> newPrivis = PrivilegeUtils.clearSamePrivilege(uc.getRoleCustom());
        	uc.setPrivilegeList(newPrivis);
    	}
    	return uc;
    }
}
