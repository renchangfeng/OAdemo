<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="oa" uri="http://cqf.com/privilegeTagLib" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
	    <title>角色列表</title>
	    <script language="javascript" src="script/jquery.js"></script>
	    <script language="javascript" src="script/pageCommon.js" charset="utf-8"></script>
	    <script language="javascript" src="script/PageUtils.js" charset="utf-8"></script>
	    <link type="text/css" rel="stylesheet" href="style/blue/pageCommon.css" />
	    <script>
	    
	    
	    </script>
</head>
<body>
 
		<div id="Title_bar">
		    <div id="Title_bar_Head">
		        <div id="Title_Head"></div>
		        <div id="Title"><!--页面标题-->
		            <img border="0" width="13" height="13" src="style/images/title_arrow.gif"/> 角色管理
		        </div>
		        <div id="Title_End"></div>
		    </div>
		</div>

		<div id="MainArea">
		    <table cellspacing="0" cellpadding="0" class="TableStyle">
		       
		        <!-- 表头-->
		        <thead>
		            <tr align="CENTER" valign="MIDDLE" id="TableTitle">
		            	<td width="200px">角色名称</td>
		                <td width="300px">角色说明</td>
		                <td>相关操作</td>
		            </tr>
		        </thead>
		
				<!--显示数据列表-->
		        <tbody id="TableData" class="dataContainer">
		         <c:forEach items="${roleData}" var="role">
					<tr class="TableDetail1 template">
						<td>${role.rname}&nbsp;</td>
						<td>${role.rmemo}&nbsp;</td>
						<td>
						    <oa:a action="updateRoleUI.action?rid=${role.rid}">修改</oa:a>
						    <oa:a action="setRolePrivilegeUI.action?roleId=${role.rid}">设置权限</oa:a>
						    <oa:a action="deleteRoleAllInfo.action?id=${role.rid}" onclick="return window.confirm('【提示信息】您确定要删除当前角色吗？')">删除</oa:a>
							
						</td>
					</tr>
				  </c:forEach>
		        </tbody>
		    </table>
		    
		    <!-- 其他功能超链接 -->
		    <div id="TableTail">
		        <div id="TableTail_inside">
		        	<oa:a action="saveRoleForUI.action"><img src="style/images/createNew.png" /></oa:a>
		        </div>
		    </div>
		</div>
		<script>
		var message = "${message}";
		if(message != ""){
			alert(message);
		}
		</script>
</body>
</html>
