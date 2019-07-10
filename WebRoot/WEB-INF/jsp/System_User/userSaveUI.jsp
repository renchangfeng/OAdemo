<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
	<title>用户信息</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script language="javascript" src="script/jquery.js"></script>
    <script language="javascript" src="script/pageCommon.js" charset="utf-8"></script>
    <script language="javascript" src="script/PageUtils.js" charset="utf-8"></script>
    <script language="javascript" src="script/DemoData.js" charset="utf-8"></script>
	<script language="javascript" src="script/DataShowManager.js" charset="utf-8"></script>
    <link type="text/css" rel="stylesheet" href="style/blue/pageCommon.css" />
    <script type="text/javascript">
	        function checkLoginName(loginName){
	        	var checkLogin = "checkLoginname.action";
	        	
	        	$.ajax({
	        		type: "post",
	        		url: convertURL(checkLogin),
	        		data: "loginname=" + encodeURI(loginName) + "&userId=0",
	        		dataType: "json",
	        		success: function(backdata){
	        			var msg ="";
	        			if(backdata > 0){
	        				msg = "【提示】：登陆名称已存在";
	        			}else{
	        				msg = "&nbsp";
	        			}
	        			$("#mess").html("<font color='red'><b>" + msg + "</b></font>");
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
     
    </script>
</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="style/images/title_arrow.gif"/> 用户信息
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <form action="saveUserInfo.action" method="post">
        <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="style/blue/images/item_point.gif" /> 用户信息 </div> 
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr><td width="100">所属部门</td>
                        <td>${select}</td>
                    </tr>
                    <tr><td>登录名</td>
                        <td><input type="text" name="loginname" class="InputStyle" onblur="checkLoginName(this.value);"/> *
							（登录名要唯一）<span id="mess"></span>
						</td>
                    </tr>
                    <tr><td>姓名</td>
                        <td><input type="text" name="uname" class="InputStyle"/> *</td>
                    </tr>
					<tr><td>性别</td>
                        <td><input type="RADIO" name="sex" value="男" id="male"/><label for="male">男</label>
							<input type="RADIO" name="sex" value="女" id="female"/><label for="female">女</label>
						</td>
                    </tr>
					<tr><td>联系电话</td>
                        <td><input type="text" name="phone" class="InputStyle"/></td>
                    </tr>
                    <tr><td>E-mail</td>
                        <td><input type="text" name="email" class="InputStyle"/></td>
                    </tr>
                    <tr><td>是否为系统管理员</td>
							<td>
								<input type="radio" name="isAdmin" value="0" id="sysadmin"/><label for="sysadmin">系统管理员</label>
								<input type="radio" name="isAdmin" checked value="1" id="syscommon"/><label for="syscommon">普通用户</label>
							</td>
					</tr>
                    <tr><td>备注</td>
                        <td><textarea name="memo" class="TextareaStyle"></textarea></td>
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
	1.用户的登录名要唯一，在填写时要同时检测是否可用。<br />
	2.新建用户后，密码被初始化为"1234"。<br />
	3.用户登录系统后可以使用“个人设置→修改密码”功能修改密码。<br />
	4.修改用户信息时，登录名不可修改。
</div>

</body>
</html>
