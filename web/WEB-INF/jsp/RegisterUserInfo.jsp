<%--
  Created by IntelliJ IDEA.
  User: user0
  Date: 2018/12/13
  Time: 15:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户信息注册</title>
</head>
<body>
<form method="post" action="/registerUserInfo.action">
    <table>
        <%--<tr>--%>
            <%--<td>用户名：</td>--%>
            <%--<td>${user.userId}</td>--%>
        <%--</tr>--%>
        <tr>
            <td>昵称：</td>
            <td><input type="text" name="nickName"></td>
        </tr>
        <tr>
            <td>真实姓名：</td>
            <td><input type="text" name="realName"></td>
        </tr>
        <tr>
            <td>简介：</td>
            <td><input type="text" name="intro"></td>
        </tr>
        <tr>
            <td>电子邮箱：</td>
            <td><input type="email" name="email"></td>
        </tr>
        <tr>
            <td>地址：</td>
            <td><input type="text" name="address"></td>
        </tr>
        <tr>
            <td>QQ号：</td>
            <td><input type="text" name="qq"></td>
        </tr>
        <tr>
            <td>电话号码：</td>
            <td><input type="text" name="tel"></td>
        </tr>
        <tr>
            <td><input type="submit" value="注册"></td>
        </tr>
    </table>
</form>
</body>
</html>
