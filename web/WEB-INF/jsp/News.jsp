<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <script src="js/page.js"></script>
    <link rel="stylesheet" href="css/base.css">

</head>
<body>
<div class="container-fluid">
    <jsp:include page="headder.jsp"/>

    <div class="row">
        <div style="width: 1100px;justify-content: center;margin:0 auto;margin-top: 80px;border: 1px solid #eee;">
            <ul id="lists" class="essayList">
                <c:forEach items="${allNewsList}" var="news">
                    <c:if test="${news.isPass == 1}">
                    <div class="listOuter">
                        <li>
                            <a href="getNewsById?newsId=${news.newsId}" style="color: black;">
                                    ${news.title}
                                <div class="subTitle"> ${news.establishTime}</div>
                                    <%--<div style="display: inline-block;font-size: 12px;color: #eeeeee">点击量:</div><div class="subTitle">${news.hits}</div>--%>
                            </a>
                                <%----%>
                        </li>
                            <%--<hr style="border: 0.5px solid #eee;padding-top：13px;margin-bottom: -3px;">--%>
                    </div>
                    </c:if>
                </c:forEach>
            </ul>
        </div>
    </div>

    <!-- 分页 -->
    <div class="row" style="justify-content: center;margin-top: 30px;">
        <ul class="pagination">
            <h4 id="curPage">当前页</h4>
            <li class="page-item"><button class="btn btn-default" id="pre">Previous</button></li>
            <li class="page-item"><button class="btn btn-default" id="next">Next</button></li>
        </ul>
    </div>

    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>
