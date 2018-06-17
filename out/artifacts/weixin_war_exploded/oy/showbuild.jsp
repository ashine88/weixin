<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>公寓人数统计</title>
    <meta name="viewport" content="width=device-width,initial-scale=0.8,minimum-scale=0.8,maximum-scale=1,user-scalable=no" />
	<link href="css/mui.min.css" rel="stylesheet" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="../css/login.css" rel="stylesheet" />
  </head>
  
  <body>
  <div style=" height: 50px;margin-top: 20px; margin-left :20px; ">
  	<span style="font-size:24px; " >
  		${user.user_name }
  	</span>
  </div>
   <div style="margin-top: 10px">
   <c:forEach items="${rlist }" var="r">
		<ul style="width: 50px%; float: left; margin-left: 25px; text-align: center;">
			<li style="float: left; margin-right: 40px; margin-bottom: 50px;">				
					 <p style="font-size: 24px;">${r.roomName }</p>
					<a href="roomAction_selectAllfloor.action?roomId=${r.roomId }&buildName=${r.roomName}&buildnotback=${r.personIncount}"> <img alt="" src="images/${r.roomName}.jpg" width="200px" height="200px"> </a><br> 
					<c:if test="${r.personIncount ==0 }">
					 	<span style="color: green; font-size: 24px;text-align: center;">全寝</span> 
					 </c:if>
					 <c:if test="${r.personIncount !=0 }">
					 	不在寝：<span style="color: red; font-size: 24px">${r.personIncount }</span>人 
					 </c:if>
					 <br>
					总人数：${r.personAllcount }人
					&nbsp;缺寝率：<fmt:formatNumber type="number" value="${r.personIncount*100/r.personAllcount}" maxFractionDigits="1"/>%
					
					
								
			</li>			
		</ul>
   </c:forEach>
   </div>

  </body>
</html>
