<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html>
  <head>
		<meta charset="UTF-8">
		<title>个人请假</title>
		<meta name="viewport" content="width=device-width,initial-scale=0.8,minimum-scale=0.8,maximum-scale=1,user-scalable=no" />
		<link href="../css/mui.min.css" rel="stylesheet" />
 	<script type="text/javascript" src="/weixin1/js/jquery-1.4.2.js"></script>
	<script type="text/javascript">
		function countDay(){
			var begin_date = $("#begin").val();
			var end_date = $("#end").val();
			$.post("leaveAction_countDay.action","personLeave.begin_date="+begin_date+"&personLeave.end_date="+end_date,
					   function(result){							
							document.getElementById("days").value=result;				   	
					  });			
		}
	</script>
	
	</head>

	<body>
		<script src="../js/mui.min.js"></script>
		<script type="text/javascript">
			mui.init();
		</script>
		
		<br>
		<span style="color: red">${msg }</span>
		<form action="leaveAction_addPersonLeave.action" method="post">
	  		
	  		<div>&nbsp;姓&nbsp;&nbsp;&nbsp;&nbsp;名:<input type="text" name="perInfo.piName" value="${perInfo.piName }" ></div>
	    	<div>&nbsp;开始时间:<input type="date" name="personLeave.begin_date" value="${personLeave.begin_date }" id="begin"></div>
	    	<div>&nbsp;结束时间:<input type="date" name="personLeave.end_date" value="${personLeave.end_date}" id="end" onblur="countDay()"></div>
	    	<div>&nbsp;请假时长:<input type="number" name="personLeave.leaveDays" min="1" value="${personLeave.leaveDays }" readonly="readonly" id="days"></div>
	    	<div>&nbsp;请假原因:
	    	<select name="personLeave.leave_type">
	    		<option value="1">病假</option>
	    		<option value="2">事假</option> 
	    		<option value="3">实习</option>    		
	    		<option value="4">其他</option>
	    	</select>
	    	</div>
	    	<div>&nbsp;具体原因:<textarea rows="3" cols="20" name="personLeave.reason">${personLeave.reason}</textarea></div>
	    	<div class="mui-button-row">
		        <button type="submit" class="mui-btn mui-btn-primary" style="width: 20%;height: 50px" >确认</button>&nbsp;&nbsp;&nbsp;
		        <button type="reset" class="mui-btn mui-btn-danger"style="width: 20%;height: 50px"  >取消</button>
		    </div>
	    </form>
	    </div>
	</body>

</html>