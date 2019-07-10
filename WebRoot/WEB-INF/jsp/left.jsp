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

	<title>导航菜单</title>
	<script language="JavaScript" src="script/jquery.js"></script>
	<script language="JavaScript" src="script/menu.js"></script>
	<link type="text/css" rel="stylesheet" href="style/blue/menu.css" />
  </head>
 <body style="margin: 0">
		<div id="Menu">
		    <ul id="MenuUl">
		       
		       <c:forEach items="${onelevelData}" var="oneLevel">
			      <c:if test="${user.hasPrivilegeForId(oneLevel.id) }">  
					<c:if test="${!oneLevel.judgeMenuLevel(oneLevel.childrenList)}">
						<!--显示一级菜单，该一级菜单没有二级菜单，同时还有超链接-->
				        <li class="level1">
				            <div class="level1Style"><img src="style/images/MenuIcon/${oneLevel.pic}" class="Icon" /> <a target="right" href="${oneLevel.url}.action">${oneLevel.privilegeName}</a></div>
				        </li>
					</c:if>
				 
						<c:if test="${oneLevel.judgeMenuLevel(oneLevel.childrenList)}">
							<!--显示一级菜单，该一级菜单有二级菜单，所以没有超链接-->
					        <li class="level1">
					            <div onClick="menuClick(this);" class="level1Style"><img src="style/images/MenuIcon/${oneLevel.pic}" class="Icon" />${oneLevel.privilegeName}</div>
					            <!--显示二级菜单-->
								<ul style="display: none;" class="MenuLevel2">
								  <c:forEach items="${oneLevel.childrenList}" var="twolevel">
					                <c:if test="${user.hasPrivilegeForId(twolevel.id) }">
						                <li class="level2">
						                    <div class="level2Style"><img src="style/images/MenuIcon/${twolevel.pic}" /> <a target="right" href="${twolevel.url}.action"> ${twolevel.privilegeName}</a></div>
						                </li>
					            	 </c:if>
					              </c:forEach>
					            </ul>
					        </li>
						</c:if>
					
				</c:if>
				</c:forEach>
		    </ul>
		</div>
</body>
</html>
