<%--
  Created by IntelliJ IDEA.
  User: liushuai2
  Date: 2018/6/17
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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

    <link href="css/mui.picker.css" rel="stylesheet" />
    <link href="css/mui.poppicker.css" rel="stylesheet" />
    <link href="css/mui.dtpicker.css" rel="stylesheet" />

</head>

<body>
<script type="text/javascript" src="js/jquery-1.4.2.js"></script>
<script type="text/javascript" src="js/mui.min.js"></script>
<script type="text/javascript" src="js/mui.picker.js"></script>
<script type="text/javascript" src="js/mui.poppicker.js"></script>

<script type="text/javascript" src="js/mui.dtpicker.js"></script>

<script type="text/javascript">
    mui.init();
</script>
<script type="text/javascript">

$(document).ready(function() {
    var options = {
        target_objid : "demo1"
    }


    var btns = $('#startTimeBtn, #endTimeBtn');
    btns.each(function(i, btn) {
        btn.addEventListener('tap', function () {
            var that = this;
            var dtPicker = new mui.DtPicker(options);
            dtPicker.show(function (selectItems) {
                var targetInput = that.getAttribute("target-input");
                console.log(targetInput);
                console.log(that.getAttribute("id") + "---------");
                console.log(selectItems.y);//{text: "2016",value: 2016}
                console.log(selectItems.m);//{text: "05",value: "05"}
            });
        });
    });

});

</script>
<div >



    <form class="mui-input-group">
        <input type="text" hiddenname="startTime" id="startTime" value="${startTime}"/>
        <input type="text" hiddenname="endTime" id="endTime" value="${endTime}"/>

        <div class="mui-input-row mui-select">
            <c:if test="${fn:length(depts) > 0}">
                <label>班级</label>

                <select class="mui-btn mui-btn-block" style="margin: auto;" name="deptId">

                    <c:choose>
                        <c:when test="${deptId == null}">
                            <option value="-1" selected>全部</option>
                        </c:when>
                        <c:when test="${deptId != null}">
                            <option value="-1" >全部</option>
                        </c:when>

                    </c:choose>


                    <c:forEach items="${depts}" var="dept">
                        <c:choose>
                            <c:when test="${dept.deptId == deptId}">
                                <option value="${dept.deptId}" selected>${dept.deptName}</option>
                            </c:when>
                            <c:when test="${dept.deptId != deptId}">
                                <option value="${dept.deptId}">${dept.deptName}</option>
                            </c:when>
                        </c:choose>

                    </c:forEach>

                </select>
            </c:if>

            <c:if test="${fn:length(rooms) >0 }">
                 <label>公寓</label>
                <select class="mui-h5" style="margin: auto;" name="roomId">
                    <c:choose>
                        <c:when test="${roomId == null}">
                            <option value="-1" selected>全部</option>
                        </c:when>
                        <c:when test="${roomId != null}">
                            <option value="-1" >全部</option>
                        </c:when>

                    </c:choose>
                    <c:forEach items="${rooms}" var="apartment">
                        <c:choose>
                            <c:when test="${apartment.roomId == roomId}">
                                <option value="${apartment.roomId}" selected>${apartment.fullRoomName}</option>
                            </c:when>
                            <c:when test="${apartment.roomId != roomId}">
                                <option value="${apartment.roomId}">${apartment.fullRoomName}</option>
                            </c:when>
                        </c:choose>

                    </c:forEach>
                </select>
            </c:if>

        </div>

        <div class="mui-input-row">
            <label>开始时间</label>
            <button id='startTimeBtn' target-input = "startTime" data-options='{"type":"datetime"}' class="btn mui-btn mui-btn-block">开始时间</button>

        </div>
        <div class="mui-input-row">
            <label>结束时间</label>
            <button id='endTimeBtn' target-input="endTime" data-options='{"type":"datetime"}' class="btn mui-btn mui-btn-block">结束时间</button>
        </div>
        <div class="mui-button-row">
            <button type="button" class="mui-btn mui-btn-primary" >确认</button>
        </div>
    </form>

    <ul class="mui-table-view">
        <li class="mui-table-view-cell">Item 1</li>
        <li class="mui-table-view-cell">Item 2</li>
        <li class="mui-table-view-cell">Item 3</li>
    </ul>

    <div class="mui-content">
        <div class="mui-row">
            <div class="mui-col-sm-6 mui-col-xs-12">
                <li class="mui-table-view-cell">
                    <a class="mui-navigate-right">
                        Item 1
                    </a>
                </li>
            </div>
            <div class="mui-col-sm-6 mui-col-xs-12">
                <li class="mui-table-view-cell">
                    <a class="mui-navigate-right">
                        Item 1
                    </a>
                </li>
            </div>
        </div>
    </div>
    <%--<button style="width: 150px; height: 50px; margin-left: 15px; margin-top: 15px;">退出</button>--%>


    <form action="kqMainAction_index.action" method="post">
        <table width="85%" align="center">
            <tr align="center">
                <c:if test="${fn:length(depts) > 0}">
                    <td width="150px"  align="center">
                        <li class="mui-table-view-cell">
                            <a class="mui-navigate-right" style="color: olive;">
                                <span class="mui-badge mui-active" style="background-color: #FFFFFF;">
                                    <select class="mui-h5" style="margin: auto;" name="deptId">

                                           <c:choose>
                                                    <c:when test="${deptId == null}">
                                                        <option value="-1" selected>全部</option>
                                                    </c:when>
                                                    <c:when test="${deptId != null}">
                                                       <option value="-1" >全部</option>
                                                    </c:when>

                                           </c:choose>


                                            <c:forEach items="${depts}" var="dept">
                                                <c:choose>
                                                    <c:when test="${dept.deptId == deptId}">
                                                        <option value="${dept.deptId}" selected>${dept.deptName}</option>
                                                    </c:when>
                                                    <c:when test="${dept.deptId != deptId}">
                                                        <option value="${dept.deptId}">${dept.deptName}</option>
                                                    </c:when>
                                                </c:choose>

                                            </c:forEach>

                                    </select>
                                </span>
                                班级（行政口）
                            </a>
                        </li>
                </td>
                </c:if>
                <c:if test="${fn:length(rooms) >0 }">
                    <td width="150px"  align="center">
                        <li class="mui-table-view-cell">
                            <a class="mui-navigate-right" style="color: olive;">
                                <span class="mui-badge mui-active" style="background-color: #FFFFFF;">

                                    <select class="mui-h5" style="margin: auto;" name="roomId">
                                             <c:choose>
                                                 <c:when test="${roomId == null}">
                                                     <option value="-1" selected>全部</option>
                                                 </c:when>
                                                 <c:when test="${roomId != null}">
                                                     <option value="-1" >全部</option>
                                                 </c:when>

                                             </c:choose>
                                         <c:forEach items="${rooms}" var="apartment">
                                             <c:choose>
                                                 <c:when test="${apartment.roomId == roomId}">
                                                     <option value="${apartment.roomId}" selected>${apartment.fullRoomName}</option>
                                                 </c:when>
                                                 <c:when test="${apartment.roomId != roomId}">
                                                     <option value="${apartment.roomId}">${apartment.fullRoomName}</option>
                                                 </c:when>
                                             </c:choose>

                                         </c:forEach>
                                    </select>
                                </span>
                                楼栋（后勤口）
                            </a>
                        </li>
                    </td>
                </c:if>

                <td><button type="submit" class="mui-btn" id="search_btn">查询</button></td>
            </tr>
        </table>
    </form>






    </form>


