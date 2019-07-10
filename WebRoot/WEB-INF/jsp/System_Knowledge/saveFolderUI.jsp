<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>创建目录</title>
    <!--第一步：导入相关的JS文件与CSS文件-->
	<script language="javascript" src="script/jquery.js"></script>
	<script src="script/jquery_treeview/jquery.treeview.js"></script>
	<!--
    <script language="javascript" src="script/pageCommon.js" charset="utf-8"></script>
	-->
    <script language="javascript" src="script/PageUtils.js" charset="utf-8"></script>
	
    <!--jquery树形控件所使用的CSS文件-->
	<link rel="stylesheet" type="text/css" href="script/jquery_treeview/jquery.treeview.css"/>
	<link type="text/css" rel="stylesheet" href="style/blue/pageCommon.css" />

     <script>
         
     //设置单选按钮的值
     function setRadioValue(nodeId,nodeName, level){
     	
			//如果val()方法有参数时，表示将传入的参数值赋值给相应的元素的value属性，即表示赋值
			$("#parentFolderId").val(nodeName);
			$("#parentId").val(nodeId);
			//设置目录层次
			$("#level").val(level+1);
     }
		 
		
		//<!--第三步：将上面使用ul展示的数据显示为树状结构-->
		$(function(){
		    //<!--使用treeview()将上面使用的ul展示的数据转换成树状结构，结点中的内容可以根据需要自行确定-->
    		$("#tree").treeview();
    	});
	 </script>

  </head>
  
  <BODY STYLE="background-color: #F7FFFF;">	
	
	<DIV ID="Title_bar">
	    <DIV ID="Title_bar_Head">
	        <DIV ID="Title_Head"></DIV>
	        <DIV ID="Title"><!--页面标题-->
	            <IMG BORDER="0" WIDTH="13" HEIGHT="13" SRC="style/images/title_arrow.gif"/> 系统目录管理
	        </DIV>
	        <DIV ID="Title_End"></DIV>
	    </DIV>
	</DIV>
	
	<!--当前路径-->
	<DIV ID="QueryArea">
	
	</DIV>
	
	<!--目录列表-->
	<DIV ID="dirListArea" STYLE="width: 260px; float: left;">
	
		<DIV STYLE="margin-left: 15px;">
			<!--显示文件夹树-->
	         <!--第二步：使用ul显示权限树中的权限数据，注意：如果是目录图片不能有单选按钮-->
			  <ul id="tree" class="filetree">
				  ${treeData}
		    </ul>
		</DIV>
	</DIV>
	
	<!--目录内容列表显示-->
	<DIV ID="MainArea" STYLE="margin-left: 30px; width: 800px; float: left;">
		<!-- 标题显示 -->
		<div id="Title_bar">
			<div id="Title_bar_Head">
				<div id="Title_Head"></div>
				<div id="Title"><!--页面标题-->
					<img border="0" width="13" height="13" src="style/images/title_arrow.gif"/> 目录属性
				</div>
				<div id="Title_End"></div>
			</div>
		</div>
	
		<!--显示表单内容-->
		<div id=MainArea>
			<form action="saveFolder.action" method="post">
				<div class="ItemBlock_Title1"><!-- 信息说明<DIV CLASS="ItemBlock_Title1">
					<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="../style/blue/images/item_point.gif" /> 文件夹属性</DIV>  -->
				</div>
				
				<!-- 表单内容显示 -->
				<div class="ItemBlockBorder">
					<div class="ItemBlock">
						<table cellpadding="0" cellspacing="0" class="mainForm">
							<tr>
								<td width="100">文件夹名称</td>
								<td><input type="text" name="folderName" class="InputStyle" /> *</td>
							</tr>
							<tr>
								<td>文件夹描述</td>
								<td><textarea name="folderMemo" class="TextareaStyle"></textarea></td>
							</tr>
							<tr>
								<td>上级文件夹</td>
								<td>
								   <input type="TEXT" name="parentFolderId" id="parentFolderId" readonly value="" class="InputStyle" /><br><br>
								   <input type="text" name="parentId" id="parentId"/>【开发时改为隐藏域】
								   </td>
							</tr>
							<tr>
								<td>是否为最终目录</td>
								<td>
								    <input type="radio" name="isLeaf" value="1"/>是 &nbsp;&nbsp;<input type="radio" name="isLeaf" value="0"/>否
								<td>
							</tr>
							<tr>
								<td>目录层次</td>
								<td>
								    <input type="text" id="level" name="level" value="" readonly="true"/>
								<td>
							</tr>
							
						</table>
					</div>
				</div>
				
				<!-- 表单操作 -->
				<div id="InputDetailBar">
					<input type="image" src="style/images/save.png"/>
					<a href="searchKnowledge.action"><img src="style/images/goBack.png"/></a>
				</div>
			</form>
		</div>
	
		
		<div class="Description">
			说明：<br />
			1.文件夹的名称，注意不要与本文件夹中已有的文件夹重名。
		</div>
	</DIV>
	<script>
	   var mess = "${message}";
	   if(mess != ""){
		   alert(mess);	   
	   }
	
	</script>
  </BODY>
</html>
