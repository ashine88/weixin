<%--
  Created by IntelliJ IDEA.
  User: liushuai2
  Date: 2018/6/17
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    System.out.println(basePath);
%>

<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>辅导员功能菜单</title>
    <meta name="viewport" content="width=device-width,initial-scale=0.6,minimum-scale=0.6,maximum-scale=1,user-scalable=no" />
    <link href="css/mui.min.css" rel="stylesheet" />

1111


</head>

<body>
<script type="text/javascript" src="js/jquery-1.4.2.js"></script>
<script type="text/javascript">
    mui.init();
</script>
<script type="text/javascript">
    $(document).ready(function(){
        $("button").click(function(){
            alert("您即将退出该系统 ！！！");
            $.ajax({

                url:"userAction_logout.action",
                success:function(result){
                    window.location.href="${path}/wx/login.jsp";
                }});
        });
    });

</script>
<div >
    <button style="width: 150px; height: 50px; margin-left: 15px; margin-top: 15px;">退出</button>
</div>
<div class="mui-card">
    <!--内容区-->
    <div class="mui-card-content" align="center" >
        <a href="alertAction_findAlert.action?pe.pageNum=1"><img src="images/1.png"></a>
        <a href="abnAction_findLaterInfo.action?pe.pageNum=1"><img src="images/2.png"></a>
    </div>
    <div class="mui-card-content" align="center">
        <a href="abnAction_findCardAbn.action?pe.pageNum=1"><img src="images/3.jpg"></a>
        <a href="wx/fdy_personleave.jsp"><img src="images/4.png"></a>
    </div>
    <div class="mui-card-content" align="center">
        <a href="leaveAction_selectAllClass.action"><img src="images/5.png"></a>
        <a href="rankAction_rankByAss.action?pe.pageNum=1"><img src="images/6.png"></a>
    </div>
</div>
</body>

</html>