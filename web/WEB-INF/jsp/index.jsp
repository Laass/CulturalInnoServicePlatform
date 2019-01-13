<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user0
  Date: 2018/12/11
  Time: 15:51
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
    <style type="text/css">
        .indexPhoto
        {
            height: 100%;
            width: 100%;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="headder.jsp"/>
    <div class="row" style="margin-top: 5px;justify-content: center;">
        <div id="demo" class="carousel slide" data-ride="carousel" style="width: 800px;display: inline-block;margin-top: 45px;">

            <!-- 指示符 -->
            <ul class="carousel-indicators">
                <li data-target="#demo" data-slide-to="0" class="active"></li>
                <li data-target="#demo" data-slide-to="1"></li>
                <li data-target="#demo" data-slide-to="2"></li>
            </ul>

            <!-- 轮播图片 -->
            <div class="carousel-inner" style="width: 800px;height: 350px;">
                <div class="carousel-item active">
                    <img src="images/index/index3.jpg" class="indexPhoto">
                </div>
                <div class="carousel-item">
                    <img src="images/index/index2.jpg" class="indexPhoto">
                </div>
                <div class="carousel-item">
                    <img src="images/index/index1.jpg" class="indexPhoto">
                </div>
            </div>

            <!-- 左右切换按钮 -->
            <a class="carousel-control-prev" href="#demo" data-slide="prev">
                <span class="carousel-control-prev-icon"></span>
            </a>
            <a class="carousel-control-next" href="#demo" data-slide="next">
                <span class="carousel-control-next-icon"></span>
            </a>

        </div>
        <div class="newsList">
            <h3>资讯信息</h3>
            <c:forEach items="${latestNewsList}" var="news">
            <div class="news">
                <div lass="newTitle"><a href="getNewsById?newsId=${news.newsId}">${news.title}</a></div>
                <%--<div class="newsInfo">${news.content}</div>--%>
            </div>
            </c:forEach>
        </div>
    </div>

</div>
<div class="row" style="margin-top: 10px;justify-content: center;">
  <div class="MidNewsList">
    <div class="MidNewsListTitle"><a style="text-decoration: none; color: white;" href="SupplyAndDemand.html">供应</a></div>
    <ul class="MidNewsListContain">
        <c:forEach items="${latestSupplyList}" var="supply">
      <li><a href="getSDInfo?sdId=${supply.sdId}">${supply.title}</a></li>
        </c:forEach>
    </ul>
  </div>
  <div class="MidNewsList">
      <div class="MidNewsListTitle"><a style="text-decoration: none; color: white;" href="SupplyAndDemand.html">需求</a></div>
    <ul class="MidNewsListContain">
        <c:forEach items="${latestDemandList}" var="demand">
      <li><a href="getSDInfo?sdId=${demand.sdId}">${demand.title}</a></li>
        </c:forEach>
    </ul>
  </div>
  <div class="MidNewsList">
      <div class="MidNewsListTitle"><a style="text-decoration: none; color: white;" href="Exhibition.html">展会</a></div>
    <ul class="MidNewsListContain">
        <c:forEach items="${latestExhibitionList}" var="exhi">
      <li><a href="getExhibitionInfo?exhiId=${exhi.exId}">${exhi.theme}</a></li>
        </c:forEach>
    </ul>
  </div>
</div>
<div class="row" style="font-size: 28px;font-weight: bold;justify-content: center;width: 100%;">
    <a style="text-decoration: none; color: black;" href="Product.html">
  文化创意产品
    </a>
</div>
<div class="row" style="justify-content: center;">
  <div style="width: 1100px;">
      <c:forEach items="${pList}" var="p">
    <div class="indexProduct">
      <div class="productImg">
        <img src="${p.fourth}" alt="${product.proName}" style="height: 100%;">
      </div>
      <div class="productTitle"><a href="getProductById?productId=${p.second}">${p.first}</a></div>
        <p>￥${p.fifth}&nbsp;&nbsp;&nbsp;点击量：${p.sixth}</p>
      <%--<div class="productInfo">
        ${p.third}
      </div>--%>
    </div>
      </c:forEach>
  </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
