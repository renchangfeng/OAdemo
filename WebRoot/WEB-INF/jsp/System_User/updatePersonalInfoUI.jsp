<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>个人信息管理</title>
    <script language="javascript" src="script/jquery.js"></script>
    <script language="javascript" src="script/pageCommon.js" charset="utf-8"></script>
    <script language="javascript" src="script/PageUtils.js" charset="utf-8"></script>
    <link type="text/css" rel="stylesheet" href="style/blue/pageCommon.css" />
  </head>
  
  <body>
	<!-- 标题显示 -->
	<div id="Title_bar">
	    <div id="Title_bar_Head">
	        <div id="Title_Head"></div>
	        <div id="Title"><!--页面标题-->
	            <img border="0" width="13" height="13" src="style/images/title_arrow.gif"/> 个人信息
	        </div>
	        <div id="Title_End"></div>
	    </div>
	</div>
	
	<!--显示表单内容-->
	<div id=MainArea>
	    <form action="updatePersonalInfo.action" method="post">
	        <input type="hidden" name="uid" value="${user.uid}"/>
	        <div class="ItemBlock_Title1"><!-- 信息说明<DIV CLASS="ItemBlock_Title1">
	        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="style/blue/images/item_point.gif" /> 个人信息 </DIV>  -->
	        </div>
	        
	        <!-- 表单内容显示 -->
	        <div class="ItemBlockBorder">
	            <div class="ItemBlock">
	                <table cellpadding="0" cellspacing="0" class="mainForm">
						<tr>
	                        <td width="150">用户ID（登录名）</td>
	                        <td>${user.loginname}</td>
	                    </tr>
	                    <tr>
	                        <td>部门</td>
	                        <td>${user.deptCustom.dname}</td>
	                    </tr>
						<tr>
	                        <td>角色</td>
	                        <td>
                               <c:forEach items="${user.roleCustom}" var="role">
                                   ${role.rname} &nbsp;&nbsp;
                               </c:forEach>
                            </td>
	                    </tr>
						<tr>
	                        <td>姓名</td>
	                        <td><input type="text" name="uname" value="${user.uname}"/></td>
	                    </tr>
						<tr>
	                        <td>性别</td>
	                        <td>
							   <c:if test="${user.sex=='男' }">
							      <input type="radio" name="sex" value="男" checked/>男
							   </c:if>
							   <c:if test="${user.sex!='男' }">
							      <input type="radio" name="sex" value="男"/>男
							   </c:if>
							   <c:if test="${user.sex=='女' }">
							      <input type="radio" name="sex" value="女" checked/>女
							   </c:if>
							   <c:if test="${user.sex!='女' }">
							      <input type="radio" name="sex" value="女"/>女
							   </c:if>
							   
							</td>
	                    </tr>
						<tr>
	                        <td>联系电话</td>
	                        <td><input type="text" name="phone" value="${user.phone}"/></td>
	                    </tr>
						<tr>
	                        <td>Email</td>
	                        <td><input type="text" name="email" value="${user.email}"/></td>
	                    </tr>
	                    <tr><td>简介</td>
	                        <td><textarea name="memo" class="TextareaStyle">${user.memo}</textarea></td>
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
		1.可以修改自已的姓名，其它信息不能修改。<br />
	</div>
	
	
  </body>
</html>