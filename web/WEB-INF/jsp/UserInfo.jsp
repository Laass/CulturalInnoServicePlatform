<%--
  Created by IntelliJ IDEA.
  User: user0
  Date: 2018/12/13
  Time: 18:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户注册</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/base.css">
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/ajaxFileUpload.js"></script>
    <script type="text/javascript" src="js/userInfo.js"></script>
</head>
<body style="background-color: #eee;">
<%
    String imgLoc = (String) request.getAttribute("imgLocation");
    if (imgLoc == null || imgLoc.trim().equals(""))
        imgLoc = "images/addPortrait.png";
    request.setAttribute("imgLoc", imgLoc);
%>
<div class="log" >
    <a href="" style="display: inline-block;"><img style="margin-top: -10px;margin-left: 30px;width:70px;height: 70px;" src="images/leaf.png"></a>
    <h1 style="width: auto;display: inline-block;margin-top:20px;margin-left:30px;">文化创意产业公共服务平台</h1>
    <div class="portrait">
        <a href="jsp/index.html" class="portrait"><img style="margin-top: -5px;" src="${imgLocation}" alt="暂无头像"></a>
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
<%--<jsp:include page="headder.jsp"/>--%>
<div class="container">
    <div style="width: 500px;height:auto;margin-top: 130px;margin-left: auto;margin-right:auto;background-color: white;padding:90px;padding-bottom:40px;opacity: 0.8;border-radius: 5%;padding-top: 30px;">
        <h2>User Info</h2>
        <hr/>
        <form method="post" action="" style="width: 300px;" onsubmit="false">
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text">用户名</span>
                </div>
                <input type="text" class="form-control" value="${nickName}" id="nickName" name="nickName" readonly="readonly">
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text">真实姓名</span>
                </div>
                <input type="text" class="form-control" value="${realName}" id="realName" name="realName" readonly="readonly">
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text">简介</span>
                </div>
                <input type="text" class="form-control" value="${intro}" id="intro" name="intro" readonly="readonly">
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text">电子邮箱</span>
                </div>
                <input type="text" class="form-control" value="${email}" id="email" name="email" readonly="readonly">
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text">地址</span>
                </div>
                <input type="text" class="form-control" value="${address}" id="address" name="address" readonly="readonly">
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text">QQ账号</span>
                </div>
                <input type="text" class="form-control" value="${qq}" id="qq" name="qq" readonly="readonly">
            </div>
            <div class="input-group mb-3">
            <div class="input-group-prepend">
            <span class="input-group-text">电话号码</span>
            </div>
            <input type="text" class="form-control" value="${tel}" id="tel" name="tel" readonly="readonly">
            </div>
            <img src="${imgLoc}" class="img-thumbnail" id="portrait" style="cursor:pointer" alt="暂无头像">
            <input id="choosePortrait" name="file" type="file" style="display: none;"/>
            <button type="submit" class="btn btn-primary" id="enableEdit">修改信息</button>
            <button type="button" class="btn btn-warning" id="submit">提交修改</button>
            <button type="button" class="btn btn-danger" id="delPortrait">删除头像</button>
        </form>
    </div>
</div>
<%--<jsp:include page="footer.jsp"/>--%>
<body>
<div class="row" style="text-align: left;background-color: black;color: white;margin-top: 40px;">
    <div style="padding: 50px;padding-left: 80px;padding-right: 150px">Designed and built with all the love in the world by @mdo and @fat. Maintained by the core team with the help of our contributors.

        本项目源码受 MIT开源协议保护，文档受 CC BY 3.0 开源协议保护。</div>
</div>
</body>
</html>

<%--<html>--%>
<%--<head>--%>
    <%--<title>用户详细信息</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--${userId}--%>
<%--${nickName}--%>
<%--${realName}--%>
<%--${intro}--%>
<%--${email}--%>
<%--${address}--%>
<%--${qq}--%>
<%--${tel}--%>
<%--</body>--%>
<%--</html>--%>
