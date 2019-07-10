package com.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.exception.CustomException;
import com.pojo.DepartmentCustom;
import com.service.IDepartmentService;
import com.utils.DepartmentUtils;
import com.utils.IContants;

@Controller
public class DepartmentController {
    
	@Resource(name="deptService")
	private IDepartmentService ids;
	
	//查看所有部门信息
	@RequestMapping("/searchDepts")
	public ModelAndView searchDepts(){
		ModelAndView mav = new ModelAndView();
		List<DepartmentCustom> deptList = this.ids.searchAllDept();
		mav.addObject("deptData", deptList);
		mav.setViewName(IContants.PREFIX + "jsp/System_Department/deptList.jsp");
		return mav;
	}
	
	//查看部门的下属部门信息
	@RequestMapping("/searchChildDepts")
	public ModelAndView searchChildDepts(int id){
		ModelAndView mav = new ModelAndView();
		DepartmentCustom dc = this.ids.searchChildrenDeptByParentId(id);
		DepartmentCustom depter = this.ids.selectbasicDeptInfoForUpdate(dc.getParentDeptId());
		List<DepartmentCustom> listChild = dc.getChildrenList();
		mav.addObject("deptChild",listChild);
		mav.addObject("dcDemo",dc);
		mav.addObject("dcParent", depter);
		mav.setViewName(IContants.PREFIX + "jsp/System_Department/deptListLevel.jsp");
		return mav;
	}
	
	//存储部门信息，新建页并且查询所有一级部门
	@RequestMapping("/saveDeptUI")
	public ModelAndView saveDeptUI(){
		ModelAndView mav = new ModelAndView();
		List<DepartmentCustom> treeNode = this.ids.searchAllFirstDeptInformation();
		String selectList = DepartmentUtils.selectFirstDept(treeNode);
		mav.addObject("selectList",selectList);
		mav.setViewName(IContants.PREFIX + "jsp/System_Department/deptSaveUI.jsp");
	    return mav;
	}
	
	//存储部门信息，完成返回部门列单页
	
	@RequestMapping("/saveDept")
	public ModelAndView saveDept(DepartmentCustom dc){
		ModelAndView mav = new ModelAndView();
        String message = "";
        try{
        	this.ids.saveNewDeptInformation(dc);
        	message = "【提示信息】新建成功";
        }catch(Exception e){
        	 e.printStackTrace();
        	 message = "【提示信息】新建失败";
        	 throw new CustomException(message);
        }
        mav.addObject("message", message);
		mav.setViewName("searchDepts.action");
	    return mav;
	}
	
	//修改部门信息，新建页并且查询所有一级部门（不包含要修改的信息及其子部门）
	@RequestMapping("/updateDeptUI")
	public ModelAndView updateDeptUI(DepartmentCustom dc){
		ModelAndView mav = new ModelAndView();
		List<DepartmentCustom> treeNode = this.ids.searchAllFirstDeptInformation();
		DepartmentCustom dept = this.ids.selectbasicDeptInfoForUpdate(dc.getId());
		System.out.println(dc.getId() + "部门id 与 父节点id" + dept.getParentDeptId());
		String selectList = DepartmentUtils.selectFirstDeptForUpdate(treeNode,dc.getId(),dept.getParentDeptId());
		mav.addObject("selectUpdateList",selectList);
		mav.addObject("deptInfo", dept);
		mav.setViewName(IContants.PREFIX + "jsp/System_Department/deptUpdateUI.jsp");
	    return mav;
	}
	
	
	//修改部门信息，完成返回部门列单页
	@RequestMapping("/updateDeptInfo")
	public ModelAndView updateDeptInfo(DepartmentCustom dc){
		ModelAndView mav = new ModelAndView();
		String message = "";
		try{
        	this.ids.updateDeptInformation(dc);
        	 message = "【提示信息】修改成功";
        }catch(Exception e){
        	 e.printStackTrace();
        	 message = "【提示信息】修改失败";
        	 throw new CustomException(message);
        }
        mav.addObject("message", message);
		mav.setViewName("searchDepts.action");
		return mav;
	}
	
	//删除部门信息
	@RequestMapping("/deleteDeptInfo")
	public ModelAndView deleteDeptInfo(int id){
		ModelAndView mav = new ModelAndView();
		String message = "";
		try{
			this.ids.deleteBasicDept(id);
			message = "【提示信息】删除成功";
		}catch(Exception e){
			message = "【提示信息】删除失败";
			throw new CustomException(message);
		}
	    mav.setViewName("searchDepts.action");
		return mav;
	}
}
