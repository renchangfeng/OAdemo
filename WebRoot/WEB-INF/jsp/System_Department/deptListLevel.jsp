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
    
    <title>My JSP 'deptListLevel.jsp' starting page</title>
    <SCRIPT LANGUAGE="javascript" SRC="script/jquery.js"></SCRIPT>
    <SCRIPT LANGUAGE="javascript" SRC="script/pageCommon.js" CHARSET="utf-8"></SCRIPT>
    <SCRIPT LANGUAGE="javascript" SRC="script/PageUtils.js" CHARSET="utf-8"></SCRIPT>
    <LINK TYPE="text/css" REL="stylesheet" HREF="style/blue/pageCommon.css" />

 </head>
<BODY>

		<DIV ID="Title_bar">
		    <DIV ID="Title_bar_Head">
		        <DIV ID="Title_Head"></DIV>
		        <DIV ID="Title"><!--页面标题-->
		            <IMG BORDER="0" WIDTH="13" HEIGHT="13" SRC="style/images/title_arrow.gif"/> ${dcDemo.dname}的下属部门信息 
		        </DIV>
		        <DIV ID="Title_End"></DIV>
		    </DIV>
		</DIV>

		<DIV ID="MainArea">
		    <TABLE CELLSPACING="0" CELLPADDING="0" CLASS="TableStyle">
		       
		        <!-- 表头-->
		        <THEAD>
		            <TR ALIGN=center VALIGN=middle ID=TableTitle>
		            	<TD WIDTH="50px">部门名称</TD>
						<TD>职能说明</TD>
		            </TR>
		        </THEAD>
		
				<!--显示数据列表-->
		        <TBODY ID="TableData" CLASS="dataContainer" >
                  <c:forEach items="${deptChild}" var="dept">
                            <TR CLASS="TableDetail1 template">
							<TD><a href="searchChildDepts.action?id=${dept.id}">${dept.dname}</a>&nbsp;</TD>
							<TD>${dept.memo}&nbsp;</TD>
					</TR>
				  </c:forEach>
		        </TBODY>
		    </TABLE>
		    
		    <!-- 其他功能超链接 -->
		    <DIV ID="TableTail">
		        <DIV ID="TableTail_inside">
		            <c:if test="${dcDemo.level>1}">
		               <A HREF="searchDepts.action"><IMG SRC="style/blue/images/button/goBack.png" /></A>
				       <A HREF="searchChildDepts.action?id=${dcDemo.parentDeptId}"><IMG SRC="style/blue/images/button/ReturnToPrevLevel.png" /></A> 
				      
				    </c:if>
					<c:if test="${dcDemo.level==1}">
					<A HREF="searchDepts.action"><IMG SRC="style/blue/images/button/goBack.png" /></A>
					</c:if>
		        </DIV>
		    </DIV>
		</DIV>
</BODY>
</HTML>
