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


</head>

<body>
<script type="text/javascript" src="js/jquery-1.4.2.js"></script>
<script type="text/javascript" src="js/mui.min.js"></script>
<script type="text/javascript">
    mui.init();
</script>
<script type="text/javascript">
//    $(document).ready(function(){
        <%--$("#search_btn").click(function(){--%>
            <%--var classId = --%>



            <%--$.ajax({--%>
                <%--url:"kqMainAction_index.action",--%>
                <%--success:function(result){--%>
                    <%--window.location.href="${path}/wx/login.jsp";--%>
                <%--}});--%>
        <%--});--%>
    <%--});--%>

</script>
<div >
    <button style="width: 150px; height: 50px; margin-left: 15px; margin-top: 15px;">退出</button>
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
                <td width="150px"  align="center">
                    开始时间：<input type="text" name="startTime" id="startTime" value="${startTime}"/>
                </td>

                <td width="150px"  align="center">
                    结束时间：<input type="text" name="endTime" id="endTime" value="${endTime}"/>
                </td>

                <td><button type="submit" class="mui-btn" id="search_btn">查询</button></td>
            </tr>
        </table>
    </form>






    </form>


</div>
<div class="mui-card">
    <!--内容区-->
    <%--<div class="mui-card-content" align="center" >--%>
        <%--<a href="alertAction_findAlert.action?pe.pageNum=1"><img src="images/1.png"></a>--%>
        <%--<a href="abnAction_findLaterInfo.action?pe.pageNum=1"><img src="images/2.png"></a>--%>
    <%--</div>--%>

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