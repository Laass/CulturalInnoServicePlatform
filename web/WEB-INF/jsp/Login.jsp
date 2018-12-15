<%--
  Created by IntelliJ IDEA.
  User: JY
  Date: 2018/12/12
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
</head>
<body style="background-color: #eee;">
<div class="container">
    <div style="width: 500px;height:400px;margin-top: 130px;margin-left: auto;margin-right:auto ;background-color: white;padding:90px;opacity: 0.8;border-radius: 5%;padding-top: 30px;">
        <h2>Login</h2>
        <hr/>
        ${message}
        <form style="width: 300px;" method="post" action="/login.action">
            <div class="form-group">
                <label for="email">Phone:</label>
                <input type="text" class="form-control" name="userId" id="email" placeholder="Enter Phone">
            </div>
            <div class="form-group">
                <label for="pwd">Password:</label>
                <input type="password" class="form-control" name="password" id="pwd" placeholder="Enter password">
            </div>
            <div class="form-check" style="margin-bottom: 15px;">
                <label class="form-check-label">
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
    <%--<title>登录</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<h1>登录界面</h1>--%>
    <%--<h1>${message}</h1>--%>
    <%--<form action="login.action" method="POST">--%>
        <%--用户名:<input name="userId" type="text">--%>
        <%--密码:  <input name="password" type="text">--%>
        <%--<input type="submit" value="登录">--%>
    <%--</form>--%>
<%--</body>--%>
<%--</html>--%>
