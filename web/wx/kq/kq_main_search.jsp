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
    <form action="">

        <li class="mui-table-view-cell">
            <a class="mui-navigate-right" style="color: olive;">
                <span class="mui-badge mui-active" style="background-color: #FFFFFF;">
                    <select class="mui-h5" style="margin: auto;" name="banji">
                        <option value="1">京</option>
                        <option value="1">津</option>
                        <option value="1">冀</option>
                        <option value="1">京</option>
                        <option value="1">津</option>
                        <option value="1">冀</option>
                        <option value="1">京</option>
                        <option value="1">津</option>
                        <option value="1">冀</option>
                    </select>
                </span>
                班级
            </a>
        </li>

        学院<input type="text" name="xueyuan" id="xueyuan"/>
        开始时间：<input type="text" name="startTime" id="startTime"/>
        结束时间：<input type="text" name="startTime" id="endTime"/>

        <button type="button" class="mui-btn mui-btn-primary">蓝色</button>

        <button type="button" class="mui-btn mui-btn-primary">汇总条件</button>
    </form>


</div>
<div class="mui-card">

    <!--内容区-->
    <%--<div class="mui-card-content" align="center" >--%>
        <%--<a href="alertAction_findAlert.action?pe.pageNum=1"><img src="images/1.png"></a>--%>
        <%--<a href="abnAction_findLaterInfo.action?pe.pageNum=1"><img src="images/2.png"></a>--%>
    <%--</div>--%>
    <div class="mui-card-content" align="center" >
        <a href="alertAction_findAlert.action?pe.pageNum=1" >总人数（100）</a>
        <a href="abnAction_findLaterInfo.action?pe.pageNum=1">实习人数（100）</a>
    </div>
    <div class="mui-card-content" align="center" >
        <a href="alertAction_findAlert.action?pe.pageNum=1">请假人数（10）</a>
        <a href="abnAction_findLaterInfo.action?pe.pageNum=1">迟归人数（10）</a>
    </div>
    <div class="mui-card-content" align="center" >
        <a href="alertAction_findAlert.action?pe.pageNum=1">未归人数（10）</a>
        <a href="abnAction_findLaterInfo.action?pe.pageNum=1">未归报警（10）</a>
    </div>
    <div class="mui-card-content" align="center">
        <a href="abnAction_findCardAbn.action?pe.pageNum=1"未出报警（10）</a>
        <a href="wx/fdy_personleave.jsp">在校预警</a>
    </div>

</div>
</body>

</html>