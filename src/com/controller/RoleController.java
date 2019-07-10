package com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.exception.CustomException;
import com.pojo.PrivilegeCustom;
import com.pojo.RoleCustom;
import com.service.IRoleService;
import com.utils.IContants;
import com.utils.PrivilegeUtils;

@Controller
public class RoleController {
    
	@Resource(name="roleService")
	private IRoleService irs;
	
	//角色列表，查询所有角色
	@RequestMapping("/searchRoleList")
	public ModelAndView searchRoleList(){
		 ModelAndView mav = new ModelAndView();
		 List<RoleCustom> roleList = irs.searchAllRoleInfo();
		 mav.addObject("roleData", roleList);
		 mav.setViewName(IContants.PREFIX + "jsp/System_Role/roleList.jsp");
		 return mav;
	}

	//新建角色，跳转新建界面。
	@RequestMapping("/saveRoleForUI")
	public ModelAndView saveRoleForUI(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName(IContants.PREFIX + "jsp/System_Role/saveRoleUI.jsp");
		return mav;
	}
	
	
	//保存角色，用于新建角色   save
	@RequestMapping("/saveRoleInfo")
	public ModelAndView saveRoleInfo(RoleCustom rc){
		ModelAndView mav = new ModelAndView();
		String message = "";
		try{
			 irs.saveNewRoleInfo(rc);
        	 message = "【提示信息】新建成功";
        }catch(Exception e){
        	 e.printStackTrace();
        	 message = "【提示信息】新建失败";
        	 throw new CustomException(message);
        }
        mav.addObject("message", message);
		mav.setViewName("searchRoleList.action");
		return mav;
	}
	
	//修改角色,跳转到修改界面
	@RequestMapping("/updateRoleUI")
	public ModelAndView updateRoleUI(int rid){
		  ModelAndView mav = new ModelAndView();
		  RoleCustom rc = irs.selectForUpdateRoleInfo(rid);
		  mav.addObject("roleInfo", rc);
		  mav.setViewName(IContants.PREFIX + "jsp/System_Role/updateRoleUI.jsp");
		  return mav;
	}
	
	//保存角色，用于修改角色　update
	@RequestMapping("/updateRoleInfo")
    public ModelAndView updateRoleInfo(RoleCustom rc){
    	ModelAndView mav = new ModelAndView();
    	String message = "";
    	try{
    		irs.updateRoleInfo(rc);
    		message = "【提示信息】修改成功";
    	}catch(Exception e){
    		e.printStackTrace();
    		message = "【提示信息】修改失败";
    		throw new CustomException();
    	}
    	mav.addObject("message", message);
    	mav.setViewName("searchRoleList.action");
    	return mav;
    }
	
	//删除角色
	@RequestMapping("/deleteRoleAllInfo")
	public ModelAndView deleteRoleAllInfo(int id){
		ModelAndView mav = new ModelAndView();
		String message = "";
		try{
			irs.deleteRoleInfo(id);
			message = "【提示信息】删除成功";
		}catch(Exception e){
			e.printStackTrace();
			message = "【提示信息】删除失败";
		}
		mav.addObject("message", message);
		mav.setViewName("searchRoleList.action");
		return mav;
	}
	
	//跳转到角色权限绑定页面
	@RequestMapping("/setRolePrivilegeUI")
	public ModelAndView setRolePrivilegeUI(int roleId,HttpSession session){
		ModelAndView mav = new ModelAndView();
		//查询角色信息
		RoleCustom rc = this.irs.searchRoleAndPrivilegeInfo(roleId);
		//从application对象中获取树节点
		ServletContext sc = session.getServletContext();
		PrivilegeCustom root = (PrivilegeCustom) sc.getAttribute("privilegeData");
		//生成权限树
		String tree = PrivilegeUtils.createPrivilegeTree(root, rc);
		mav.addObject("roleData",rc);
		mav.addObject("treeData", tree);
		mav.setViewName(IContants.PREFIX + "jsp/System_Role/saveRolePrivilegeUI.jsp");
		return mav;
	}
	
	//保存角色-权限绑定信息
	@RequestMapping("/saveRolePrivilege")
	public ModelAndView saveRolePrivilege(int roleId, String privilegeIds){
		ModelAndView mav = new ModelAndView();
		String message = "";
		try{
			//传入权限id  String类型 以及角色id
			//准备数据
			boolean flag = false;
			Map map = null;
			if(privilegeIds != null && !privilegeIds.trim().equals("")){
				String[] str = privilegeIds.split(",");
				if(str != null && str.length>0){
					map = new HashMap();
					flag = true;
					for(String temp : str){
						map.put(Integer.parseInt(temp), roleId);
					}
				}
				
			}
			//先删除角色原有权限
			//在添加角色新权限
			    this.irs.deleteRoleAndPrivilege(roleId);
			if(flag){
				this.irs.saveRoleAndPrivilegeInfoByMap(map);
			}
			message = "【提示信息】绑定成功 ";
		}catch(Exception e){
			e.printStackTrace();
			message = "【提示信息】绑定失败";
	        throw new CustomException(message);
		}
		mav.addObject("message", message);
		mav.setViewName("searchRoleList.action");
		return mav;
	}
}
