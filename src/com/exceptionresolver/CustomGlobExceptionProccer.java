package com.exceptionresolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.exception.CustomException;

public class CustomGlobExceptionProccer implements HandlerExceptionResolver{

	
	/**
	 * 参数说明：
	 * handler：处理器适配器要执行处理器对象（Handler）
	 * exception：我们手工抛出的异常对象
	 * */
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exption) {

	CustomException customException = null;
	if(exption instanceof CustomException){
		customException = (CustomException)exption;
	}else{
		customException = new CustomException("未知错误");
	}

	if(handler instanceof HandlerMethod){
		HandlerMethod hm = (HandlerMethod)handler;
		System.out.println(hm.getMethod().getName());
	}

	//错误信息
	String message = customException.getMessage();

	ModelAndView modelAndView = new ModelAndView();

	//将错误信息传到页面
	modelAndView.addObject("message", message);

	//指向错误页面
	modelAndView.setViewName("/jsp/error.jsp");

	return modelAndView;
	
	}
}
