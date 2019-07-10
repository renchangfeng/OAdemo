package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.utils.IContants;

@Controller
public class JSPPathController {
     
	 @RequestMapping("/to/{path}/{url}")
	 public ModelAndView forword(@PathVariable("path") String path,@PathVariable("url") String url){
		 ModelAndView mav = new ModelAndView();
		 
//		 String targetPath = IContants.PREFIX + path + "/" + url + IContants.SUFFIX;
		 StringBuffer sbb = new StringBuffer();
		 sbb.append(IContants.PREFIX);
		 sbb.append(path);
		 sbb.append("/");
		 sbb.append(url);
		 sbb.append(IContants.SUFFIX);
		 
		 
		 mav.setViewName(sbb.toString());
		 return mav;
	 }
}
