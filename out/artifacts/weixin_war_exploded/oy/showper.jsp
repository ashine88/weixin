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
    
    <title>房间人员明细</title>
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
  <span>
  	<input type="button" onclick="history.back()" value="返回上一级" style="width: 120px; height: 50px;margin-top: 20px; margin-left :20px;">
  </span>
  <div style=" height: 50px;margin-top: 20px; margin-left :20px; font-size: 20px">
  	<span style="font-size:24px; margin-right: 20px" >
  		${user.user_name }
  	</span>
  	  	${buildName}${floorName}
  </div>
  <hr>
  <div style="margin-bottom: 50px">
  
   <table width="80%"  border="1" align="center" style="margin-top: 50px;">
	   <tr height="40px" align="center">
	   		<td>姓名</td>    		
    		<td>房间</td>
    		<td>部门</td>
    		<td>最后进出宿舍时间</td>
    		<td>是否在寝</td>
	   </tr>
	   <c:forEach items="${plist }" var="p">
	   <c:if test="${p.lastRoomTGINOUT =='4_2' }">
	   <tr height="40px" bgcolor="#FFE4B5" align="center">		   
			<td>${p.piName}</td>			
			<td>${p.room.roomName}</td>
			<td>${p.dept.deptName}</td>
			<td>
				<fmt:parseDate value="${p.lastRoomTGDT }" var="date" pattern="yyyy-MM-dd HH:mm:ss"/>  
				<fmt:formatDate  pattern="MM月dd日 HH:mm" value="${date}"/>
			</td>
			<td><span style="color: red">不在寝</span></td>		  
	   </tr>
	   </c:if>
	   <c:if test="${p.lastRoomTGINOUT =='4_1' }">
	   <tr height="40px" align="center">		   
			<td>${p.piName}</td>			
			<td>${p.room.roomName}</td>
			<td>${p.dept.deptName}</td>
			<td>${p.lastRoomTGDT }</td>
			<td>在寝</td>		  
	   </tr>
	   </c:if>
	    </c:forEach> 
   </table> 
   
   </div>
  </body>
</html>
