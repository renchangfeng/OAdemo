<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'gotologin.jsp' starting page</title>


  </head>
  
  <body>
      <a id="gotologin" href="login.jsp" target="_top"></a>
   
   <script type="text/javascript">
       var obj = document.getElementById("gotologin");

	   var mess = "${message}";
	   if(mess != ""){
		   alert(mess);	   
	   }
       obj.click();
   </script>
  </body>
</html>
