<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html>
  <head>
		<meta charset="UTF-8">
		<title>登录页面</title>
		<meta name="viewport" content="width=device-width,initial-scale=0.6,minimum-scale=0.6,maximum-scale=1,user-scalable=no" />
		<link href="/weixin1/css/mui.min.css" rel="stylesheet" />
	</head>

	<body style="background: url(/weixin1/images/2.jpg); background-position-x:10%;background-position-y:70%;">
		<script src="/weixin1/js/mui.min.js"></script>
		<script type="text/javascript">
			mui.init();
		</script>
		
		<div style="margin-top: 200px;" align="center">
		<form action="userAction_login.action" method="post">
			
			<table width="50%" align="center">
				<tr>
					<td align="right"><h3>用户名:</h3></td>
					<td><input type="text" name="user_code"></td>
				</tr>
				<tr>
					<td align="right"><h3>密&nbsp;&nbsp;&nbsp;&nbsp;码:</h3></td>
					<td><input type="password" name="user_password"></td>				
				</tr>
				<tr>
				<td></td>
					<td  align="left">
						<button type="submit" class="mui-badge-blue" style="width: 70%">登录</button><br>
						<br><button type="reset" class="mui-badge-danger" style="width: 70%">取消</button>
					</td>							
				</tr>
			</table>			
		</form>
		</div>
		
	</body>

</html>