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
        .essayInfo{
            display: block;
        }
        .essayContain{
            width: 1200px;
            /*	height: auto;*/
            /*	background-color: red;*/
            padding: 20px;
            /*	white-space: nowrap;*/
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="headder.jsp"/>
    <div style="margin: 0 auto;margin-left:auto;width: 1200px;margin-top: 30px;">
        <div style="display: block;text-align: center;"><h2>${exhi.first}</h2></div>
        <div style="display: block;">
            <div class="essayInfo"  style="display: block;text-align: center;">
                <small>发布人:${exhi.second}</small>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                <small>发布时间：${exhi.third}</small>
            </div>
        </div>
    </div>
    <div class="row essayContain" style="margin: 0 auto;">
        <p>
            ${exhi.fourth}</p>
        <p>
            然而，时至今日，转型文创园的发展却并非尽如人意。红专厂文化创意产业园所在区域早在2013年已经确定被规划为金融中心，面临拆迁。消息一出，“红专厂”到底何去何从，引发社会各界强烈关注。“红专厂”全称为“广州红专厂文化创意产业园”，曾一度被认为是城市工业遗产更新的成功案例，然而时至今日，已建成8年之久的它面临要再次搬迁的窘境。以小窥大，这集中反映了城市工业遗产更新，特别是转型文创园产生的诸多问题。</p>
    </div>

    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>
