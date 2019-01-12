<%--
  Created by IntelliJ IDEA.
  User: YHY
  Date: 2018/12/22
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 供求 列表 -->

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>文化创意产业服务平台</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="../css/font.css">
    <link rel="stylesheet" href="../css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="../js/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="../js/xadmin.js"></script>
    <script type="text/javascript" src="../js/base.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<div class="x-nav">
      <span class="layui-breadcrumb">
        <a href="">首页</a>
        <a href="">供求管理</a>
        <a>
          <cite>供求列表</cite></a>
      </span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
</div>

<div class="x-body">
    <div class="layui-row">

        <div class="layui-form layui-col-md12 x-so">
            <input type="text" name="keyword"  placeholder="请输入搜索编号" autocomplete="off" class="layui-input">
            <div class="layui-input-inline">
                <select name="contrller" id="selectMethod">
                    <option value="MH">模糊</option>
                    <option>精确</option>
                </select>
            </div>
            <button id="manageSearch" class="layui-btn"  lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
        </div>
    </div>

    <xblock>
        <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>
        <span class="x-right" style="line-height:40px"><span id="listType"><c:out value="${listType}"/></span>共有数据：<c:out value = "${listNum}"/></span>
    </xblock>
    <table class="layui-table">
        <thead>
        <tr>
            <th>
                <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i class="layui-icon">&#xe605;</i></div>
            </th>
            <th class="id">编号</th>
            <th class="type">文章标题</th>
            <th class="startTime">开始时间</th>
            <th class="endTime">到期时间</th>
            <th class="hits">访问量</th>
            <th>审核状态</th>
            <th>操作</th>
        </tr>
        </thead>

        <!-- 列表开头 -->
        <tbody>
        <c:forEach items="${list}" var="per">
            <tr>
                <td>
                    <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='2'><i class="layui-icon">&#xe605;</i></div>
                </td>
                <td class="id">${per.first}</td>
                <td>${per.second}</td>
                <td>${per.third}</td>
                <td>${per.fourth}</td>
                <td>${per.fifth}</td>
                <td>${per.sixth}</td>
                <td class="td-status">
                    <span class="layui-btn layui-btn-normal layui-btn-mini deleteButton">删除</span>
                    <c:if test="${sessionScope.currentUser.type == '15'}">
                        <span class="layui-btn layui-btn-normal layui-btn-mini checkButton">审核</span>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
        <!-- 列表结尾 -->


    </table>
    <div class="page">
        <div>
            <a class="prev" href="">&lt;&lt;</a>
            <span class="current">current</span>
            <a class="next" href="">&gt;&gt;</a>
        </div>
    </div>

</div>

</body>

</html>