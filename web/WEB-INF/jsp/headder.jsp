<%--
  Created by IntelliJ IDEA.
  User: user0
  Date: 2018/12/14
  Time: 17:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>
<html>
<style>
    /*    .navbar-nav > li{
            width: 120px;
        }*/
</style>
<body>
<div class="log">
    <h1 style="width: auto;display: inline-block;">文化创意中心</h1>
    <div class="portrait">
        <a href="" class="portrait"><img src="http://static.runoob.com/images/mix/img_fjords_wide.jpg"></a>
    </div>
</div>
<div class="row">
    <nav class="navbar navbar-expand-sm navbar-dark"
         style="height:70px;background-color: #1e50ae;font-weight: bold;width:100%;">
        <!-- Brand/logo -->
        <a class="navbar-brand" href="#">Logo</a>

        <!-- Links -->
        <ul class="navbar-nav">
            <li class="nav-item guidLi">
                <a class="nav-link" href="#" style="color: #FCFCFC;">供求</a>
            </li>
            <div style="color: white;margin-top: 3px;font-size: 26px;">/</div>
            <li class="nav-item guidLi">
                <a class="nav-link" href="Exhibition.html" style="color: #FCFCFC;padding-right: 0px;">展会</a>
            </li>
            <div style="color: white;margin-top: 3px;font-size: 26px;">/</div>
            <li class="nav-item guidLi">
                <a class="nav-link" href="News.html" style="color: #FCFCFC;padding-right: 0px;">资讯</a>
            </li>
            <div style="color: white;margin-top: 3px;font-size: 26px;">/</div>
            <li class="nav-item dropdown guidLi">
                <a class="nav-link dropdown-toggle" href="Product.html" id="navbardrop" data-toggle="dropdown"
                   style="color: #FCFCFC;padding-right: 0px;">
                    产品
                </a>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="#">书画</a>
                    <a class="dropdown-item" href="#">文化</a>
                    <a class="dropdown-item" href="#">器物</a>
                    <a class="dropdown-item" href="#">服饰</a>
                </div>
            </li>
        </ul>
        <form class="navbar-form navbar-right" style="margin-left: 600px;">
            <div class="form-group">
                <div class="input-group">
                    <div class="input-group-addon">
                        <i class="glyphicon glyphicon-search"></i>
                    </div>
                    <input type="text" class="form-control" placeholder="搜索"/>
                    <button type="button" class="btn btn-default" style="display: inline-block;">搜索</button>
                </div>
            </div>
        </form>
    </nav>
</div>
</body>
</html>