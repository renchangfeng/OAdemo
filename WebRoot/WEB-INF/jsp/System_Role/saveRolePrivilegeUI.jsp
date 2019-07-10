<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'saveRolePrivilegeUI.jsp' starting page</title>
    
<!--第一步：导入相关的JS文件与CSS文件-->
	<script language="javascript" src="script/jquery.js"></script>
	<script src="script/jquery_treeview/jquery.treeview.js"></script>

    <!--jquery树形控件所使用的CSS文件-->
	<link rel="stylesheet" type="text/css" href="script/jquery_treeview/jquery.treeview.css"/>
	<link type="text/css" rel="stylesheet" href="style/blue/pageCommon.css" />

	<script type="text/javascript">
 		  // 选择所有
    	function selectAll(checkedValue){
		    
    		$("input[type=checkbox][name=resourceIdList]").attr("checked", checkedValue);
			
			//获取选中复选框
			var cbs_choose = $("input[type=checkbox][name=resourceIdList]:checked");
			//将选中的复选框的值存入一个隐藏域中
			var cbs_choose_values = "";
			//循环获取选中复选框的值，如果val()方法为空时，表示获取指定元素的值
			cbs_choose.each(function(){			
			   cbs_choose_values = cbs_choose_values + $(this).val() + ",";
			});
			//alert(cbs_choose_values);
			//如果val()方法有参数时，表示将传入的参数值赋值给相应的元素的value属性，即表示赋值
			$("#privilegeValues").val(cbs_choose_values);
    	}
    	
    	function doChecked(liID, checkedValue){

			// 当前点击的checkbox元素所在的li元素
    		var jLi = $("#" + liID);
            
    		// 选中所有的直属下级。（children()方法是筛选下一级，find()是筛选所有后代）
    		jLi.children("ul").find("input[type=checkbox]").attr("checked", checkedValue);
    		
    		// 选中或取消选中直属上级
    		if( checkedValue ){ // checkedValue是boolean型，表示是否选中了当前复选框
			
    			/*
				     如果当前是选中，则选中所有的直属上级。说明：children()只是选择直接子元素，间接子元素不能查找
					 find()可以选择所有后代,会把所有后代全部找到
				*/ 
				//jLi.parents("li").children("input[type=checkbox]").attr("checked", checkedValue);
				//jLi.parents("li").find("input[type=checkbox]").first().attr("checked", checkedValue);
				jLi.parents("li").children("span").children("input[type=checkbox]").attr("checked", checkedValue);
				//alert(jLi.parents("li").size());
	
			}else{
				// 如果当前是取消选中，并且同级中没有被选中的项，则也取消上级的选中状态。siblings用于选择同级元素
				//var jCheckedSibingCB = jLi.siblings("li").children("input[type=checkbox]:checked");
				var jCheckedSibingCB = jLi.siblings("li").find("input[type=checkbox]:checked");
				//alert(jCheckedSibingCB.size());
				if(jCheckedSibingCB.size() == 0){
					var jCheckboxInput = jLi.parent("ul").prev("span").children("input[type=checkbox]");
					jCheckboxInput.attr("checked", checkedValue);
					
					// 递归操作每一层直属上级
					var jParentLi = jCheckboxInput.parents("li");
					//alert(jParentLi.size());
					if(jParentLi.size() > 0){
						doChecked(jParentLi.attr("id"), checkedValue);
					}
				}
			}
			
			/****以下代码为处理“全选按钮” ****/
			//获取所有复选框对象
			var cbsAll = $("input[type=checkbox][name=resourceIdList]");
			//获取复选框数量
			var cbnums = cbsAll.size();
			//alert(cbnums);
			
			//获取选中复选框
			var cbs_choose = $("input[type=checkbox][name=resourceIdList]:checked");
			//选中的复选框数量
			var chooseNum = cbs_choose.size();
			//alert(chooseNum);
			
			if(chooseNum == cbnums){
			   $("#cbSelectAll").attr("checked", true);
			}else{
			   $("#cbSelectAll").attr("checked", false);
			}
			
			//将选中的复选框的值存入一个隐藏域中
			var cbs_choose_values = "";
			//循环获取选中复选框的值，如果val()方法为空时，表示获取指定元素的值
			cbs_choose.each(function(){			
			   cbs_choose_values = cbs_choose_values + $(this).val() + ",";
			});
			//alert(cbs_choose_values);
			//如果val()方法有参数时，表示将传入的参数值赋值给相应的元素的value属性，即表示赋值
			$("#privilegeValues").val(cbs_choose_values);

    	} 

        //获取选中复选框的值
        function getCheckedValues(){
        	//获取选中复选框
			var cbs_choose = $("input[type=checkbox][name=resourceIdList]:checked");
        	//将选中的复选框的值存入一个隐藏域中
			var cbs_choose_values = "";
			//循环获取选中复选框的值，如果val()方法为空时，表示获取指定元素的值
			cbs_choose.each(function(){			
			   cbs_choose_values = cbs_choose_values + $(this).val() + ",";
			});
			//alert(cbs_choose_values);
			//如果val()方法有参数时，表示将传入的参数值赋值给相应的元素的value属性，即表示赋值
			$("#privilegeValues").val(cbs_choose_values);
        }
		
		//<!--第三步：将上面使用ul展示的数据显示为树状结构-->
		$(function(){
		    //<!--使用treeview()将上面使用的ul展示的数据转换成树状结构，结点中的内容可以根据需要自行确定-->
    		$("#tree").treeview();
    	});
    </script>

  </head>
  
  <body>
			<!-- 标题显示 -->
			<div id="Title_bar">
			    <div id="Title_bar_Head">
			        <div id="Title_Head"></div>
			        <div id="Title"><!--页面标题-->
			            <img border="0" width="13" height="13" src="style/images/title_arrow.gif"/> 配置权限
			        </div>
			        <div id="Title_End"></div>
			    </div>
			</div>
			
			<!--显示表单内容-->
			<div id=MainArea>
			    <form action="saveRolePrivilege.action" method="post">
			        <!-- roleId -->
			        <input type="hidden" name="roleId" value="${roleData.rid}">
			        <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
			        	<img border="0" width="4" height="7" src="style/blue/images/item_point.gif" /> 正在为【${roleData.rname}】配置权限 </div> 
			        </div>
			        
			        <!-- 表单内容显示 -->
			        <div class="ItemBlockBorder">
			            <div class="ItemBlock">
			                <table cellpadding="0" cellspacing="0" class="mainForm">
								<!--表头-->
								<thead>
									<tr align="LEFT" valign="MIDDLE" id="TableTitle">
										<td width="300px" style="padding-left: 7px;">
											<!-- 如果把全选元素的id指定为selectAll，如果这时存在一个函数名称也为selectAll()，就会有错，所以将全选钮名称设置为"cbSelectAll"。因为有一种用法：可以直接用id引用元素 -->
										    <input type="checkbox" id="cbSelectAll" onClick="selectAll(this.checked)"/>
										    <label for="cbSelectAll">全选</label>
										</td>
									</tr>
								</thead>
			                   
						   		<!--显示数据列表-->
								<tbody id="TableData">
									<tr class="TableDetail1">
										<!-- 第二步：显示权限树，使用ul显示权限树中的权限数据-->
										<td>
											<ul id='tree' class="filetree">
                                                    ${treeData}
											   </ul>
										</td>
									</tr>
								</tbody>
			                </table>
			            </div>
			        </div>
			     <input type="hidden" name="privilegeIds" id="privilegeValues"/><!-- 【说明】：实际开发时转换为隐藏域hidden<br><br>-->
			    <!--
			     <input type="button" value="获取选中值" onclick="getCheckedValues();">    
				 -->
			        <!-- 表单操作 -->
			        <div id="InputDetailBar">
			            <input type="image" src="style/images/save.png" onclick="getCheckedValues();"/>
						
			            <a href="searchRoleList.action"><img src="style/images/goBack.png"/></a>
			        </div>
			    </form>
			</div>
			
			<div class="Description">
				说明：<br />
				1.选中一个权限时：<br />
				&nbsp;&nbsp;&nbsp;&nbsp; a.应该选中 他的所有直系上级。<br />
				&nbsp;&nbsp;&nbsp;&nbsp; b.应该选中他的所有直系下级。<br />
				2.取消选择一个权限时：<br />
				&nbsp;&nbsp;&nbsp;&nbsp; a.应该取消选择 他的所有直系下级。<br />
				&nbsp;&nbsp;&nbsp;&nbsp; b.如果同级的权限都是未选择状态，就应该取消选中他的直接上级，并递归向上做这个操作。<br />
			
				3.全选/取消全选。<br />
				4.默认选中当前岗位已有的权限。<br />
			</div>
			
			<script>
			var message = "${message}";
			if(message != ""){
				alert(message)
			}
			
			</script>
  </body>
</html>
