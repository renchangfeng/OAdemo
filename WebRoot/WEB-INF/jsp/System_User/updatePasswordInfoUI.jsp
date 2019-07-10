<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改个人密码</title>
    <script language="javascript" src="script/jquery.js"></script>
    <script language="javascript" src="script/pageCommon.js" charset="utf-8"></script>
    <script language="javascript" src="script/PageUtils.js" charset="utf-8"></script>
    <link type="text/css" rel="stylesheet" href="style/blue/pageCommon.css" />
    <script type="text/javascript">
       function checkPassword(){
    	   var flag = false;
    	   var error = "";
    	   var originPass = "${user.pass}";
    	   var oldPass = $("#oldPassword").val();
    	   var newPass = $("#pass").val();
    	   var againPass = $("#againPass").val();
    	   
    	   if(originPass != oldPass){
    		   error = "【提示信息】 输入的原密码不正确，请确认后重试";
    	   }else if(newPass != againPass){
    		   error = "【提示信息】 两次输入的新密码不匹配，请确认后重试";
    	   }
    	   
    	   if(error != ""){
    		   alert(error);
    		   flag = false;
    	   }else{
    		   flag = true;
    	   }
    	   return flag;
       }
    
    
    </script>

  </head>
  
  <body>
	<!-- 标题显示 -->
	<div id="Title_bar">
	    <div id="Title_bar_Head">
	        <div id="Title_Head"></div>
	        <div id="Title"><!--页面标题-->
	            <img border="0" width="13" height="13" src="style/images/title_arrow.gif"/> 修改密码
	        </div>
	        <div id="Title_End"></div>
	    </div>
	</div>
	
	<!--显示表单内容-->
	<div id=MainArea>
	    <form action="updatePasswordInfo.action" method="post" onsubmit="return checkPassword();">
	        <input type="hidden" name="userId" value="${user.uid}"/>
	        <div class="ItemBlock_Title1"><!-- 信息说明<DIV CLASS="ItemBlock_Title1">
	        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="style/blue/images/item_point.gif" /> 修改密码 </DIV>  -->
	        </div>
	        
	        <!-- 表单内容显示 -->
	        <div class="ItemBlockBorder">
	            <div class="ItemBlock">
	                <table cellpadding="0" cellspacing="0" class="mainForm">
						<tr height="50">
							<td width="150">
								<img border="0" width="4" height="7" src="style/blue/images/item_point.gif" />
								请输入原密码
							</td>
							<td><input type="password" id="oldPassword" name="oldPassword" class="InputStyle" /> *</td>
						</tr>
						<tr height="25">
							<td width="150">
								<img border="0" width="4" height="7" src="style/blue/images/item_point.gif" />
								请填写新密码
							</td>
							<td><input type="password" id="pass" name="pass" class="InputStyle" /> *</td>
							<td></td>
						</tr>
						<tr height="25">
							<td width="150">
								<img border="0" width="4" height="7" src="style/blue/images/item_point.gif" />
								再次输入新密码
							</td>
							<td><input type="password" id="againPass" name="againPass" class="InputStyle" /></td>
							<td></td>
						</tr>
	                </table>
	            </div>
	        </div>
	       
	        <!-- 表单操作 -->
	        <div id="InputDetailBar">
	            <input type="image" src="style/images/save.png"/>
	            <a href="to/jsp/index" target="_top"><img src="style/images/goBack.png"/></a>
	        </div>
	    </form>
	</div>
	
	<div class="Description">
		验证规则：<br />
		1.旧密码不能为空。<br />
		2.新密码不能为空。<br />
		3.再次输入的密码要和新密码一致。<br />
	</div>
	
	<script>
	   var mess = "${message}";
	   if(mess != ""){
		   alert(mess);	   
	   }
	
	</script>
  </body>
</html>
