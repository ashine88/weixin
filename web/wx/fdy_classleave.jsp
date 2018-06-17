<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html>
  <head>
		<meta charset="UTF-8">
		<title>班级休假</title>
		<meta name="viewport" content="width=device-width,initial-scale=0.8,minimum-scale=0.8,maximum-scale=1,user-scalable=no" />
		<link href="/weixin1/css/mui.min.css" rel="stylesheet" />
 	
	</head>

	<body>
		<script src="/weixin1/js/mui.min.js"></script>
		<script type="text/javascript">
			mui.init();
		</script>
		<script type="text/javascript">
			
		</script>
		
		<span style="color: red">${msg }</span>
		<form action="leaveAction_addClassLeave.action" method="post">
	  		<div><input type="hidden" name="dept.deptId" value="${dept.deptId }" ></div>
	  		<div>&nbsp;班&nbsp;&nbsp;&nbsp;&nbsp;级:<input type="text" readonly="readonly" name="dept.deptName" value="${dept.deptName }" ></div>
	    	<div>&nbsp;开始时间:<input type="date" name="classLeave.begin_day" value="${classLeave.begin_day }"></div>
	    	<div>&nbsp;结束时间:<input type="date" name="classLeave.end_day" value="${classLeave.end_day}"></div>
	    	<div>&nbsp;请假原因:
	    	<select name="classLeave.holiday_type">
	    		<option value="1">暑假</option>
	    		<option value="2">寒假</option> 
	    		<option value="3">实习</option>    		
	    		<option value="4">其他</option>
	    	</select>
	    	</div>
	    	<br>
	    	<div class="mui-button-row">
		        <button style="width: 20%;height: 50px" type="submit" class="mui-btn mui-btn-primary" >确认</button>
		       &nbsp;&nbsp;&nbsp; <button style="width: 20% ;height: 50px" type="reset" class="mui-btn mui-btn-danger" >取消</button>
		    </div>
	    </form>
	    </div>
	</body>

</html>