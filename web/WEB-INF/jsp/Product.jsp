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
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>


    <style type="text/css">
        .log{
            height: 100px;

            overflow: hidden;
        }
        .search{
            width: 200px;
            height: 20px;
            float: right;
            margin-right: 80px;
        }
        .productTitle{
            font-size: 13px;
        }
        .productInfo{
            font-size: 13px;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <div class="row log">
        <h1 style="width: auto;">文化创意中心</h1>

    </div>
    <div class="row">
        <nav class="navbar navbar-expand-sm navbar-dark" style="background-color: #1e50ae;font-weight: bold;width:100%;">
            <!-- Brand/logo -->
            <a class="navbar-brand" href="#">Logo</a>

            <!-- Links -->
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="#" >供求</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">展会</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">资讯</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                        产品
                    </a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="#">书画</a>
                        <a class="dropdown-item" href="#">文化服饰</a>
                        <a class="dropdown-item" href="#">器物</a>
                    </div>
                </li>
            </ul>
        </nav>
    </div>
    <div class="row" style="justify-content: center;margin-top: 30px;">
        <!-- 标签导航 -->
        <div class="col-3 center-block">
            <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
                <a class="nav-link active show" id="v-pills-calligraphy-tab" data-toggle="pill" href="#v-pills-calligraphy" role="tab" aria-controls="v-pills-hocalligraphyme" aria-selected="true">书法</a>
                <a class="nav-link" id="v-pills-Painting-tab" data-toggle="pill" href="#v-pills-Painting" role="tab" aria-controls="v-pills-Painting" aria-selected="false">绘画</a>
                <a class="nav-link" id="v-pills-Instrument-tab" data-toggle="pill" href="#v-pills-Instrument" role="tab" aria-controls="v-pills-Instrument" aria-selected="false">乐器</a>
                <a class="nav-link" id="v-pills-Dress-tab" data-toggle="pill" href="#v-pills-Dress" role="tab" aria-controls="v-pills-Dress" aria-selected="false">服饰</a>
            </div>
        </div>
        <!-- 对应标签导航显示的东西 -->
        <div class="col-8 center-block">
            <div class="tab-content" id="v-pills-tabContent">
                <div class="tab-pane fade active show" id="v-pills-calligraphy" role="tabpanel" aria-labelledby="v-pills-calligraphy-tab">
                    <!-- 一个产品 -->
                    <div class="card" style="width:230px;height: 350px;display: inline-block;margin-top: 5px;">
                        <img class="card-img-top" src="http://static.runoob.com/images/mix/img_avatar.png" alt="Card image cap" style="height: 180px;">
                        <div class="card-body" style="width: 100%;height: 150px;">
                            <h4 class="card-title" style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;width: 100%;">二胡二胡二胡二胡二胡</h4>
                            <a href="" class="card-text productTitle" style="width: 100%;height:80px;overflow: hidden;text-overflow: ellipsis;display: -webkit-box;-webkit-line-clamp: 4;-webkit-box-orient: vertical;">Some example text some example text. John Doe is an architect and engineer John Doe is an architect and engineerJohn Doe is an architect and engineer John Doe is an architect and engineer</a>
                            <h5 style="display: inline-block;color: #FF0000">$55.5</h5><small class="text-muted text-right" style="float:right;margin-top: 5px;">点击量：1000</small>
                        </div>
                    </div>
                    <!-- 一个产品结尾 -->
                    <!-- 一个产品 -->
                    <div class="card" style="width:230px;height: 350px;display: inline-block;margin-top: 5px;">
                        <img class="card-img-top" src="http://static.runoob.com/images/mix/img_avatar.png" alt="Card image cap" style="height: 180px;">
                        <div class="card-body" style="width: 100%;height: 150px;">
                            <h4 class="card-title" style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;width: 100%;">二胡二胡二胡二胡二胡</h4>
                            <a href="" class="card-text productTitle" style="width: 100%;height:80px;overflow: hidden;text-overflow: ellipsis;display: -webkit-box;-webkit-line-clamp: 4;-webkit-box-orient: vertical;">Some example text some example text. John Doe is an architect and engineer John Doe is an architect and engineerJohn Doe is an architect and engineer John Doe is an architect and engineer</a>
                            <h5 style="display: inline-block;color: #FF0000">$55.5</h5><small class="text-muted text-right" style="float:right;margin-top: 5px;">点击量：1000</small>
                        </div>
                    </div>
                    <!-- 一个产品结尾 -->
                    <!-- 一个产品 -->
                    <div class="card" style="width:230px;height: 350px;display: inline-block;margin-top: 5px;">
                        <img class="card-img-top" src="http://static.runoob.com/images/mix/img_avatar.png" alt="Card image cap" style="height: 180px;">
                        <div class="card-body" style="width: 100%;height: 150px;">
                            <h4 class="card-title" style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;width: 100%;">二胡二胡二胡二胡二胡</h4>
                            <a href="" class="card-text productTitle" style="width: 100%;height:80px;overflow: hidden;text-overflow: ellipsis;display: -webkit-box;-webkit-line-clamp: 4;-webkit-box-orient: vertical;">Some example text some example text. John Doe is an architect and engineer John Doe is an architect and engineerJohn Doe is an architect and engineer John Doe is an architect and engineer</a>
                            <h5 style="display: inline-block;color: #FF0000">$55.5</h5><small class="text-muted text-right" style="float:right;margin-top: 5px;">点击量：1000</small>
                        </div>
                    </div>
                    <!-- 一个产品结尾 -->
                    <!-- 一个产品 -->
                    <div class="card" style="width:230px;height: 350px;display: inline-block;margin-top: 5px;">
                        <img class="card-img-top" src="http://static.runoob.com/images/mix/img_avatar.png" alt="Card image cap" style="height: 180px;">
                        <div class="card-body" style="width: 100%;height: 150px;">
                            <h4 class="card-title" style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;width: 100%;">二胡二胡二胡二胡二胡</h4>
                            <a href="" class="card-text productTitle" style="width: 100%;height:80px;overflow: hidden;text-overflow: ellipsis;display: -webkit-box;-webkit-line-clamp: 4;-webkit-box-orient: vertical;">Some example text some example text. John Doe is an architect and engineer John Doe is an architect and engineerJohn Doe is an architect and engineer John Doe is an architect and engineer</a>
                            <h5 style="display: inline-block;color: #FF0000">$55.5</h5><small class="text-muted text-right" style="float:right;margin-top: 5px;">点击量：1000</small>
                        </div>
                    </div>
                    <!-- 一个产品结尾 -->

                    <!-- 分页 -->
                    <div class="row" style="justify-content: center;margin-top: 30px;">
                        <ul class="pagination">
                            <li class="page-item"><a class="page-link" href="#">Previous</a></li>
                            <li class="page-item"><a class="page-link" href="#">Next</a></li>
                        </ul>
                    </div>

                </div>
                <div class="tab-pane fade" id="v-pills-Painting" role="tabpanel" aria-labelledby="v-pills-Painting-tab">
                    <!-- 一个产品 -->
                    <div class="card" style="width:230px;height: 350px;display: inline-block;margin-top: 5px;">
                        <img class="card-img-top" src="http://static.runoob.com/images/mix/img_avatar.png" alt="Card image cap" style="height: 180px;">
                        <div class="card-body" style="width: 100%;height: 150px;">
                            <h4 class="card-title" style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;width: 100%;">二胡二胡二胡二胡二胡</h4>
                            <a href="" class="card-text productTitle" style="width: 100%;height:80px;overflow: hidden;text-overflow: ellipsis;display: -webkit-box;-webkit-line-clamp: 4;-webkit-box-orient: vertical;">Some example text some example text. John Doe is an architect and engineer John Doe is an architect and engineerJohn Doe is an architect and engineer John Doe is an architect and engineer</a>
                            <h5 style="display: inline-block;color: #FF0000">$55.5</h5><small class="text-muted text-right" style="float:right;margin-top: 5px;">点击量：1000</small>
                        </div>
                    </div>
                    <!-- 一个产品结尾 -->

                    <!-- 分页 -->
                    <div class="row" style="justify-content: center;margin-top: 30px;">
                        <ul class="pagination">
                            <li class="page-item"><a class="page-link" href="#">Previous</a></li>
                            <li class="page-item"><a class="page-link" href="#">Next</a></li>
                        </ul>
                    </div>

                </div>
                <div class="tab-pane fade" id="v-pills-Instrument" role="tabpanel" aria-labelledby="v-pills-Instrument-tab">
                    <!-- 一个产品 -->
                    <div class="card" style="width:230px;height: 350px;display: inline-block;margin-top: 5px;">
                        <img class="card-img-top" src="http://static.runoob.com/images/mix/img_avatar.png" alt="Card image cap" style="height: 180px;">
                        <div class="card-body" style="width: 100%;height: 150px;">
                            <h4 class="card-title" style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;width: 100%;">二胡二胡二胡二胡二胡</h4>
                            <a href="" class="card-text productTitle" style="width: 100%;height:80px;overflow: hidden;text-overflow: ellipsis;display: -webkit-box;-webkit-line-clamp: 4;-webkit-box-orient: vertical;">Some example text some example text. John Doe is an architect and engineer John Doe is an architect and engineerJohn Doe is an architect and engineer John Doe is an architect and engineer</a>
                            <h5 style="display: inline-block;color: #FF0000">$55.5</h5><small class="text-muted text-right" style="float:right;margin-top: 5px;">点击量：1000</small>
                        </div>
                    </div>
                    <!-- 一个产品结尾 -->

                    <!-- 分页 -->
                    <div class="row" style="justify-content: center;margin-top: 30px;">
                        <ul class="pagination">
                            <li class="page-item"><a class="page-link" href="#">Previous</a></li>
                            <li class="page-item"><a class="page-link" href="#">Next</a></li>
                        </ul>
                    </div>

                </div>
                <div class="tab-pane fade" id="v-pills-Dress" role="tabpanel" aria-labelledby="v-pills-Dress-tab">
                    <!-- 一个产品 -->
                    <div class="card" style="width:230px;height: 350px;display: inline-block;margin-top: 5px;">
                        <img class="card-img-top" src="http://static.runoob.com/images/mix/img_avatar.png" alt="Card image cap" style="height: 180px;">
                        <div class="card-body" style="width: 100%;height: 150px;">
                            <h4 class="card-title" style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;width: 100%;">二胡二胡二胡二胡二胡</h4>
                            <a href="" class="card-text productTitle" style="width: 100%;height:80px;overflow: hidden;text-overflow: ellipsis;display: -webkit-box;-webkit-line-clamp: 4;-webkit-box-orient: vertical;">Some example text some example text. John Doe is an architect and engineer John Doe is an architect and engineerJohn Doe is an architect and engineer John Doe is an architect and engineer</a>
                            <h5 style="display: inline-block;color: #FF0000">$55.5</h5><small class="text-muted text-right" style="float:right;margin-top: 5px;">点击量：1000</small>
                        </div>
                    </div>
                    <!-- 一个产品结尾 -->

                    <!-- 分页 -->
                    <div class="row" style="justify-content: center;margin-top: 30px;">
                        <ul class="pagination">
                            <li class="page-item"><a class="page-link" href="#">Previous</a></li>
                            <li class="page-item"><a class="page-link" href="#">Next</a></li>
                        </ul>
                    </div>

                </div>
            </div>
        </div>
    </div>

    <ul>
        <c:forEach items="${allProductList}" var="product">
            <li>
                <a href="#">
                        ${product.proId}
                        ${product.userId}
                        ${product.proName}
                    ${product.price}
                    ${product.hits}
                    ${product.purchase}
                    ${product.isPass}
                    ${product.productType}
                </a>
            </li>
        </c:forEach>
    </ul>
    <div class="row" style="text-align: left;background-color: black;color: white;margin-top: 40px;justify-content: center;">
        <div style="padding: 50px;padding-left: 80px;padding-right: 150px">Designed and built with all the love in the world by @mdo and @fat. Maintained by the core team with the help of our contributors.

            本项目源码受 MIT开源协议保护，文档受 CC BY 3.0 开源协议保护。</div>
    </div>
</body>
</html>

