<%--
  Created by IntelliJ IDEA.
  User: user0
  Date: 2018/12/13
  Time: 19:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <script src="js/productPage.js"></script>


    <style type="text/css">
        .log {
            height: 100px;

            overflow: hidden;
        }

        .search {
            width: 200px;
            height: 20px;
            float: right;
            margin-right: 80px;
        }

        .productTitle {
            font-size: 13px;
        }

        .productInfo {
            font-size: 13px;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="headder.jsp"/>
    <div class="row" style="justify-content: center;margin-top: 30px;">
        <!-- 标签导航 -->
        <div class="col-3 center-block">
            <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
                <a class="nav-link active show" id="v-pills-calligraphy-tab" data-toggle="pill"
                   href="#v-pills-calligraphy" role="tab" aria-controls="v-pills-hocalligraphyme" aria-selected="true">书法</a>

                <a class="nav-link" id="v-pills-Painting-tab" data-toggle="pill" href="#v-pills-Painting" role="tab"
                   aria-controls="v-pills-Painting" aria-selected="false">绘画</a>

                <a class="nav-link" id="v-pills-Instrument-tab" data-toggle="pill" href="#v-pills-Instrument" role="tab"
                   aria-controls="v-pills-Instrument" aria-selected="false">乐器</a>

                <a class="nav-link" id="v-pills-Dress-tab" data-toggle="pill" href="#v-pills-Dress" role="tab"
                   aria-controls="v-pills-Dress" aria-selected="false">服饰</a>

            </div>
        </div>
        <!-- 对应标签导航显示的东西 -->
        <div class="col-8 center-block">
            <div class="tab-content" id="v-pills-tabContent">
                <%--书法部分--%>
                <div class="tab-pane fade active show" id="v-pills-calligraphy" role="tabpanel"
                     aria-labelledby="v-pills-calligraphy-tab">
                    <c:forEach items="${calliList}" var="product">
                        <!-- 一个产品 -->
                        <div class="card" style="width:230px;height: 350px;display: inline-block;margin-top: 5px;">
                            <img class="card-img-top" src="http://static.runoob.com/images/mix/img_avatar.png"
                                 alt="${product.proName}" style="height: 180px;">
                            <div class="card-body" style="width: 100%;height: 150px;">
                                <h4 class="card-title"
                                    style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;width: 100%;">${product.proName}</h4>
                                <a href="" class="card-text productTitle"
                                   style="width: 100%;height:80px;overflow: hidden;text-overflow: ellipsis;display: -webkit-box;-webkit-line-clamp: 4;-webkit-box-orient: vertical;">${product.productType}</a>
                                <h5 style="display: inline-block;color: #FF0000">$${product.price}</h5>
                                <small class="text-muted text-right" style="float:right;margin-top: 5px;">
                                    点击量：${product.hits}</small>
                            </div>
                        </div>
                        <!-- 一个产品结尾 -->
                    </c:forEach>


                    <!-- 分页 -->
                    <div class="row" style="justify-content: center;margin-top: 30px;">
                        <ul class="pagination">
                            <h5 id="curPage">1</h5>
                            <li class="page-item"><a class="page-link" href="#" id="pre">Previous</a></li>
                            <li class="page-item"><a class="page-link" href="#" id="next">Next</a></li>
                        </ul>
                    </div>

                </div>

                <div class="tab-pane fade" id="v-pills-Painting" role="tabpanel" aria-labelledby="v-pills-Painting-tab">
                    <c:forEach items="${paintList}" var="product">
                        <!-- 一个产品 -->
                        <div class="card" style="width:230px;height: 350px;display: inline-block;margin-top: 5px;">
                            <img class="card-img-top" src="http://static.runoob.com/images/mix/img_avatar.png"
                                 alt="${product.proName}" style="height: 180px;">
                            <div class="card-body" style="width: 100%;height: 150px;">
                                <h4 class="card-title"
                                    style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;width: 100%;">${product.proName}</h4>
                                <a href="" class="card-text productTitle"
                                   style="width: 100%;height:80px;overflow: hidden;text-overflow: ellipsis;display: -webkit-box;-webkit-line-clamp: 4;-webkit-box-orient: vertical;">${product.productType}</a>
                                <h5 style="display: inline-block;color: #FF0000">$${product.price}</h5>
                                <small class="text-muted text-right" style="float:right;margin-top: 5px;">
                                    点击量：${product.hits}</small>
                            </div>
                        </div>
                        <!-- 一个产品结尾 -->
                    </c:forEach>

                    <!-- 分页 -->
                    <div class="row" style="justify-content: center;margin-top: 30px;">
                        <ul class="pagination">
                            <h5 id="PaintingcurPage">1</h5>
                            <li class="page-item"><a class="page-link" href="#" id="Paintingpre">Previous</a></li>
                            <li class="page-item"><a class="page-link" href="#" id="Paintingnext">Next</a></li>
                        </ul>
                    </div>

                </div>
                <div class="tab-pane fade" id="v-pills-Instrument" role="tabpanel"
                     aria-labelledby="v-pills-Instrument-tab">
                    <c:forEach items="${musicList}" var="product">
                        <!-- 一个产品 -->
                        <div class="card" style="width:230px;height: 350px;display: inline-block;margin-top: 5px;">
                            <img class="card-img-top" src="http://static.runoob.com/images/mix/img_avatar.png"
                                 alt="${product.proName}" style="height: 180px;">
                            <div class="card-body" style="width: 100%;height: 150px;">
                                <h4 class="card-title"
                                    style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;width: 100%;">${product.proName}</h4>
                                <a href="" class="card-text productTitle"
                                   style="width: 100%;height:80px;overflow: hidden;text-overflow: ellipsis;display: -webkit-box;-webkit-line-clamp: 4;-webkit-box-orient: vertical;">${product.productType}</a>
                                <h5 style="display: inline-block;color: #FF0000">$${product.price}</h5>
                                <small class="text-muted text-right" style="float:right;margin-top: 5px;">
                                    点击量：${product.hits}</small>
                            </div>
                        </div>
                        <!-- 一个产品结尾 -->
                    </c:forEach>

                    <!-- 分页 -->
                    <div class="row" style="justify-content: center;margin-top: 30px;">
                        <ul class="pagination">
                            <h5 id="InstrumentcurPage">1</h5>
                            <li class="page-item"><a class="page-link" href="#" id="Instrumentpre">Previous</a></li>
                            <li class="page-item"><a class="page-link" href="#" id="Instrumentnext">Next</a></li>
                        </ul>
                    </div>

                </div>
                <div class="tab-pane fade" id="v-pills-Dress" role="tabpanel" aria-labelledby="v-pills-Dress-tab">
                    <c:forEach items="${garmentList}" var="product">
                        <!-- 一个产品 -->
                        <div class="card" style="width:230px;height: 350px;display: inline-block;margin-top: 5px;">
                            <img class="card-img-top" src="http://static.runoob.com/images/mix/img_avatar.png"
                                 alt="${product.proName}" style="height: 180px;">
                            <div class="card-body" style="width: 100%;height: 150px;">
                                <h4 class="card-title"
                                    style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;width: 100%;">${product.proName}</h4>
                                <a href="" class="card-text productTitle"
                                   style="width: 100%;height:80px;overflow: hidden;text-overflow: ellipsis;display: -webkit-box;-webkit-line-clamp: 4;-webkit-box-orient: vertical;">${product.productType}</a>
                                <h5 style="display: inline-block;color: #FF0000">$${product.price}</h5>
                                <small class="text-muted text-right" style="float:right;margin-top: 5px;">
                                    点击量：${product.hits}</small>
                            </div>
                        </div>
                        <!-- 一个产品结尾 -->
                    </c:forEach>

                    <!-- 分页 -->
                    <div class="row" style="justify-content: center;margin-top: 30px;">
                        <ul class="pagination">
                            <h5 id="DresscurPage">1</h5>
                            <li class="page-item"><a class="page-link" href="#" id="Dresspre">Previous</a></li>
                            <li class="page-item"><a class="page-link" href="#" id="Dressnext">Next</a></li>
                        </ul>
                    </div>

                </div>
            </div>
        </div>
    </div>

    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>

