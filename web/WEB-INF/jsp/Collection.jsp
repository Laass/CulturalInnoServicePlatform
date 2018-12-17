<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user0
  Date: 2018/12/17
  Time: 16:14
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
        <h3 style="margin-left: 90px;margin-top: 30px;">展会信息</h3>
    </div>

    <div class="" style="justify-content: center;">
        <div style="justify-content:left;margin-top: 30px;margin-left:90px;">
            <ul class="essayList" style="list-style-type: disc;">
                <c:forEach items="${exhiInfoList}" var="exhiInfo">
                    <li><a href="/getExhibitionInfo?exhiId=${exhiInfo.fourth}"><span
                            class="title">${exhiInfo.first}</span><span
                            class="newsTime">${exhiInfo.second}</span><span
                            class="newsTime">${exhiInfo.third}</span></a></li>
                </c:forEach>
            </ul>
        </div>
    </div>

    <hr>

    <div class="row">
        <h3 style="margin-left: 90px;margin-top: 30px;">消息</h3>
    </div>

    <div class="" style="justify-content: center;">
        <div style="justify-content: center;margin-top: 30px;margin-left: 90px;">
            <ul class="essayList" style="list-style-type:disc;">
                <c:forEach items="${newsInfoList}" var="newsInfo">
                    <li><a href="/getNewsById?newsId=${newsInfo.fourth}"><span
                            class="title">${newsInfo.first}</span><span
                            class="newsTime">${newsInfo.second}</span><span
                            class="newsTime">${newsInfo.third}</span></a></li>
                </c:forEach>
            </ul>
        </div>
    </div>

    <hr>

    <div class="row">
        <h3 style="margin-left: 90px;margin-top: 30px;">产品</h3>
    </div>
    <div class="" style="justify-content: center;">
        <div style="justify-content: center;margin-top: 30px;margin-left: 90px;">
            <ul class="essayList" style="list-style-type:disc;">
                <c:forEach items="${productInfoList}" var="productInfo">
                    <li><a href="/getProductById?productId=${newsInfo.fourth}"><span
                            class="title">${productInfo.first}</span><span
                            class="newsTime">${productInfo.second}</span><span
                            class="newsTime">${productInfo.third}</span></a></li>
                </c:forEach>
            </ul>
        </div>
    </div>

    <hr>

    <div class="row">
        <h3 style="margin-left: 90px;margin-top: 30px;">供给信息</h3>
    </div>
    <div class="" style="justify-content: center;">
        <div style="justify-content: center;margin-top: 30px;margin-left: 90px;">
            <ul class="essayList" style="list-style-type:disc;">
                <c:forEach items="${supplyInfoList}" var="supplyInfo">
                    <li><a href="/getSDInfo?sdId=${supplyInfo.fifth}"><span
                            class="title">${supplyInfo.first}</span><span
                            class="newsTime">${supplyInfo.second}</span><span
                            class="newsTime">${supplyInfo.third}</span>
                        <span class="newsTime">${supplyInfo.fourth}</span></a></li>
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
                <c:forEach items="${demandInfoList}" var="demandInfo">
                    <li><a href="/getProductById?productId=${demandInfo.fifth}"><span
                            class="title">${demandInfo.first}</span><span
                            class="newsTime">${demandInfo.second}</span><span
                            class="newsTime">${demandInfo.third}</span><span
                            class="newsTime">${demandInfo.fourth}</span></a></li>
                </c:forEach>
            </ul>
        </div>
    </div>
    <jsp:include page="footer.jsp"/>
</body>
</html>
