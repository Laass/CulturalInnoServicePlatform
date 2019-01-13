<%--
  Created by IntelliJ IDEA.
  User: user0
  Date: 2018/12/14
  Time: 18:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>文化创意中心</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/base.css">

    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <script src="js/product.js"></script>
    <script src="js/productPage.js"></script>


    <style>
        * {
            margin: 0;
            padding: 0%;
        }

        html, body {
            width: 100%;
            height: 100%
        }
        .top {
            width: 100%;
        }

        .bottom {
            margin-left: 290px;
            width: 54%;
            height: 90%;
        }

        .product {
            width: 300px;
            height: 420px;
            margin-left: 40px;
            display: inline-block;
            overflow: hidden;
        }

        .proDetails {
            width: 100%;
            height: 500px;
            margin-top: 30px;
            /*	background-color: grey;*/
            display: block;
            overflow: hidden;
        }

        .proTitle {
            margin-top: 15px;
            width: 100%;
            font-size: 20px;
        }

        .proHitsandSellcount {
            margin-top: 30px;
            width: 100%;
            color: grey;
            font-size: 13px;
            height: 30px;
        }

        .proHits {
            float: left;
            margin-left: 10px;
        }

        .proSellcount {
            float: right;
            margin-right: 50px;
        }

        .proPrice {
            margin-top: 30px;
            margin-left: 10px;
            margin-bottom: 100px;
            width: 100%;
            color: red;
            font-size: 30px;
            height: 50px;
        }

        .proButton {
            margin-top: 28px;
            margin-left: 30px;
            width: 100%;
            height: 25px;
        }

        .proInfo {
            width: 100%;
            height: 25px;
        }


        .comments {
            width: 100%;
            height: 30px;
            border: 2px;
            display: inline;
            margin-top: 10px;
        }
        .commentinfo {
            border-bottom:1px solid #eee;
        }
    </style>


</head>

<body>
<div class="container-fluid">

    <jsp:include page="headder.jsp"/>

    <div class="top">
        <div class="row" style="margin-top: 5px; margin-bottom: 10px; justify-content: center;">
            <div id="demo" class="carousel slide" data-ride="carousel"
                 style="width: 380px; display: inline-block; margin-top: 45px;margin-right: 50px;">

                <!-- 轮播图片 -->
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img src="${imageList[0].storeLocation}" style="height: 100%;">
                    </div>
                    <c:forEach items="${imageList}" var="image" begin="1">
                        <div class="carousel-item">
                            <img src="${image.storeLocation}" style="height: 100%;">
                        </div>
                    </c:forEach>
                    <%--<div class="carousel-item">--%>
                    <%--<img--%>
                    <%--src="http://static.runoob.com/images/mix/img_mountains_wide.jpg">--%>
                    <%--</div>--%>
                </div>

                <!-- 左右切换按钮 -->
                <a class="carousel-control-prev" href="#demo" data-slide="prev">
                    <span class="carousel-control-prev-icon"></span>
                </a> <a class="carousel-control-next" href="#demo" data-slide="next">
                <span class="carousel-control-next-icon"></span>
            </a>

            </div>
            <div class="product">

                <div class="proDetails">
                    <div class="proId" style="display: none;">${product.proId}</div>
                    <div class="proTitle">${product.proName}</div>
                    <div class="proPrice">¥${product.price}</div>

                    <div class="proHitsandSellcount">
                        <div class="proHits">浏览量：${product.hits}</div>
                        <div class="proSellcount">月销量：${product.purchase}</div>
                    </div>

                    <div class="proButton" role="group" aria-label="Basic example">
                        <button type="button" class="btn btn-outline-danger" data-toggle="modal" data-target="#myModal">立即购买</button>
                        <button id="enshrine" type="button" class="btn btn-danger">收藏</button>
                        <button type="button" class="btn btn-outline-danger"><a href="communicate?proUserId=${product.userId}" style="color: black;">联系商家</a></button>
                    </div>



                </div>
            </div>
        </div>

    </div>



    <div class="bottom">
        <nav>
            <div class="nav nav-tabs" id="nav-tab" role="tablist">
                <a
                        class="nav-item nav-link active" id="nav-home-tab"
                        data-toggle="tab" href="#nav-home" role="tab"
                        aria-controls="nav-home" aria-selected="true">
                    详情
                </a>
                <a
                        class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab"
                        href="#nav-profile" role="tab" aria-controls="nav-profile"
                        aria-selected="false">
                    评论
                </a>
            </div>
        </nav>

        <div class="tab-content" id="nav-tabContent">

            <!-- 产品详情  -->
            <div class="tab-pane fade show active" id="nav-home" role="tabpanel"
                 aria-labelledby="nav-home-tab"><br>
                <h3>商品介绍</h3>
                <div class="commentinfo" style="margin-top: 20px;margin-bottom: 10px;"></div>
                ${product.info}
            </div>

            <!-- 产品评论  -->
            <div class="tab-pane fade" id="nav-profile" role="tabpanel"
                 aria-labelledby="nav-profile-tab"><br>

                <c:forEach items="${umList}" var="um">
                    <div class="commentinfo">
                        <h5 class="mt-0">${um.first}</h5>
                        <p>${um.second}</p>
                    </div>
                </c:forEach>

            </div>
        </div>

    </div>

    <!-- 模态框 -->
    <div class="modal fade" id="myModal">
        <div class="modal-dialog">
            <div class="modal-content" style="width:700px;margin-left: -120px;">

                <!-- 模态框头部 -->
                <div class="modal-header">
                    <h4 class="modal-title">购买页面</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <!-- 模态框主体 -->
                <div class="modal-body">
                    <img src="http://static.runoob.com/images/mix/img_avatar.png" style="width:400px;height: 400px;">
                    <div class="input-group mb-3" style="width: 200px;margin-top: 120px;float: right;margin-right: 20px;">
                        <div class="input-group-prepend">
                            <span class="input-group-text" >购买数量：</span>
                        </div>
                        <input id="purchaseNum" type="text" class="form-control" value="1" name="purchasenum">
                    </div>
                    <h4 style="float: right;margin-right: 20px;">单价：<div id="singlePrice" style="display: inline-block;">${product.price}</div></h4>
                    <h3 style="margin-top: 60px;float: right;margin-right: -160px;">总价：<div id="priceSum" style="display: inline-block">${product.price}</div></h3>
                </div>

                <!-- 模态框底部 -->
                <div class="modal-footer">
                    <button id="purchase" type="button" class="btn btn-secondary" data-dismiss="modal">提交</button>
                </div>

            </div>
        </div>
    </div>

    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>