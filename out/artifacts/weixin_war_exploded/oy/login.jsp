<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head >
    <meta charset="UTF-8">
    <title>登录页面</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <link rel="stylesheet" href="../css/reset.css"/>
    <link rel="stylesheet" href="../css/login.css"/>
    <script type="text/javascript" src="/weixin1/js/jquery.js"></script>
    <script type="text/javascript" src="/weixin1/js/js.cookie.js"></script>
    <script type="text/javascript" src="/weixin1/js/jquery.cookie.js"></script>
    
　　<script type="text/javascript">
		$(document).ready(function(){
		    //记住密码功能
		    var str = getCookie("loginInfo");
		    str = str.substring(56,str.length-19);
		    var username = str.split("#")[0];
		    var password = str.split("#")[1];
		    //自动填充用户名和密码
		    $("#user_code").val(username);
		    $("#user_password").val(password);
		});

		//获取cookie
		 function getCookie(cname) {
		var name = cname + "=";
		var ca = document.cookie.split(',');
		for(var i=0; i<ca.length; i++) {
		    var c = ca[i];
		    while (c.charAt(0)==' ') c = c.substring(1);
		    if (c.indexOf(name) != -1) return c.substring(name.length, c.length);
		}
		return "";
		} 

		//记住密码功能
		function remember(){
		var remFlag = $("input[type='checkbox']").is(':checked');
		if(remFlag==true){ //如果选中设置remFlag为1
		    //cookie存用户名和密码,回显的是真实的用户名和密码,存在安全问题.
		    var conFlag = confirm("记录密码功能不宜在公共场所(如网吧等)使用,以防密码泄露.您确定要使用此功能吗?");
		    if(conFlag){ //确认标志
		        $("#remFlag").val("1");
		    }else{
		        $("input[type='checkbox']").removeAttr('checked');
		        $("#remFlag").val("");
		    }
		}else{ //如果没选中设置remFlag为""
		    $("#remFlag").val("");
		}
		}
	</script>
</head>
<body>
<div class="enter">
    <div class="enter_wrap">
    	<div class="enter_wrap_title"> <h1>宿舍管理系统</h1></div>
    	<br>
        <form action="userAction_loginsg.action" method="post" name="enterForm">
            <input type="text" placeholder="用户名：" id="user_code" name="user_code" value="${username }"/>
            <input type="password" placeholder="密码：" id="user_password" name="user_password" value="${password }"/>            
            <label for="remember-me">
			    <input name="remFlag" id="remFlag" type="checkbox" onclick="remember()">
			                  记住密码 
			</label>
			<br><br>
            <input type="submit" value="确定"/>
            <input type="reset" value="取消"/>
        </form>
    </div>
</div>
</body>
</html>