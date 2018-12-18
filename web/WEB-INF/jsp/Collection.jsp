<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%--
  Created by IntelliJ IDEA.
  User: user0
  Date: 2018/12/17
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
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


    <style type="text/css">
        .log{
            height: 100px;

            overflow: hidden;
        }
        .search{
            width: 200px;
            height: 20px;
            float: right;
            margin-right: 80px;
        }
        .newsList{
            width: 300px;
            height: 400px;
            margin-left: 10px;
            display: inline-block;
            overflow: hidden;
        }
        .MidNewsList{
            width: 355px;
            height: 500px;
            margin-right: 17px;
            display: inline-block;
            overflow: hidden;
        }
        .news{
            width: 100%;
            height: 80px;
            margin-top: 10px;
            /*	background-color: grey;*/
            display: block;
            overflow: hidden;
        }
        .newTitle{
            width: 100%;
            color: #1e50ae;
            font-size: 13px;
            height: 15px;
        }
        .MidNewsListTitle{
            width: 100%;
            height: 90px;
            font-size: 28px;
            font-weight: bold;
            color: white;
            background-color: #222222;
            text-align: center;
            padding-top: 15px;
        }
        .newsInfo{
            width: 100%;
            height: 25px;
        }
        ul.MidNewsListContain{
            margin-top: 10px;
            padding-left:8px;
        }
        ul.MidNewsListContain > li{
            width: 100%;
            line-height: 40px;
            list-style: none;
            border-bottom: 1px dashed #eee;
        }
        ul.MidNewsListContain > li > a{
            color: black;
        }
        .product{
            width: 200px;
            height: 300px;
            margin-right: 10px;
            margin-top: 30px;
            display: inline-block;
            overflow: hidden;
        }
        .productImg{
            width: 100%;
            height: 150px;
        }
        .productImg > img{
            width: 100%;
            height: 150px;
        }
        .productTitle{
            margin-top: 8px;
            margin-bottom: 8px;
        }
        .productInfo{
            font-size: 13px;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="headder.jsp"/>

    <!-- 当前栏目名字 -->
    <div class="row">
        <h3 style="margin-left: 90px;margin-top: 30px;">收藏夹</h3>
    </div>

    <div class="row" style="width: 1200px;margin:auto;margin-top: 30px;">
        <div class="bd-example bd-example-tabs">
            <nav>
                <div class="nav nav-tabs" id="nav-tab" role="tablist">
                    <a class="nav-item nav-link active show" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab" aria-controls="nav-home" aria-selected="true">产品</a>
                    <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" href="#nav-profile" role="tab" aria-controls="nav-profile" aria-selected="false">展会</a>
                    <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" href="#nav-contact" role="tab" aria-controls="nav-contact" aria-selected="false">供求</a>
                    <a class="nav-item nav-link" id="nav-News-tab" data-toggle="tab" href="#nav-News" role="tab" aria-controls="nav-News" aria-selected="false">资讯</a>
                </div>
            </nav>

            <div class="tab-content" id="nav-tabContent">

                <div class="tab-pane fade active show" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
                   <%-- <c:forEach items="${productInfoList}" var="productInfo">
                    <div class="card" style="width: 1200px;">
                        <div class="card-body" style="height: 120px;padding: 10px;padding-left: 30px;">
                            <div class="card-title lead">${productInfo.first}</div>
                            <p class="card-text">${productInfo.second}${productInfo.thrid}</p>
                            <a href="getProductById?productId=${productInfo.fourth}" class="card-link" style="float: right;">查看详情</a>
                        </div>
                    </div>
                    </c:forEach>--%>
                       <c:forEach items="${productInfoList}" var="exhiInfo">
                           <div class="card" style="width: 1200px;">
                               <div class="card-body" style="height: 120px;padding: 10px;padding-left: 30px;">
                                   <div class="card-title lead">${exhiInfo.first}</div>
                                   <p class="card-text">${exhiInfo.second}${exhiInfo.third}</p>
                                   <a href="getProductById?productId=${exhiInfo.fourth}" class="card-link" style="float: right;">查看详情</a>
                               </div>
                           </div>
                       </c:forEach>
                </div>


                <div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
                    <c:forEach items="${exhiInfoList}" var="exhiInfo">
                    <div class="card" style="width: 1200px;">
                        <div class="card-body" style="height: 120px;padding: 10px;padding-left: 30px;">
                            <div class="card-title lead">${exhiInfo.first}</div>
                            <p class="card-text">${exhiInfo.second}${exhiInfo.third}</p>
                            <a href="getExhibitionInfo?exhiId=${exhiInfo.fourth}" class="card-link" style="float: right;">查看详情</a>
                        </div>
                    </div>
                    </c:forEach>
                </div>


                <div class="tab-pane fade" id="nav-contact" role="tabpanel" aria-labelledby="nav-contact-tab">
                    <c:forEach items="${supplyInfoList}" var="supplyInfo">
                    <div class="card" style="width: 1200px;">
                        <div class="card-body" style="height: 120px;padding: 10px;padding-left: 30px;">
                            <div class="card-title lead">${supplyInfo.first}${supplyInfo.fourth}</div>
                            <p class="card-text">${supplyInfo.second}${supplyInfo.third}</p>
                            <a href="/getSDInfo?sdId=${supplyInfo.fifth}" class="card-link" style="float: right;">查看详情</a>
                        </div>
                    </div>
                    </c:forEach>
                </div>

                <div class="tab-pane fade" id="nav-News" role="tabpanel" aria-labelledby="nav-News-tab">
                    <c:forEach items="${newsInfoList}" var="newsInfo">
                    <div class="card" style="width: 1200px;">
                        <div class="card-body" style="height: 120px;padding: 10px;padding-left: 30px;">
                            <div class="card-title lead">${newsInfo.first}</div>
                            <p class="card-text">${newsInfo.second}${newsInfo.third}</p>
                            <a href="/getNewsById?newsId=${newsInfo.fourth}" class="card-link" style="float: right;">查看详情</a>
                        </div>
                    </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<div class="row" style="justify-content: center;margin-top: 20px;">
    <div id="pages" style="">
        <ul class="pagination pagination-lg">
            <li class="page-item"><a class="page-link" href="#">Previous</a></li>
            <li class="page-item"><a class="page-link" href="#">Next</a></li>
        </ul>
    </div>
</div>

<jsp:include page="footer.jsp"/>
</div>
</body>
</html>