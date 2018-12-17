<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user0
  Date: 2018/12/16
  Time: 22:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文化创意中心</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/base.css">
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="headder.jsp"/>

        <!-- 当前栏目名字 -->
        <div class="row">
            <h3 style="margin-left: 90px;margin-top: 30px;">供应信息</h3>
        </div>

        <div class="" style="justify-content: center;">
            <div style="justify-content:left;margin-top: 30px;margin-left:90px;">
                <ul class="essayList" style="list-style-type: disc;">
                    <c:forEach items="${supplyList}" var="supply">
                    <li><a href="/getSDInfo?sdId=${supply.sdId}"><span class="title">${supply.title}</span><span
                            class="newsTime">${supply.startTime}</span><span class="newsTime">${supply.endTime}</span></a></li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <hr>
        <div class="row">
            <h3 style="margin-left: 90px;margin-top: 30px;">需求信息</h3>
        </div>

        <div class="" style="justify-content: center;">
            <div style="justify-content: center;margin-top: 30px;margin-left: 90px;">
                <ul class="essayList" style="list-style-type:disc;">
                    <c:forEach items="${demandList}" var="demand">
                    <li><a href="/getSDInfo?sdId=${demand.sdId}"><span class="title">${demand.title}</span><span
                            class="newsTime">${demand.startTime}</span><span
                        class="newsTime">${demand.endTime}</span></a></li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    <jsp:include page="footer.jsp"/>
</body>
</html>
