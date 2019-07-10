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
    <title>用户列表</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script language="javascript" src="script/jquery.js"></script>
    <script language="javascript" src="script/pageCommon.js" charset="utf-8"></script>
    <script language="javascript" src="script/PageUtils.js" charset="utf-8"></script>

    <link type="text/css" rel="stylesheet" href="style/blue/pageCommon.css" />
    <script type="text/javascript">
			    function initPassword(uid){
			    	
					//确认会返回true,取消会返回false
			    	var flag = window.confirm("【提示信息】您确定要初始化密码为1234吗？");
			    	
			    	if(flag){
			    		var serverUrl = "saveInitPassword.action";
				    	$.ajax({
							type: "post", 
							url: convertURL(serverUrl), 
				
							data: "initPassword=1234&userId=" + uid,	
							
							dataType: "json",//指定服务器端返回的数据格式为JSON
							success: function(backData){//回调函数，参数用于接收报务器端返回的JSON数据
								if(backData == 0){
									alert("【提示】：密码初始化成功！");
						        }else{
						        	alert("【提示】：密码初始化失败！");
						        }
						    }
					     });
			    	}
			
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
    </script>
</head>
<body>

<div id="Title_bar">
    <div id="Title_bar_Head"> 
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="style/images/title_arrow.gif"/> 用户管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
                <td width="100">登录名</td>
                <td width="100">姓名</td>
                <td width="100">所属部门</td>
                <td width="200">岗位</td>
                <td>备注</td>
                <td>相关操作</td>
            </tr>
        </thead>
        
        <!--显示数据列表-->
        <tbody id="TableData" class="dataContainer">
          <c:forEach items="${ucData}" var="uc">
            <tr class="TableDetail1 template">
                <td>${uc.loginname}&nbsp;</td>
                <td>${uc.uname}&nbsp;</td>
                <td>${uc.deptCustom.dname}&nbsp;</td>
                <td>
                    <c:forEach items="${uc.roleCustom}" var="item">
                       ${item.rname}&nbsp;
                    </c:forEach>
                </td>
                <td>${uc.memo}&nbsp;</td>
                <td>
                    
                    <oa:a action="updateUserInfoForUI.action?userId=${uc.uid}">修改</oa:a>
                    <oa:a action="javascript:initPassword(${uc.uid});">初始化密码</oa:a>
                    <oa:a action="assignRolesUI.action?userId=${uc.uid}">分配角色</oa:a>
                 
					<c:if test="${uc.isAdmin != 0}">
				     <oa:a action="deleteUserInfo.action?userId=${uc.uid}" onclick="return window.confirm('【提示信息】您确定要删除当前用户信息吗？')" >删除</oa:a>
					
					</c:if>
                </td>
            </tr>
           </c:forEach> 
        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
            <oa:a action="saveUserInfoForUI.action"><img src="style/images/createNew.png" /></oa:a>
        </div>
    </div>
</div>
     <script>
        var message = "${message}";
        if(message != ""){
        	alert(message);
        }
     </script>
</body>
</html>
