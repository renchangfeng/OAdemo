package com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.exception.CustomException;
import com.pojo.DepartmentCustom;
import com.pojo.RoleCustom;
import com.pojo.UserCustom;
import com.service.IDepartmentService;
import com.service.IRoleService;
import com.service.IUserService;
import com.utils.DepartmentUtils;
import com.utils.IContants;
import com.utils.RoleUtils;
import com.vo.UserVO;

@Controller
public class UserController {
    
	@Resource(name="userService")
   	private IUserService ius;
   	
	@Resource(name="deptService")
	private IDepartmentService ids;
	
	@Resource(name="roleService")
	private IRoleService irs;
	
	//系统登陆
	@RequestMapping("/sysLogin")
	public ModelAndView sysLogin(UserVO vo,HttpSession ss){
		ModelAndView mav = new ModelAndView();
		UserCustom uc = this.ius.syslogin(vo);
		String message = "";
		String path = "";
		if(uc != null){
			ss.setAttribute("user", uc);
			path = IContants.PREFIX + "jsp/index.jsp";
		}else{
			path = "login.jsp";
			message = "【提示信息】用户名与密码不匹配，请确认后重试";
		}
		mav.addObject("message", message);
		mav.setViewName(path);
		return mav;
	}
	
	//系统退出
	@RequestMapping("/sysLogout")
	public ModelAndView sysLogout(HttpSession ss){
		ModelAndView mav = new ModelAndView();
		ss.invalidate();
		mav.setViewName("logout.jsp");
		return mav;
	}
	
	
	//查询用户所有信息
	@RequestMapping("/searchAllUserInfoList")
	public ModelAndView searchAllUserInfoList(){
		ModelAndView mav = new ModelAndView();
		List<UserCustom> uc = this.ius.searchAllUserInfoAndAllInfo();
		mav.addObject("ucData", uc);
		mav.setViewName(IContants.PREFIX + "jsp/System_User/userList.jsp");
		return mav;
	}
   	
	//跳转至新建用户界面
	@RequestMapping("/saveUserInfoForUI")
	public ModelAndView saveUserInfoForUI(){
		ModelAndView mav = new ModelAndView();
		List<DepartmentCustom> deptCustom = this.ids.searchAllFirstDeptInformation();
		String str = DepartmentUtils.selectFirstDept(deptCustom);
		mav.addObject("select", str);
		mav.setViewName(IContants.PREFIX + "jsp/System_User/userSaveUI.jsp");
		return mav;
	}
	
	//新建用户信息
	@RequestMapping("/saveUserInfo")
	public ModelAndView saveUserInfo(UserCustom uc){
		ModelAndView mav = new ModelAndView();
		String message = "";
		try{
			ius.saveNewUserInfo(uc);
			message = "【提示信息】新建成功";
		}catch(Exception e){
			e.printStackTrace();
			message = "【提示信息】新建失败";
			throw new CustomException(message);
		}
		mav.addObject("message", message);
		mav.setViewName("searchAllUserInfoList.action");
		return mav;
	}
	
	//检查登录名唯一性
	@RequestMapping("/checkLoginname")
	public @ResponseBody int checkLoginname(String loginname,int userId){
        UserCustom uc = new UserCustom();
        uc.setUid(userId);
        uc.setLoginname(loginname);
		int i = ius.checkLoginNameForOnly(uc);
		return i;
	}
	
	//跳转至修改用户信息  传入id值
	@RequestMapping("/updateUserInfoForUI")
	public ModelAndView updateUserInfoForUI(int userId){
		ModelAndView mav = new ModelAndView();
		UserCustom uc = this.ius.searchUserInfoByUpdate(userId);
		List<DepartmentCustom> deptCustom = this.ids.searchAllFirstDeptInformation();
		String str = DepartmentUtils.selectFirstDeptForUpdate(deptCustom, -1, uc.getParentDeptId());
		mav.addObject("updateSelect", str);
		mav.addObject("userData", uc);
		mav.setViewName(IContants.PREFIX + "jsp/System_User/userUpdateUI.jsp");
		return mav;
	}
	
	//修改用户信息
	@RequestMapping("/updateUserInfo")
	public ModelAndView updateUserInfo(UserCustom uc){
		ModelAndView mav = new ModelAndView();
		String message = "";
		try{
			this.ius.updateUserInfoById(uc);
			message = "【提示信息】修改成功";
		}catch(Exception e){
			e.printStackTrace();
			message = "【提示信息】修改失败";
			throw new CustomException(message);
		}
		mav.addObject("message", message);
		mav.setViewName("searchAllUserInfoList.action");
		return mav;
	}
	
	//初始化密码
	@RequestMapping("/saveInitPassword")
	public @ResponseBody int saveInitPassword(String initPassword, int userId){
		//0：代表初始化密码成功；1：代表失败
		int flag = 1;
		UserCustom uc = new UserCustom();
		uc.setUid(userId);
		uc.setPass(initPassword);
		
		try{
			this.ius.saveInitPassword(uc);
			flag = 0;
		}catch(Exception e){
			e.printStackTrace();
			throw new CustomException("【提示信息】 初始化密码失败！");
		}

		return flag;
	}
	
