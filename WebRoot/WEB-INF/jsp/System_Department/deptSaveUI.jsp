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
    
   			<title>My JSP 'deptSaveUi.jsp' starting page</title>
		    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		    <script language="javascript" src="script/jquery.js"></script>
		    <script language="javascript" src="script/pageCommon.js" charset="utf-8"></script>
		    <script language="javascript" src="script/PageUtils.js" charset="utf-8"></script>
		    <link type="text/css" rel="stylesheet" href="style/blue/pageCommon.css" />
		    
		   <script>
		     
             /*function saveTreeNode(obj){
            	  //alert(obj);
            	  var vs = obj.split(",");
            	  //alert(vs);
            	  $("#deptLevel").val(eval(vs[1])+1);
            	  obj = vs[0];
            	  
            	  alert(obj)
              } */
              </script>
  </head>
  <body>

		<!-- 标题显示 --> 
		<div id="Title_bar">
		    <div id="Title_bar_Head">
		        <div id="Title_Head"></div>
		        <div id="Title"><!--页面标题-->
		            <img border="0" width="13" height="13" src="style/images/title_arrow.gif"/> 添加部门信息
		        </div>
		        <div id="Title_End"></div>
		    </div>
		</div>

		<!--显示表单内容-->
		<div id=MainArea>
		    <form action="saveDept.action" method="post">
		        <div class="ItemBlock_Title1"><!-- 信息说明 --><DIV CLASS="ItemBlock_Title1">
		        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="style/blue/images/item_point.gif" /> 部门信息 </DIV> 
		        </div>
		        
		        <!-- 表单内容显示 -->
		        <div class="ItemBlockBorder">
		            <div class="ItemBlock">
		                <table cellpadding="0" cellspacing="0" class="mainForm">
		                    <tr><td width="100">上级部门</td>
		                        <td>
                                  ${selectList}
		                        </td>
		                    </tr>
		                    <tr><td>部门名称</td>
		                        <td><input type="text" id="deptName" name="dname" class="InputStyle"/> *</td>
		                    </tr>
		                    <tr><td>职能说明</td>
		                        <td><textarea name="memo" id="deptMemo" class="TextareaStyle"></textarea></td>
		                    </tr>
		                    <tr><td>部门层级</td>
		                        <td><input type="text" id="deptLevel" name="level" class="InputStyle"/></td>
		                    </tr>
		                </table>
		            </div>
		        </div>
		        
		        <!-- 表单操作 -->
		        <div id="InputDetailBar">
		            <input type="image" src="style/images/save.png"/>
		            <a href="searchDepts.action"><img src="style/images/goBack.png"/></a>
		        </div>
		    </form>
		</div>

		<div class="Description">
			说明：<br />
			1.上级部门的列表采用树形结构显示。<br/>
			2.部门层级表示当前部门所在层次，例如：如果是一级部门（没有上级部门）则设置为1，二级部门设置为2，以此类推。<br />
			3.如果是修改：上级部门列表中不能显示当前修改的部门及其子孙部门。因为不能选择自已或自已的子部门作为上级部门。<br />
		</div>

</body>
</html>
