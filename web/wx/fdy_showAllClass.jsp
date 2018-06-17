<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html>
  <head>
		<meta charset="UTF-8">
		<title>班级请假 </title>
		<meta name="viewport" content="width=device-width,initial-scale=0.8,minimum-scale=0.8,maximum-scale=1,user-scalable=no" />
		<link href="/weixin1/css/mui.min.css" rel="stylesheet" />
 	
	</head>

	<body>
		<script src="/weixin1/js/mui.min.js"></script>
		<script type="text/javascript">
			mui.init();
		</script>
		<div style="width: 80%;margin-left: 50px;margin-top: 10px; margin-right: 50px;">
	<button type="button" class="mui-btn mui-btn-primary" onclick="history.back()">返回</button>
		<br>
		<br>
		<table width="100%" align="center" border="1">		
			<tr>
				<th>班级</th>
				<th>操作</th>
			</tr>
			<c:if test="${not empty udlist }">
			<c:forEach items="${udlist }" var="u"> 
			
			<tr>
			<c:set var="l1" value="${fn:length(u.dept_id.fullName) }"></c:set>
			<c:set var="newname" value="${fn:replace(u.dept_id.fullName,'_','') }"></c:set>
			<c:set var="l2" value="${fn:length(newname) }"></c:set>
			<c:set var="num" value="${l1-l2}"></c:set>
			<c:set var="len" value="${fn:length(u.dept_id.fullName) }"></c:set>
			<c:if test="${num ==3 }">
				<td>${fn:substring( u.dept_id.fullName ,u.dept_id.fullName.indexOf("_",DeptName.indexOf("_")+1) + 1,len) }</td>
				<td><a href="leaveAction_preAddClassLeave.action?dept.deptId=${u.dept_id.deptId }">请假</a></td>
			</c:if>
				
			</tr>
		
			</c:forEach>
			</c:if>
			<c:if test="${empty udlist }">
			<tr height="50px">
		    		<td colspan="2" align="center">您没有负责的班级，不能进行该操作</td>
		    	</tr>
			</c:if>	
		</table>
		
	  	</div>
	  		
	</body>
</html>
