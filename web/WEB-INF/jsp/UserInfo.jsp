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
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body style="background-color: #eee;">
<jsp:include page="headder.jsp"/>
<div class="container">
    <div style="width: 500px;height:auto;margin-top: 130px;margin-left: auto;margin-right:auto;background-color: white;padding:90px;padding-bottom:40px;opacity: 0.8;border-radius: 5%;padding-top: 30px;">
        <h2>User Info</h2>
        <hr/>
        <form method="post" action="" style="width: 300px;">
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
            <%--<button type="submit" class="btn btn-primary">Register</button>--%>
        </form>
    </div>
</div>
<jsp:include page="footer.jsp"/>
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
