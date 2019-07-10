<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>分配角色</title>
    <script language="javascript" src="script/jquery.js"></script>
    <script language="javascript" src="script/pageCommon.js" charset="utf-8"></script>
    <script language="javascript" src="script/PageUtils.js" charset="utf-8"></script>

    <link type="text/css" rel="stylesheet" href="style/blue/pageCommon.css" />
    <script type="text/javascript">
	   function setRoleInfo(roleIds){
	         var rolesObj = $("#roleIdList");
			 var values = rolesObj.val();
			 //alert(values);	
			 $("#roleIds").val(values);
                
	   }
	
    </script>

  </head>
  
  <body>
	<!-- 标题显示 -->
	<div id="Title_bar">
	    <div id="Title_bar_Head">
	        <div id="Title_Head"></div>
	        <div id="Title"><!--页面标题-->
	            <img border="0" width="13" height="13" src="style/images/title_arrow.gif"/> 分配角色
	        </div>
	        <div id="Title_End"></div>
	    </div>
	</div>
	
	<!--显示表单内容-->
	<div id=MainArea>
	    <form action="saveUserForRoleInfo.action" method="post">
	      <input type="hidden" name="userId" value="${ucData.uid}">
	        <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
	        	<img border="0" width="4" height="7" src="style/blue/images/item_point.gif" /> 正在为【${ucData.uname}】分配角色</div> 
	        </div>
	        
	        
	        
			<div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
	        	角色设置 </div> 
	        </div>
	        
	        <!-- 表单内容显示 -->
	        <div class="ItemBlockBorder">
	            <div class="ItemBlock">
	                <table cellpadding="0" cellspacing="0" class="mainForm">
	                    <tr>
							<td width="100">角色</td>
	                        <td>${roleData}
	                                 <br>按住Ctrl键可以多选或取消选择
								<br>【所选角色（演示效果使用，应用时应改为隐藏域）：】<input type="text" id="roleIds"/>
	                        </td>
	                    </tr>
	                </table>
	            </div>
	        </div>		
			
	        <!-- 表单操作 -->
	        <div id="InputDetailBar">
	            <input type="image" src="style/images/save.png"/>
	            <a href="searchAllUserInfoList.action"><img src="style/images/goBack.png"/></a>
	        </div>
	    </form>
	</div>
	
	<div class="Description">
		说明：<br />
		1.可以为用户分配多个角色，从而使用户拥有多种系统权限。<br />
	
	</div>
  </body>
</html>
