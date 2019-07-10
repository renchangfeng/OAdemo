package com.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.pojo.PrivilegeCustom;
import com.service.IPrivilegeService;

public class CustomInitPrivilegeListener implements ServletContextListener {

	
	public void contextInitialized(ServletContextEvent sce) {
		
		//获取application对象
		ServletContext sc = sce.getServletContext();
		
		//获取ioc容器
		ApplicationContext ioc =  WebApplicationContextUtils.getWebApplicationContext(sc);
		
		//在ioc容器里，获取PrivilegeServiceImpl对象
		IPrivilegeService ips =  (IPrivilegeService)ioc.getBean("privilegeService");
		
		
		//调用业务逻辑层，获取根节点
		PrivilegeCustom pc = ips.searchNodePointInfo();
		
		//调用业务逻辑层，获取一级节点以及二级节点
		List<PrivilegeCustom> pcList = ips.searchAllNodeOnePointInfo();

		//调用业务逻辑层，获取公共权限
		List<PrivilegeCustom> pcPublic = ips.searchAllPublicPrivilegeInfo();
		
		//将获取数据存入application对象
		sc.setAttribute("privilegeData", pc);
		sc.setAttribute("onelevelData", pcList);
		sc.setAttribute("publicPrivilege", pcPublic);
//		
		System.out.println("========系统权限信息加载完毕=========");
	}

	
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
