<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html>
  <head>
		<meta charset="UTF-8">
		<title>预警处理</title>
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
		<span style="color: red">${msg }</span>
		<form action="alertAction_updatePer.action?perInfo.piid=${perInfo.piid }" method="post">
	  		
	  		<div ><input type="hidden" name="alertInfo.alert_info_id" value="${alertInfo.alert_info_id }"  ></div>
	  		<div>&nbsp;姓&nbsp;&nbsp;&nbsp;&nbsp;名:<input type="text" name="" value="${perInfo.piName  }" ></div>
	    	<div>&nbsp;预警处理:
	    	<select name="">
	    		<option value="病假">病假</option>
	    		<option value="事假">事假</option> 
	    		<option value="实习">实习</option>    		
	    		<option value="其他">其他</option>
	    	</select>
	    	</div>
	    	<div>&nbsp;具体原因:<textarea rows="3" cols="20" name="alertInfo.check_reason">${alertInfo.check_reason}</textarea></div>
	    	<div class="mui-button-row">
		        <button type="submit" class="mui-btn mui-btn-primary" >处理</button>
		        <button type="reset" class="mui-btn mui-btn-danger" >取消</button>
		    </div>
	    </form>
	    </div>
	</body>
</html>
