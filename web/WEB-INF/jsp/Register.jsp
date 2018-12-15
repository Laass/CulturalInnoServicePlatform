<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: user0
  Date: 2018/12/13
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户注册</title>
    <!-- <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous"> -->

    <!-- <script src="http://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>  -->
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body style="background-color: #eee;">
<div class="container">
    <div style="width: 500px;height:430px;margin-top: 130px;margin-left: auto;margin-right:auto;background-color: white;padding:30px 90px 90px 90px;opacity: 0.8;border-radius: 5%;"><!--padding-top: 30px;">-->
        <h2>Register</h2>
        <hr/>
        <form method="post" action="/registerUser.action" style="width: 300px;">
            <div class="form-group">
                <label for="userPhone">Phone:</label>
                <input type="text" class="form-control" id="userPhone" name="userId" placeholder="Enter Phone">
            </div>
            <div class="form-group">
                <label for="pwd">Password:</label>
                <input type="password" class="form-control" id="pwd" name="password" placeholder="Enter password">
            </div>
            <label class="radio-inline" style="margin-bottom: 10px">
                <input type="radio" name="type" id="type1" value="32" checked>个人用户 &nbsp; &nbsp; &nbsp; &nbsp;
            </label>
            <label class="radio-inline"  style="margin-bottom: 10px">
                <input type="radio" name="type" id="type2"  value="16">企业用户
            </label>
            <div class="form-check">
                <label class="form-check-label" style="margin-bottom: 15px;">
                    <input class="form-check-input" type="checkbox"> Remember me
                </label>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
</div>
</body>
</html>

<%--<html>--%>
<%--<head>--%>
    <%--<title>用户注册</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<form method="post" action="registerUser.action">--%>
    <%--<table>--%>
        <%--<tr>--%>
            <%--<td>新用户名:</td>--%>
            <%--<td><input type="text" name="userId" /></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td>密码:</td>--%>
            <%--<td><input type="text" name="password" /></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td><input type="submit" value="提交"></td>--%>
        <%--</tr>--%>
    <%--</table>--%>
<%--</form>--%>
<%--</body>--%>
<%--</html>--%>
