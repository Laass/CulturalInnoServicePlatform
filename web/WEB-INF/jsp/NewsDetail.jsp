<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user0
  Date: 2018/12/16
  Time: 11:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
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
    <script src="js/base.js"></script>

</head>
<body>
<div class="container-fluid">
    <jsp:include page="headder.jsp"/>
    <div style="margin: 0 auto;margin-left:auto;width: 1200px;margin-top: 30px;">
        <div style="display: block;text-align: center;">
            <h2>${news.title}</h2>
            <div id="enshrineNews">收藏</div>
        </div>
        <div style="display: block;">
            <div class="essayInfo"  style="display: block;text-align: center;">
                <small>发布人:${userInfo.nickName}</small>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                <small>发布时间：${news.establishTime}</small>
            </div>
        </div>
    </div>
    <div class="row essayContain" style="margin: 0 auto;width: 1200px;padding: 20px;">
        <p>${news.content}</p>
    </div>
    <div class="input-group" style="margin: 0 auto;margin-top: 20px;width: 1000px;">
        <div class="input-group-prepend">
            <span class="input-group-text">留言</span>
        </div>
        <div id="oid" style="display: none">${news.newsId}</div>
        <div id="essayType" style="display: none">news</div>
        <textarea class="form-control" aria-label="With textarea" id="messageContent"></textarea>
        <button class="btn btn-light" id="publishMessage">发表留言</button>
    </div>
    <div style="margin: 0 auto; width: 1000px;">
        <br/>

        <c:forEach items="${umList}" var="um">
            <div class="commentinfo">
                <h5 class="mt-0">${um.first}</h5>
                <p>${um.second}</p>
            </div>
        </c:forEach>

    </div>
    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>