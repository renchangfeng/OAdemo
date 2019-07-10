<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>BankOA</title>
    

  </head>
  
  <frameset rows="100,*,25" framespacing="0" border="0" frameborder="0">
    <frame src="to/jsp/top" name="TopMenu"  scrolling="no" noresize />
    <frameset cols="180,*" id="resize">
        <frame noresize name="menu" src="to/jsp/left" scrolling="yes" />
        <frame noresize name="right" src="to/jsp/right" scrolling="yes" />
    </frameset> 
    <frame noresize name="status_bar" scrolling="no" src="to/jsp/bottom" />
   </frameset>
	<noframes>
	<body>
	</body>
</noframes>
</html>
