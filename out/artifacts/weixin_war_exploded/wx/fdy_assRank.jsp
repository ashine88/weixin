<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>管理排名</title>
    <meta name="viewport" content="width=device-width,initial-scale=0.8,minimum-scale=0.8,maximum-scale=0.8,user-scalable=no" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="/weixin1/css/mui.min.css" rel="stylesheet" />
  </head>
  
  <body>
  <div style="margin-top: 50px;">
    <table width="100%" border="1" cellspacing="0" align="center">
    	<tr height="30px;">
    		<td>姓名</td>
    		<td>部门名称</td>
    		<td>处理率</td>
    		<td>排名</td>
    	</tr>
    	<c:forEach items="${rlist }" var="r" varStatus="s">
    	<tr height="30px;">
    		<td>${r.username }</td>
    		<td>${r.deptname }</td>
    		<td>${r.deal }%</td>
    		<td>${s.count }</td>
    	</tr>
    	</c:forEach>
    </table>
    </div>
  </body>
</html>
