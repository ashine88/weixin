<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>房间人数统计</title>
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
	
	<script type="text/javascript">
		function changeList() {
			 var count = document.getElementById("changename").value;
			 if(count=='查看缺寝房间'){
				 document.getElementById("div1").style.display ="";
				 document.getElementById("div2").style.display ="none";
				 document.getElementById("changename").value='显示全部';
			 }else{
				 document.getElementById("div1").style.display ="none";
				 document.getElementById("div2").style.display ="";
				 document.getElementById("changename").value='查看缺寝房间';
				 
			 }			
		}
	</script>
  </head>
  
  <body>  
  <span>
  	<input type="button" onclick="history.back()" value="返回上一级" style="width: 120px; height: 50px;margin-top: 20px; margin-left :20px;">
  </span>
  <div style=" height: 50px;margin-top: 20px; margin-left :20px; font-size: 20px">
  	<span style="font-size:24px; margin-right: 20px" >
  		${user.user_name }
  	</span>
  	${buildName} ${floorName } &nbsp;
  	不在寝：<a href="roomAction_findNotBackByfloorId.action?roomId=${roomId }&buildName=${buildName}&floorName=${floorName}&sysusername=${user.user_name}&floornotback=${floornotback}">${floornotback }</a> 人
  	<input type="button" style="width: 130px" onclick="changeList()" value="查看缺寝房间" id="changename">				
  				 
  </div>
  <hr>
  <!-- 只显示不在寝房间信息 -->
  <div id="div1" style="display: none;">
  		<c:forEach items="${rlist }" var="r">
   			<c:if test="${r.personIncount !=0 }">
   			<a href="roomAction_selectAllper.action?roomId=${r.roomId }&buildName=${buildName}&floorName=${floorName }&sysusername=${user.user_name}">
			<ul style="width: 50px%; float: left; border: 1px solid #000 ;margin-left: 25px;" id="1">
				<li style="float: left; margin-right: 50px; margin-bottom: 20px; text-align: center; margin-top: 5px;">				
					<p style="font-size: 24px;margin-top: 5px;">${r.roomName }</p>
					 <br>					 
					 	不在寝：<span style="color: red; font-size: 24px">${r.personIncount }</span>人 
					<br>
					总人数：	${r.personAllcount } 人
					<%-- 不在寝：<a href="oy/perdetail.jsp">${r.personIncount }</a>人 --%>				
				</li>			
			</ul>		
			</a>
	 		</c:if>
   		</c:forEach>	
  </div>
  <!-- 显示全部信息  -->
  <div  id="div2">
  	<c:forEach items="${rlist }" var="r">
    <a href="roomAction_selectAllper.action?roomId=${r.roomId }&buildName=${buildName}&floorName=${floorName }&roomName=${r.roomName }&sysusername=${user.user_name}">
		<ul style="width: 50px%; float: left; border: 1px solid #000 ;margin-left: 25px;" id="1">
			<li style="float: left; margin-right: 50px; margin-bottom: 20px; text-align: center; margin-top: 5px;">				
					<p style="font-size: 24px;margin-top: 5px;">${r.roomName }</p>
					 <br>
					 <c:if test="${r.personIncount ==0 }">
					 	<span style="color: green; font-size: 24px;text-align: center;">全寝</span> 
					 </c:if>
					 <c:if test="${r.personIncount !=0 }">
					 	不在寝：<span style="color: red; font-size: 24px">${r.personIncount }</span>人 
					 </c:if>
					<br>
					总人数：	${r.personAllcount } 人
			</li>			
		</ul>		
	</a>
  	</c:forEach> 
  </div>
   
   
  </body>
</html>
