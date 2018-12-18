<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user0
  Date: 2018/12/14
  Time: 10:51
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
    <link rel="stylesheet" href="css/base.css">
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <script src="js/page.js"></script>

</head>
<body>
<div class="container-fluid">
    <jsp:include page="headder.jsp"/>

    <!-- 当前栏目名字 -->
    <div class="row">
        <h3 style="margin-left: 90px;margin-top: 30px;">展会信息</h3>
    </div>

    <div class="row">
        <div style="width: 1100px;justify-content: center;margin:0 auto;margin-top: 60px;border: 1px solid #eee;">
            <ul id="lists" class="essayList">
                <c:forEach items="${eInfoList}" var="exhiInfo">
                    <div class="listOuter">
                        <li>
                            <a href="getExhibitionInfo?exhiId=${exhiInfo.fifth}" style="color: black;">
                                <span class="title">
                                        ${exhiInfo.first}
                                </span>
                                <span>${exhiInfo.second}</span>
                                <span class="newsTime">
                                        ${exhiInfo.third}
                                </span>
                            </a>
                        </li>
                    </div>
                </c:forEach>
            </ul>
        </div>
    </div>
    <!-- 分页 -->
    <div class="row" style="justify-content: center;margin-top: 30px;">
        <h4 id="curPage">当前页</h4>
        <ul class="pagination">
            <li class="page-item"><a class="page-link" id="pre">Previous</a></li>
            <li class="page-item"><a class="page-link" id="next">Next</a></li>
        </ul>
    </div>
    <jsp:include page="footer.jsp"/>
</body>
</html>

