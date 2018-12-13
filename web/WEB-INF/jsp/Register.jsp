<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: user0
  Date: 2018/12/13
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户注册</title>
</head>
<body>
<form method="post" action="registerUser.action">
    <table>
        <tr>
            <td>新用户名:</td>
            <td><input type="text" name="userId" /></td>
        </tr>
        <tr>
            <td>密码:</td>
            <td><input type="text" name="password" /></td>
        </tr>
        <tr>
            <td><input type="submit" value="提交"></td>
        </tr>
    </table>
</form>
</body>
</html>
