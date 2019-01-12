<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user0
  Date: 2018/12/14
  Time: 0:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>文化创意中心</title>
    <!-- <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous"> -->

    <!-- <script src="http://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>  -->
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/base.css">
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <script src="js/orderPage.js"></script>
    <script src="js/productPage.js"></script>

</head>
<body>
<div class="container-fluid">
    <jsp:include page="headder.jsp"/>

    <!-- 当前栏目名字 -->
    <div class="row">
        <h3 style="margin-left: 90px;margin-top: 30px;">订单信息</h3>
    </div>

    <div class="row" style="justify-content: center;">
        <div id="orderSection" class="orderLists" style="width: 1200px;justify-content: center;margin-top: 30px;">
            <c:forEach items="${orderInfoList}" var="order">
                <!-- 一个订单的开始 -->
                <div class="card">
                    <div class="card-header">订单编号:${order.first}</div>
                    <div class="card-body">
                        <img src="${order.sixth}" alt="${order.second}" style="width: auto;height: 100px;float: left;">
                        <div class="orderTitle"><a href="getProductById?productId=${order.seventh}">${order.second}</a></div>
                        <p class="text-right" style="margin-right: 30px;position: relative;bottom: -20px;">购买数量：${order.third} &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp单个价格：
                            <span style="display: inline-block;color: #FF0000">${order.fourth}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;购买时间：${order.fifth}</p>
                    </div>
                    <div class="proId" style="display: none;">${order.seventh}</div>
                    <div class="card-footer">
                        <button type="button" class="comment btn btn-light btn-outline-secondary btn-lg float-right">评价</button>
                    </div>
                </div>
                <!-- 一个订单的结尾 -->
            </c:forEach>
        </div>
    </div>
    <!-- 分页 -->
    <div class="row" style="justify-content: center;margin-top: 30px;">
        <ul class="pagination">
            <h5 id="curPage">1</h5>
            <li class="page-item"><a class="page-link" href="#" id="pre">Previous</a></li>
            <li class="page-item"><a class="page-link" href="#" id="next">Next</a></li>
        </ul>
    </div>
    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>
