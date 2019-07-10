<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>上传文件</title>
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
         
        //获取选中单选按钮的值
        function setRadioValue(nodeId,nodeName){
        	
			//如果val()方法有参数时，表示将传入的参数值赋值给相应的元素的value属性，即表示赋值
			$("#folderName").val(nodeName);
			$("#folderId").val(nodeId);
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
	            <IMG BORDER="0" WIDTH="13" HEIGHT="13" SRC="style/images/title_arrow.gif"/> 上传文件
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
		<DIV ID="Title_bar">
			<DIV ID="Title_bar_Head">
				<DIV ID="Title_Head"></DIV>
				<DIV ID="Title"><!--页面标题-->
					<IMG BORDER="0" WIDTH="13" HEIGHT="13" SRC="style/images/title_arrow.gif"/> 文件属性
				</DIV>
				<DIV ID="Title_End"></DIV>
			</DIV>
		</DIV>
	
		<!--显示表单内容-->
		<DIV ID=MainArea>
			<FORM ACTION="saveFile.action" method="post" enctype="multipart/form-data">
				<DIV CLASS="ItemBlock_Title1"><!-- 信息说明<DIV CLASS="ItemBlock_Title1">
					<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="style/blue/images/item_point.gif" /> 文件属性</DIV>  -->
				</DIV>
				
				<!-- 表单内容显示 -->
				<DIV CLASS="ItemBlockBorder">
					<DIV CLASS="ItemBlock">
						<TABLE CELLPADDING="0" CELLSPACING="0" CLASS="mainForm">
							<TR>
								<TD WIDTH="100">文件信息</TD>
								<TD><INPUT TYPE="file" NAME="upload" CLASS="InputStyle" STYLE="width: 400px;"/></TD>
							</TR>
							<TR>
								<TD>文件描述</TD>
								<TD><TEXTAREA NAME="fileMemo" CLASS="TextareaStyle"></TEXTAREA></TD>
							</TR>
	
							<TR>
								<TD>所属文件夹</TD>
								<TD>
									   <input type="TEXT" name="folderName" id="folderName" readonly value="" class="InputStyle" /><br><br>
									   <input type="text" name="folderId" id="folderId"/>【开发时改为隐藏域】
								</TD>
							</TR>
						</TABLE>
					</DIV>
				</DIV>
				
				<!-- 表单操作 -->
				<DIV ID="InputDetailBar">
					<INPUT TYPE="image" SRC="style/images/save.png"/>
					<A HREF="searchKnowledge.action"><IMG SRC="style/images/goBack.png"/></A>
				</DIV>
			</FORM>
		</DIV>
	
		<DIV CLASS="Description">
			说明：<BR />
			1.选择的文件的名称，就是这个文件的显示名称，注意不要与本文件夹中已有的文件重名。<BR />
		</DIV>
	
	
	</DIV>

  </BODY>
</html>
