<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>权限提示信息页</title>
    
    <SCRIPT LANGUAGE="javascript" SRC="script/jquery.js"></SCRIPT>
    <SCRIPT LANGUAGE="javascript" SRC="script/pageCommon.js" CHARSET="utf-8"></SCRIPT>
    <SCRIPT LANGUAGE="javascript" SRC="script/PageUtils.js" CHARSET="utf-8"></SCRIPT>
    <LINK TYPE="text/css" REL="stylesheet" HREF="style/blue/pageCommon.css" />

  </head>
  
  <body>
	<DIV ID="Title_bar">
	    <DIV ID="Title_bar_Head">
	        <DIV ID="Title_Head"></DIV>
	        <DIV ID="Title"><!--页面标题-->
	            <IMG BORDER="0" WIDTH="13" HEIGHT="13" SRC="style/images/title_arrow.gif"/> 提示
	        </DIV>
	        <DIV ID="Title_End"></DIV>
	    </DIV>
	</DIV>


	<!--显示表单内容-->
	<DIV ID="MainArea">
			<DIV CLASS="ItemBlock_Title1">
	        </DIV> 
	
	        <DIV CLASS="ItemBlockBorder" STYLE="margin-left: 15px;">
	            <DIV CLASS="ItemBlock" STYLE="text-align: center; font-size: 16px;">
	                                        ${message};
	            </DIV>
	        </DIV>
	        
	        <!-- 操作 -->
	        <DIV ID="InputDetailBar">
	            <A HREF="to/jsp/index" target="_top"><IMG SRC="style/images/goBack.png"/></A>
	        </DIV>
	    
	</DIV>
  </body>
</html>