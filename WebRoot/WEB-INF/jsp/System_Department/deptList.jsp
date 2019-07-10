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
    
    <title>My JSP 'deptList.jsp' starting page</title>
    
	<script language="javascript" src="script/jquery.js"></script>
    <script language="javascript" src="script/pageCommon.js" charset="utf-8"></script>
    <script language="javascript" src="script/PageUtils.js" charset="utf-8"></script>
    <link type="text/css" rel="stylesheet" href="style/blue/pageCommon.css" />
    

  </head>
  
  <body>
		<div id="Title_bar">
		    <div id="Title_bar_Head">
		        <div id="Title_Head"></div>
		        <div id="Title"><!--页面标题-->
		            <img border="0" width="13" height="13" src="style/images/title_arrow.gif"/> 部门管理
		        </div>
		        <div id="Title_End"></div>
		    </div>
		</div>
		
		<div id="MainArea">
		    <table cellspacing="0" cellpadding="0" class="TableStyle">
		       
		        <!-- 表头-->
		        <thead>
		            <tr align=center valign=middle id=TableTitle>
		            	<td width="50px">部门名称</td>
						<td width="50px">上级部门名称</td>
						<td width="150px">职能说明</td>
						<td>相关操作</td>
		            </tr>
		        </thead>
		
				<!--显示数据列表-->
		        <tbody id="TableData" class="dataContainer">
					
				  <c:forEach items = "${deptData}" var="dept">
					<tr class="TableDetail1 template">
						<td><a href="searchChildDepts.action?id=${dept.id}">${dept.dname}</a>&nbsp;</td>
						<td>${dept.parent.dname}&nbsp;</td>
						<td>${dept.memo}&nbsp;</td>
						<td>
						    <oa:a action="updateDeptUI.action?id=${dept.id}">修改</oa:a>&nbsp;&nbsp;
						    <oa:a action="deleteDeptInfo?id=${dept.id}" onclick="return window.confirm('【提示信息】您确定要删除当前部门吗？')">删除</oa:a>
							
						</td>
					</tr>
				</c:forEach>
		        </tbody>
		    </table>
		    
		    <!-- 其他功能超链接 -->
		    <div id="TableTail">
		        <div id="TableTail_inside">
		            <oa:a action="saveDeptUI.action"><img src="style/images/createNew.png" /></oa:a>
		        </div>
		    </div>
		</div>
		
		<!--说明-->	
		<div id="Description"> 
		【说明】：<br />
			1.单击部门名称，可以查看此部门相应的下级部门列表。<br />
			2.删除部门时，如果该部门下有子部门，请先删除子部门后再删除当前部门。
		</div>
		<script>
		  var message = "${message}";
		  if(message != ""){
			  alert(message);
		  }
		  
		</script>
  </body>
</html>
