package com.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.pojo.UserCustom;
import com.utils.CommonUtils;

public class CustomPrivilegeTag extends BodyTagSupport{

	String action;
	
	String onclick;
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getOnclick() {
		return onclick;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}

	@Override
	public int doStartTag() throws JspException {

		return super.doStartTag();
	}

	@Override
	public int doEndTag() throws JspException {

        
	    //获取application中的属性
		HttpServletRequest request = (HttpServletRequest) this.pageContext.getRequest();
		HttpSession sess = request.getSession();
		UserCustom user = (UserCustom)sess.getAttribute("user");
		//截取正确	url
		String url = CommonUtils.cutUrl(this.action);
		
		System.out.println("用户权限数量：" + user.getPrivilegeList().size());
		System.out.println("action:　" + this.action);
		System.out.println("url :　" + url);
		StringBuffer targetUrl = new StringBuffer();
		if(user.hasPrivilegeForUrl(url)){
//			System.out.println("是否进入标签体:　");
			//设置标签体。
			String bodyContent = this.getBodyContent().getString();
			targetUrl.append("<a href=\"");
			targetUrl.append(this.action);
			targetUrl.append("\" target=\"right\" ");
			if(this.onclick != null && !this.onclick.trim().equals("")){
				targetUrl.append("onclick=\"");
				targetUrl.append(this.onclick);
				targetUrl.append("\"");
			}
			targetUrl.append(">");
			targetUrl.append(bodyContent);
			targetUrl.append("</a>");	
		}else{
			targetUrl.append("&nbsp;");
		}
		
		//输出内容
		
		try {
			this.bodyContent.getEnclosingWriter().write(targetUrl.toString());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return super.doEndTag();
	}

}