</div>

<div class="mui-content" style="background-color:#fff">
    <!--内容区-->
    <%--<div class="mui-card-content" align="center" >--%>
        <%--<a href="alertAction_findAlert.action?pe.pageNum=1"><img src="images/1.png"></a>--%>
        <%--<a href="abnAction_findLaterInfo.action?pe.pageNum=1"><img src="images/2.png"></a>--%>
    <%--</div>--%>


        <ul class="mui-table-view mui-grid-view">
            <li class="mui-table-view-cell mui-media mui-col-xs-6">
                <a href="#">
                    <img class="mui-media-object" src="images/1.png">
                    <%--<div class="mui-media-body">幸福就是可以一起睡觉</div>--%>
                </a>
            </li>
            <li class="mui-table-view-cell mui-media mui-col-xs-6">
                <a href="#">
                    <img class="mui-media-object" src="images/2.png">
                    <%--<div class="mui-media-body">想要一间这样的木屋，静静的喝咖啡</div>--%>
                </a></li>
            <li class="mui-table-view-cell mui-media mui-col-xs-6">
                <a href="#">
                    <img class="mui-media-object" src="images/2.png">
                    <%--<div class="mui-media-body">Color of SIP CBD</div>--%>
                </a>
            </li>
            <li class="mui-table-view-cell mui-media mui-col-xs-6">
                <a href="#">
                    <img class="mui-media-object" src="images/2.png">
                    <%--<div class="mui-media-body">静静看这世界</div>--%>
                </a></li>
        </ul>


    <div class="mui-card-content" align="center" >
        <a href="alertAction_findAlert.action?pe.pageNum=1" >总人数（${kqMsgDTO.total}）</a>
        <a href="abnAction_findLaterInfo.action?pe.pageNum=1">实习人数（${kqMsgDTO.sx}）</a>
    </div>
    <div class="mui-card-content" align="center" >
        <a href="alertAction_findAlert.action?pe.pageNum=1">请假人数（${kqMsgDTO.qj}）</a>
        <a href="abnAction_findLaterInfo.action?pe.pageNum=1">迟归人数（${kqMsgDTO.cg}）</a>
    </div>
    <div class="mui-card-content" align="center" >
        <a href="alertAction_findAlert.action?pe.pageNum=1">未归人数（${kqMsgDTO.wgbj}）</a>
        <a href="abnAction_findLaterInfo.action?pe.pageNum=1">未归报警（${kqMsgDTO.wcbj}）</a>
    </div>
    <div class="mui-card-content" align="center">
        <a href="abnAction_findCardAbn.action?pe.pageNum=1">在校预警（${kqMsgDTO.zxyj}）</a>
    </div>

</div>
</body>

</html>