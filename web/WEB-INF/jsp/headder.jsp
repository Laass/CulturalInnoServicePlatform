<%--
  Created by IntelliJ IDEA.
  User: user0
  Date: 2018/12/14
  Time: 17:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/css/bootstrap.min.css">
<link rel="stylesheet" href="css/base.css">
<script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>
<html>
<body>
<div class="log" >
    <a href="" style="display: inline-block;"><img style="margin-top: -10px;margin-left: 30px;width:70px;height: 70px;" src="images/leaf.png"></a>
    <h1 style="width: auto;display: inline-block;margin-top:20px;margin-left:30px;">文化创意产业公共服务平台</h1>
    <div class="portrait">
        <a href="jsp/index.html" class="portrait"><img style="margin-top: -5px;" src="http://static.runoob.com/images/mix/img_fjords_wide.jpg"></a>
    </div>
</div>
<div class="row">
    <nav class="navbar navbar-expand-sm navbar-dark" style="height:70px;background-color: #1e50ae;font-weight: bold;width:100%;">
        <!-- Brand/logo -->
        <%--<a class="navbar-brand" href="#">Logo</a>--%>

        <!-- Links -->
        <ul class="navbar-nav">
            <li class="nav-item guidLi">
                <a class="nav-link" href="SupplyAndDemand.html" style="color: #FCFCFC;">供求</a>
            </li><div style="color: white;margin-top: 3px;font-size: 26px;">/</div>
            <li class="nav-item guidLi">
                <a class="nav-link" href="Exhibition.html" style="color: #FCFCFC;padding-right: 0px;">展会</a>
            </li><div style="color: white;margin-top: 3px;font-size: 26px;">/</div>
            <li class="nav-item guidLi">
                <a class="nav-link" href="News.html" style="color: #FCFCFC;padding-right: 0px;">资讯</a>
            </li><div style="color: white;margin-top: 3px;font-size: 26px;">/</div>
            <li class="nav-item dropdown guidLi">
                <a class="nav-link dropdown-toggle" href="Product.html" id="navbardrop" data-toggle="dropdown" style="color: #FCFCFC;padding-right: 0px;">
                    产品
                </a>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="Product?ptype=call">书画</a>
                    <a class="dropdown-item" href="Product?ptype=draw">绘画</a>
                    <a class="dropdown-item" href="Product?ptype=ins">乐器</a>
                    <a class="dropdown-item" href="Product?ptype=dress">服饰</a>
                </div>
            </li>
        </ul>

        <div class="btn-group" role="group" style="margin-left: 620px;">
            <img src="images/flogo.PNG" style="float: right;width: 48px;height: 30px;" class="dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <div class="dropdown-menu dropdown-menu-right" style="justify-content: center;">
                <button class="dropdown-item" type="button"><a href="Order.html">订单</a></button>
                <button class="dropdown-item" type="button"><a href="Collection.html">收藏夹</a></button>
            </div>
        </div>
        <a href="search.html"><img src="images/search.png" style="width: 25px;height: 25px;margin-left: 20px;"></a>
    </nav>
</div>


</body>
</html>
