package com.interceptor;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.pojo.PrivilegeCustom;
import com.pojo.UserCustom;
import com.utils.CommonUtils;
import com.utils.PrivilegeUtils;

public class PrivilegeInterceptor implements HandlerInterceptor {

	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp,Object handle) throws Exception {
   
		 //获取session
		  HttpSession session = req.getSession();
		 //获取application
          ServletContext sc = session.getServletContext();
		 //获取请求的url
		  String purl = req.getRequestURI(); 
		 //截取url与数据库数据匹配
		  String url = CommonUtils.cutUrl(purl);
		 //比对此url是否与公共权限url一样  需先将公共权限查询好 存入application
		  List<PrivilegeCustom> pc = (List<PrivilegeCustom>) sc.getAttribute("publicPrivilege");
		  boolean flag = PrivilegeUtils.judgePublicPrivilege(url, pc);
		  System.out.println("flag = " + flag);
		  String message = "";
		  /*
		    if(未登录){
		            跳转至登陆页面
		    }else{
		       if(是否有此权限){
		                 放行
		       }else{
		                 对不起，没有此权限
		       }
		    }
		 */
		  if(!flag){
			  UserCustom uc = (UserCustom)session.getAttribute("user");
			  if(uc == null){
				  message = "【提示信息】您尚未登录系统，请登录后使用相关功能！";
				  req.setAttribute("message", message);
				  req.getRequestDispatcher("gotologin.jsp").forward(req, resp);
			  }else{
				  if(uc.hasPrivilegeForUrl(url)){
					  flag = true;
				  }else{
				   message = "【提示信息】您没访功能的操作权限，系统已访录相关操作，请与系统管理员联系！";  
				   req.setAttribute("message", message);
				   req.getRequestDispatcher("privilegeInfo.jsp").forward(req, resp);
				  }
			  }
		  }
		
		return flag;
	}
	
	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse resp,Object handle, ModelAndView mav) throws Exception {



	}
	
	@Override
	public void afterCompletion(HttpServletRequest req,HttpServletResponse resp, Object handle, Exception e)throws Exception {



	}
	

}