	//删除角色
	@RequestMapping("/deleteUserInfo")
	public ModelAndView deleteUserInfo(int userId){
		ModelAndView mav = new ModelAndView();
		String message = "";
		try{
			//先删除用户绑定角色信息
			this.ius.deleteRoleInfoForId(userId);
			//再删除用户信息
			this.ius.deleteUserInfoById(userId);
			message = "【提示信息】删除成功";
		}catch(Exception e){
			e.printStackTrace();
			message = "【提示信息】删除失败";
			throw new CustomException(message);
		}
		mav.addObject("message", message);
		mav.setViewName("searchAllUserInfoList.action");
		return mav;
	}
	
	//跳转到分配角色页面
	@RequestMapping("/assignRolesUI")
	public ModelAndView assignRolesUI(int userId){
		ModelAndView mav = new ModelAndView();
		List<RoleCustom> rc = irs.searchAllRoleInfo();
		UserCustom uc = this.ius.searchUserForRolesInfoById(userId);
		String strRole = RoleUtils.selectBoxForRoles(rc,uc);
		
		mav.addObject("roleData", strRole);
		mav.addObject("ucData", uc);
		mav.setViewName(IContants.PREFIX + "jsp/System_User/saveUserPrivilegeUI.jsp");
		return mav;
	}
	
	//绑定用户——角色信息
	@RequestMapping("/saveUserForRoleInfo")
	public ModelAndView saveUserForRoleInfo(int userId,String roleIdList){
		ModelAndView mav = new ModelAndView();
		String message = "";
		try{
			//判断角色信息是否为空
			if(roleIdList != null && !roleIdList.trim().equals("")){
				Map map = new HashMap();
				String[] temp = roleIdList.split(",");
				if(temp != null && temp.length>0){
					for(String str : temp){
						map.put(Integer.parseInt(str), userId);
					}
				}
				//先删除用户绑定角色信息
				this.ius.deleteRoleInfoForId(userId);
				//再对用户进行角色绑定
				this.ius.saveUserAndRoleInfoForMap(map);
				
			}else{
				this.ius.deleteRoleInfoForId(userId);
			}
			message = "【提示信息】绑定角色成功";
		}catch(Exception e){
			e.printStackTrace();
			message = "【提示信息】绑定角色失败";
			throw new CustomException(message);
		}
		mav.addObject("message", message);
		mav.setViewName("searchAllUserInfoList.action");
		return mav;
	}
	
	//跳转个人设置页面
	@RequestMapping("/updatePersonalInfoUI")
	public ModelAndView updatePersonalInfoUI(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName(IContants.PREFIX + "jsp/System_User/updatePersonalInfoUI.jsp");
		return mav;
	}
	
	//保存个人设置 用于修改
	@RequestMapping("/updatePersonalInfo")
	public ModelAndView updatePersonalInfo(UserCustom uc,HttpSession session){
		ModelAndView mav = new ModelAndView();
		try{
			this.ius.updatePersonalInfoByUc(uc);
			UserCustom user = (UserCustom) session.getAttribute("user");
			user.setUname(uc.getUname());
			user.setSex(uc.getSex());
			user.setPhone(uc.getPhone());
			user.setEmail(uc.getEmail());
			user.setMemo(uc.getMemo());
		}catch(Exception e){
			e.printStackTrace();
			throw new CustomException("【提示信息】个人设置修改失败");
		}
		mav.setViewName(IContants.PREFIX + "jsp/System_User/updatePersonalInfoUI.jsp");
		return mav;
	}
	
	//修改密码页面
	@RequestMapping("/updatePasswordInfoUI")
	public ModelAndView updatePasswordInfoUI(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName(IContants.PREFIX + "jsp/System_User/updatePasswordInfoUI.jsp");
		return mav;
	}
	
	//修改密码
	@RequestMapping("/updatePasswordInfo")
	public ModelAndView updatePasswordInfo(int userId,String pass,HttpSession session){
		ModelAndView mav = new ModelAndView();
//		UserCustom uc = (UserCustom) session.getAttribute("user");
//		uc.setUid(uid);
//		uc.setPass(pass);
//		this.ius.updatePasswordInfoByUc(uc);
		//上面是错误的，不应该先修改session里的user  如果修改失败则数据保存错误
		String message = "";
		try{
			UserCustom uc = new UserCustom();
			uc.setUid(userId);
			uc.setPass(pass);
			this.ius.updatePasswordInfoByUc(uc);
			message = "【提示信息】 密码修改成功！";
			UserCustom user = (UserCustom) session.getAttribute("user");
			user.setPass(pass);		
		}catch(Exception e){
			message = "【提示信息】 密码修改失败！";
			e.printStackTrace();
			throw new CustomException(message);
		}
		mav.addObject("message", message);
    	mav.setViewName(IContants.PREFIX + "jsp/System_User/updatePasswordInfoUI.jsp");
    	
		return mav;
	}
	
}
