<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html>
 <head>
		<meta charset="UTF-8">
		<title>查看预警</title>
		<meta name="viewport" content="width=device-width,initial-scale=0.8,minimum-scale=0.8,maximum-scale=0.8,user-scalable=no" />
		<link href="/weixin1/css/mui.min.css" rel="stylesheet" />
	</head>

	<body>
		<script src="/weixin1/js/mui.min.js"></script>
		<script type="text/javascript">
			mui.init();
		</script>
		<div style="margin-top: 40px;">
			<form action="alertAction_findAlert.action?pe.pageNum=1" method="post">
				<table width="85%" align="center">
					<tr align="center">
						<td><a href="/weixin1/wx/fdy_menu.jsp" target="_parent"><button type="button" class="mui-btn ">返回</button></a></td>
						<td width="150px"  align="center"><input  type="date" style="background-color: #87CEEB;" name="onday" value="${onday }" ></td>
						<td><button type="submit" class="mui-btn ">查询</button></td>
					</tr>
				</table>
			</form>
			<c:set var="currentList" value="${alist.subList(pe.beginIndex,pe.endIndex)}"/>
			
			<table width="100%" border="1" cellspacing="0" align="center">
				<tr height="30px" align="center">
					<td>姓名</td>
					<td>专业年级</td>					
					<td>预警类型</td>
					<td>通过时间</td>
					<td>操作</td>
				</tr>
				<c:if test="${not empty alist }">
				<c:forEach items="${currentList }" var="a">
					<tr height="50px" align="center">
					<td>${a.piname }</td>
					
					<td>${a.deptName}</td>
					<c:if test="${a.alert_type=='4' }">
						<td> 未出预警</td>
					</c:if>
					<c:if test="${a.alert_type=='5' }">
						<td> 未归预警</td>
					</c:if>
					<c:if test="${a.alert_type=='6' }">
						<td> 假期在校</td>
					</c:if>
					<td>
					<fmt:parseDate value="${a.lasttgtime }" var="date" pattern="yyyy-MM-dd HH:mm:ss"/>  
					<fmt:formatDate  pattern="MM月dd日 HH:mm" value="${date}"/>
					</td>
					<td>
					
					<c:if test="${a.alert_state =='ACTIVE' }">
						<a href="alertAction_preupdatePer.action?perInfo.piid=${a.piid }&alertInfo.alert_info_id=${a.alert_info_id}">处理</a>
					</c:if>
					<c:if test="${a.alert_state =='INACTIVE' }">
						已处理
					</c:if>	
					</td>
				</tr>
				</c:forEach>
				
				 <tr>
					<td colspan="5" align="center">
						<a href="alertAction_findAlert.action?pe.pageNum=1&onday=${onday }"><button class="mui-btn mui-btn-primary">首页</button></a>&nbsp;
						<c:if test="${pe.pageNum ==1 }">
							<button class="mui-btn mui-btn-primary">上一页</button>
						</c:if>
						<c:if test="${pe.pageNum !=1 }">
							<a href="alertAction_findAlert.action?pe.pageNum=${pe.pageNum-1 }&onday=${onday }"><button class="mui-btn mui-btn-primary">上一页</button></a>
						</c:if>
						&nbsp;
						<c:if test="${pe.pageNum ==pe.maxPage }">
							<button class="mui-btn mui-btn-primary">下一页</button>
						</c:if>
						<c:if test="${pe.pageNum !=pe.maxPage }">
							<a href="alertAction_findAlert.action?pe.pageNum=${pe.pageNum+1 }&onday=${onday }"><button class="mui-btn mui-btn-primary">下一页</button></a>
						</c:if>
						&nbsp;
						<a href="alertAction_findAlert.action?pe.pageNum=${pe.maxPage }&onday=${onday }"><button class="mui-btn mui-btn-primary">末页</button></a>&nbsp;
						共${pe.totalNum }人
					</td>				
				</tr> 
				</c:if>
				<c:if test="${empty alist }">
		    	<tr height="50px">
		    		<td colspan="5" align="center">一条数据也没有</td>
		    	</tr>
		    	</c:if>
			</table>
		</div>
		
	</body>

</html>