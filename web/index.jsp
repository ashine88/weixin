<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>创建菜单</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
  <script type="text/javascript" src="/weixin1/js/jquery-1.4.2.js"></script>
		<script type="text/javascript">
			mui.init();
		</script>
		<script type="text/javascript">
		$(document).ready(function(){
			$("button").click(function(){
				
			     $.ajax({			    	
			    	url:"menuAction_buildMenu.action",
			    	success:function(result){
			    		if(result !=null){
			    			if(result == 0){
				    			alert("菜单创建成功！");
				    		}else{
				    			alert("菜单创建失败,错误码："+result);
				    		} 
			    			
			    		}else{
			    			alert("请输入正确的appid和appsecret");
			    		}
			    		
			    }}); 
			});
		});
		
		</script>
		 <h1>微信菜单</h1> 
		<div >
		<button style="width: 150px; height: 50px; margin-left: 15px; margin-top: 15px; font-size: 24px;">生成菜单</button>
		</div>
  
   
  </body>
</html>
