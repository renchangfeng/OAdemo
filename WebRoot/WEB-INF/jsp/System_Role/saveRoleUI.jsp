<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	  <head>
	    <base href="<%=basePath%>">
	    
	   	<title>角色设置</title>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<script language="javascript" src="script/jquery.js"></script>
	    <script language="javascript" src="script/pageCommon.js" charset="utf-8"></script>
	    <script language="javascript" src="script/PageUtils.js" charset="utf-8"></script>
	    <link type="text/css" rel="stylesheet" href="style/blue/pageCommon.css" />
	    <script type="text/javascript">
	      
	       var formGet = function(){
	    	   var error = "";
	    	   var flag = false;
	    	   var RoleName = $("#rname");
	    	   var RoleMemo = $("#rmemo");
	    	   console.log(RoleName.val());
	    	   
	    	   if(RoleName.val() == null || RoleName.val() == ""){
	    		   error = error + "【提示信息】请输入角色名称  \r\n";
	    		  
	    	   }
	    	   if(RoleMemo.val() == null || RoleMemo.val() == ""){
	    		   error = error + "【提示信息】请输入角色说明  \r\n";
	    		  
	    	   }
	    	   
	    	   if(error == ""){
	    		   flag = true;
	    	   }else{
	    		   alert(error);
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
		            <img border="0" width="13" height="13" src="style/images/title_arrow.gif"/> 角色设置
		        </div>
		        <div id="Title_End"></div>
		    </div>
		</div>

		<!--显示表单内容-->
		<div id="MainArea">
		    <form action="saveRoleInfo.action" method="post" onsubmit="return formGet();">
		        <div class="ItemBlock_Title1"><!-- 信息说明<DIV CLASS="ItemBlock_Title1">
		        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="../style/blue/images/item_point.gif" /> 岗位信息 </DIV>  -->
		        </div>
		        
		        <!-- 表单内容显示 -->
		        <div class="ItemBlockBorder">
		            <div class="ItemBlock">
		                <table cellpadding="0" cellspacing="0" class="mainForm">
		                    <tr>
		                        <td width="100">角色名称</td>
		                        <td><input type="text" id="rname" name="rname" class="InputStyle" /> *</td>
		                    </tr>
		                    <tr>
		                        <td>角色说明</td>
		                        <td><textarea id="rmemo" name="rmemo" class="TextareaStyle"></textarea></td>
		                    </tr>
		                </table>
		            </div>
		        </div>
		        
		        <!-- 表单操作 -->
		        <div id="InputDetailBar">
		            <input type="image" src="style/images/save.png"/>
		            <a href="searchRoleList.action"><img src="style/images/goBack.png"/></a>
		        </div>
		    </form>
		</div>

</body>
</html>
