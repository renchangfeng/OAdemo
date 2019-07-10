<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>删除文件</title>
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
         
        //获取选中复选框的值
        function searchFiles(nid){
			
			//采用ajax请求服务器端
            var serverUrl = "searchFiles.action";
	
			$.ajax({
				type: "post", 
				url: convertURL(serverUrl), 
				data: "folderId=" + nid,							
				dataType: "json",//指定服务器端返回的数据格式为JSON
				success: function(backData){//回调函数，参数用于接收报务器端返回的JSON数据
					
				     //当前节点下的文件信息
					 var fileList = backData;
					
					//获取表格（table）
					var table_obj = $("#TableData");
					//每次请求都将原表格中的内容置为空
					table_obj.html("");
					$.each(fileList, function(posi,fileInfo){
						//将每次迭代出来的文件信息放入一个表格行				
						$("<tr id=\"tr_" + fileInfo.id + "\" class=\"TableDetail1 template\"><td style=\"height:30px;padding-left: 10px;\"><img src=\"style/images/FileType/" + fileInfo.filePic + "\"/>&nbsp;<a href=\"fileDownload.action?realName=" + fileInfo.realName + "&orginName=" + fileInfo.encodeName + "&filePath=" + fileInfo.filePath + "\" title=\"" +  fileInfo.fileMemo + "\">" + fileInfo.originName + "</a>&nbsp;" + "<td style=\"height:30px;\" align=\"center\">" + fileInfo.fileSize + "</td><td style=\"height:30px;\" align=\"center\">" + fileInfo.fileCreatetime + "</td><td style=\"height:30px;\" align=\"center\"><a href=\"javascript:delFile(" + fileInfo.id + ",'tr_" + fileInfo.id + "');\">删除</a></td></tr>").appendTo(table_obj);
			
					});
				}
			 });
		
        }
		function convertURL(url) {
			//获取时间戳
			var timstamp = (new Date()).valueOf();
			//将时间戳信息拼接到url上
			if (url.indexOf("?") >= 0) {
				url = url + "&t=" + timstamp;
			} else {
				url = url + "?t=" + timstamp;
			}
			return url;
		}
		
		//删除文件
		function delFile(fid, trId){

			var flag = window.confirm("【提示信息】您确认要删除选中的文件吗?");
			
			if(flag){
				//采用Ajax请求删除服务器端删除操作
	            var serverUrl = "delFile.action";
				$.ajax({
					type: "post", 
					url: convertURL(serverUrl), 
					data: "fileId=" + fid,							
					dataType: "json",//指定服务器端返回的数据格式为JSON
					success: function(backData){//回调函数，参数用于接收报务器端返回的JSON数据
						
						if(backData > 0){
						   var delTr = $("#" + trId);
						   delTr.remove();
						   alert("【提示信息】 文件删除成功！");
						   
						}else{
						   alert("【提示信息】 文件删除失败！");
						}
					}
				 });
			}
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
	            <IMG BORDER="0" WIDTH="13" HEIGHT="13" SRC="style/images/title_arrow.gif"/> 知识管理
	        </DIV>
	        <DIV ID="Title_End"></DIV>
	    </DIV>
	</DIV>
	
	<!--当前路径-->
	<DIV ID="QueryArea">
		<DIV ID="FilePath">
			<DIV CLASS="PathTitle">
				<A HREF="#" onClick="">当前路径：</A>
				<!--<A HREF="#"><IMG BORDER="0" ALT="返回上级文件夹" SRC="../style/images/folder_up.gif" /></A>-->
			</DIV>
			<!--当前显示的上级文件夹-->
			<DIV CLASS="PathShow">/oa使用说明书/</DIV>
			<!--
			<DIV CLASS="PathEnd">
				<A HREF="#"><IMG BORDER="0" ALT="返回上级文件夹" SRC="../style/images/folder_up.gif" /></A>
				<A HREF="#" onClick="">向上</A>
			</DIV>
			-->
		</DIV>
	</DIV>
	
	<!--目录列表-->
	<DIV ID="dirListArea" STYLE="width: 260px; float: left;">
	
		<DIV STYLE="margin-left: 15px;">
			<!--显示文件夹树-->
			 <ul id="tree" class="filetree">	
			 
				  ${treeData}
					
			 </ul>
		</DIV>
	</DIV>
	
	<!--目录内容列表显示-->
	<DIV ID="MainArea" STYLE="margin-left: 30px; width: 800px; float: left;">
		<TABLE CELLSPACING="0" CELLPADDING="0" CLASS="TableStyle">
			<!-- 表头-->
			<THEAD>
				<TR ALIGN="CENTER" VALIGN="MIDDLE" ID="TableTitle">
					<TD WIDTH="300px">名称</TD>
					<TD WIDTH="100px">大小</TD>
					<TD WIDTH="200px">创建时间</TD>
					<TD>相关操作</TD>
				</TR>
			</THEAD>
	
	
			
			<!--显示文件列表-->
			<tbody id="TableData" class="dataContainer" dataKey="fileList">
			
				<tr id="tr_1" class="TableDetail1 template">
					<td style="height:30px;padding-left: 15px;"><!--<img src="../style/images/FileType/${file.fileType.icon}"/>-->&nbsp;
						&nbsp;
					</td>
					<td style="height:30px;" align="center">&nbsp;</td>
					<td style="height:30px;" align="center">&nbsp;</td>
					<td style="height:30px;"  align="center">
					    &nbsp;
					</td>
				</tr>
				
				
			</tbody>
			
		</TABLE>
	
		<!-- 其他功能超链接 -->
		<DIV ID="TableTail">
			<DIV ID="TableTail_inside">
				<TABLE BORDER="0" CELLSPACING="0" CELLPADDING="10" ALIGN="right">
	                <TR>
						<TD>
						    <DIV CLASS="FuncBtn">
	                            <DIV class=FuncBtnHead></DIV>
	                            <DIV class=FuncBtnMemo><A HREF="searchKnowledge.action">返回</A></DIV>
	                            <DIV class=FuncBtnTail></DIV>
	                        </DIV>
						</TD>
					
	                </TR>
				</TABLE>
			</DIV>
		</DIV>
		
		<DIV CLASS="Description">
			说明1：左侧的文件夹列表<BR />
			1.显示为树形结构。<!-- 显示根目录 --><BR />
			2.单击某文件夹（节点时），则右侧文件列表则显示当前文夹下的所有文件。<BR />
			<BR />
	
			说明2：文件列表<BR />
			1.文件列表默认按创建时间升序排列。<!-- 默认按名称的升序排列 --><BR />
			2.不同的文件类型，显示不同的图标。<BR />&nbsp;&nbsp;
			3.单击"删除"按钮，可以删除该文件。<BR />
		</DIV>
	</DIV>

  </BODY>
</html>
