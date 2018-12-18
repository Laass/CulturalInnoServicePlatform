<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <script src="js/base.js"></script>
    <link rel="stylesheet" href="css/base.css">

</head>
<body>
<div class="container-fluid">
    <jsp:include page="headder.jsp"/>
        <form class="navbar-form navbar-right" style="margin-top: 50px;">
            <div class="form-group">
                <div class="input-group">
                    <div class="input-group-addon">
                        <i class="glyphicon glyphicon-search"></i>
                    </div>
                    <input type="text" class="form-control" placeholder="搜索" name="keyword"/>
                    <select id="selectType">
                        <option value="Exhibition">展会</option>
                        <option value="News">资讯</option>
                        <option value="SD">供求</option>
                        <option value="PRODUCT">产品</option>
                    </select>
                    <select id="selectMethod">
                        <option value="MH">模糊查询</option>
                        <option value="ACU">精确查询</option>
                    </select>
                    <button id="search" type="button" class="btn btn-default" style="display: inline-block;">搜索</button>
                </div>
            </div>
        </form>
    <div class="row">
        <div style="width: 1100px;justify-content: center;margin:0 auto;margin-top: 80px;border: 1px solid #eee;">
            <ul id="lists">

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
