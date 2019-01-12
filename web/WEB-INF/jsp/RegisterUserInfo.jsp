<%--
  Created by IntelliJ IDEA.
  User: user0
  Date: 2018/12/13
  Time: 15:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<div class="container">
    <div style="width: 500px;height:auto;margin-top: 130px;margin-left: auto;margin-right:auto;background-color: white;padding:90px;padding-bottom:40px;opacity: 0.8;border-radius: 5%;padding-top: 30px;">
        <h2>Register</h2>
        <hr/>
        <form method="post" action="/registerUserInfo.action" style="width: 300px;" accept-charset="UTF-8">
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text">用户名</span>
                </div>
                <input type="text" class="form-control" placeholder="username" id="nickName" name="nickName">
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text">真实姓名</span>
                </div>
                <input type="text" class="form-control" placeholder="real name" id="realName" name="realName">
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text">简介</span>
                </div>
                <input type="text" class="form-control" placeholder="introduction" id="intro" name="intro">
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text">电子邮箱</span>
                </div>
                <input type="text" class="form-control" placeholder="email" id="email" name="email">
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text">地址</span>
                </div>
                <input type="text" class="form-control" placeholder="address" id="address" name="address">
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text">QQ账号</span>
                </div>
                <input type="text" class="form-control" placeholder="QQ" id="qq" name="qq">
            </div>
            <%--<div class="input-group mb-3">--%>
                <%--<div class="input-group-prepend">--%>
                    <%--<span class="input-group-text">电话号码</span>--%>
                <%--</div>--%>
                <%--<input type="text" class="form-control" placeholder="tel" id="tel" name="tel">--%>
            <%--</div>--%>
            <button type="submit" class="btn btn-primary">Register</button>
        </form>
    </div>
</div>
</body>
</html>
<%--<html>--%>
<%--<head>--%>
    <%--<title>用户信息注册</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<form method="post" action="/registerUserInfo.action">--%>
    <%--<table>--%>
        <%--&lt;%&ndash;<tr>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<td>用户名：</td>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<td>${user.userId}</td>&ndash;%&gt;--%>
        <%--&lt;%&ndash;</tr>&ndash;%&gt;--%>
        <%--<tr>--%>
            <%--<td>昵称：</td>--%>
            <%--<td><input type="text" name="nickName"></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td>真实姓名：</td>--%>
            <%--<td><input type="text" name="realName"></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td>简介：</td>--%>
            <%--<td><input type="text" name="intro"></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td>电子邮箱：</td>--%>
            <%--<td><input type="email" name="email"></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td>地址：</td>--%>
            <%--<td><input type="text" name="address"></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td>QQ号：</td>--%>
            <%--<td><input type="text" name="qq"></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td>电话号码：</td>--%>
            <%--<td><input type="text" name="tel"></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td><input type="submit" value="注册"></td>--%>
        <%--</tr>--%>
    <%--</table>--%>
<%--</form>--%>
<%--</body>--%>
<%--</html>--%>
