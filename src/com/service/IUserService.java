package com.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pojo.UserCustom;
import com.vo.UserVO;

public interface IUserService {
  
	//查询用户所有信息，包括部门信息以及角色信息
	public List<UserCustom> searchAllUserInfoAndAllInfo();
	
	//新建用户信息
	public void saveNewUserInfo(UserCustom uc); 
	
	//检验登录名唯一性
	public int checkLoginNameForOnly(UserCustom uc);
	
	//修改用户信息
	public void updateUserInfoById(UserCustom uc);
	
	//修改个人设置信息，在个人设置界面
	public void updatePersonalInfoByUc(UserCustom uc);
	
	//查询用户信息 用于修改 一个
	public UserCustom searchUserInfoByUpdate(int id);
	
	//删除用户信息
    public void deleteUserInfoById(int id);
    
    //初始化密码
    public void saveInitPassword(UserCustom uc);
    
    //修改密码  用于————个人设置
    public void updatePasswordInfoByUc(UserCustom uc);
    
    //查询用户所拥有的角色
    public UserCustom searchUserForRolesInfoById(int id);
    
    //绑定用户————角色信息
    public void saveUserAndRoleInfoForMap(Map userRoleList);
    
    //绑定用户----角色信息用于删除
    public void deleteRoleInfoForId(int uid);
    
    //系统登陆
    public UserCustom syslogin(UserVO vo);
}
