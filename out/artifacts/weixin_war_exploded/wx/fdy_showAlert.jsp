<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
  <head>
		<meta charset="UTF-8">
		<title>处理详情 </title>
		<meta name="viewport" content="width=device-width,initial-scale=0.8,minimum-scale=0.8,maximum-scale=1,user-scalable=no" />
		<link href="css/mui.min.css" rel="stylesheet" />
 	
	</head>

	<body>
		<script src="js/mui.min.js"></script>
		<script type="text/javascript">
			mui.init();
		</script>
		<div style="width: 80%;margin-left: 50px;margin-top: 10px; margin-right: 50px;">
	<button type="button" class="mui-btn mui-btn-primary" onclick="history.back()">返回</button>
		<br>
		<span style="color: red">${msg }</span>
		
	  		
	  		<div ><input type="hidden" name="alertInfo.alert_info_id" value="${alertInfo.alert_info_id }"  ></div>
	  		<div>&nbsp;姓&nbsp;&nbsp;&nbsp;&nbsp;名:<input readonly="readonly" type="text" name="" value="${perInfo.piName  }" ></div>
	    	<div>&nbsp;处理时间:<input readonly="readonly" type="text" name="" value="${alertInfo.check_date  }" ></div>
	    	<div>&nbsp;处理意见:<textarea readonly="readonly" rows="3" cols="20" name="alertInfo.check_reason">${alertInfo.check_reason}</textarea></div>
	    	
	    </div>
	</body>
</html>
