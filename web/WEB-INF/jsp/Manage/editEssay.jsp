<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user0
  Date: 2018/12/17
  Time: 9:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文章添加</title>
    <!-- 新 Bootstrap4 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/css/bootstrap.min.css">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <!-- popper.min.js 用于弹窗、提示、下拉菜单 -->
    <script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
    <!-- 最新的 Bootstrap4 核心 JavaScript 文件 -->
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="css/bootstrap-datetimepicker.css">
    <script type="text/javascript" src="../js/bootstrap-datetimepicker.js"></script>
    <script type="text/javascript" src="../js/wangEditor.js"></script>
    <script>
        $("document").ready(function ()
        {
            $(".sdRadio").css("display","none");
            $(".chooseTime").css("display","none");
            $(".essayType").change(function ()
            {
                if($(".essayType:checked").val()=="sd")
                {
                    $(".sdRadio").css("display","inline");
                    $(".chooseTime").css("display","block")
                }
                else
                {
                    $(".sdRadio").css("display","none");
                    $(".chooseTime").css("display","none");
                }
            });
            $("#submit").click(function ()
            {
                $.post("addEssay",
                    {
                        essayTitle:$("#essayTitle").val(),
                        essayContent:editor.txt.html(),
                        essayType:$(".essayType:checked").val(),
                        sdType:$(".sdType:checked").val(),
                        startTime:$("#startTime").val(),
                        endTime:$("#endTime").val()
                    },
                    function (data,status)
                    {
                        alert("数据："+data+"\n状态："+status);
                    }
                );
            });
            $("#edit").click(function ()
            {
                var essayType=$("#essayType").text()
                $.post("editEssay",
                    {
                        essayTitle:$("#essayTitle").val(),
                        essayContent:editor.txt.html(),
                        essayId:$("#essayId").text(),
                        essayType:essayType
                    },
                    function (data, status)
                    {
                        alert(status);
                        window.location.href="toList?listName="+essayType;
                    });
            });
        });
    </script>
    <style>
        .betterFont
        {
            font-family: arial,pingfang sc,stheiti,microsoft yahei,sans-serif;
        }
    </style>
</head>
<body>
<div class="form-group betterFont">
    <label for="essayTitle" style="font-size: 20px;font-weight: bold;">文章标题</label>
    <c:if test="${requestScope.essay!=null}">
        <input type="text" class="form-control" id="essayTitle" value="${requestScope.essay.first}" style="width: 50%">
    </c:if>
    <c:if test="${requestScope.essay==null}">
        <input type="text" class="form-control" id="essayTitle" style="width: 50%">
    </c:if>
</div>
<div id="editor">

    <c:if test="${requestScope.essay==null}">
        <p>欢迎使用<b>wangEditor</b>富文本编辑器</p>
    </c:if>
</div>
<script type="text/javascript">
    var E=window.wangEditor;
    var editor=new E('#editor');
    editor.customConfig.menus=[
        'head',
        'bold',
        'fontSize',
        'fontName',
        'italic',
        'underline',
        'strikeThrough',
        'foreColor',
        'backColor',
        'link',
        'list',
        'justify',
        'quote',
        'emoticon',
        'image',
        'table',
        'undo',
        'redo'
    ];
    editor.customConfig.uploadImgShowBase64="true";
    editor.customConfig.showLinkImg=false;
    editor.create();
    <c:if test="${requestScope.essay!=null}">
    editor.txt.html('${requestScope.essay.second}');
    </c:if>
</script>
<c:if test="${requestScope.essay==null}">
<div class="radio betterFont">
    <span>请选择文章类型:</span>
    <label class="radio-inline"><input type="radio" class="essayType" name="essayType" value="exhi" checked>展会信息</label>
    <label class="radio-inline"><input type="radio" class="essayType" name="essayType" value="news">资讯</label>
    <label class="radio-inline"><input type="radio" class="essayType" name="essayType" value="sd">供求信息</label>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <span class="sdRadio">请选择供求类型:</span>
    <label class="radio-inline sdRadio"><input type="radio" class="sdType" name="sdType" value="supply" checked>供给</label>
    <label class="radio-inline sdRadio"><input type="radio" class="sdType" name="sdType" value="demand" checked>需求</label>
</div>
<div class="form-group betterFont chooseTime">
    <label for="startTime">起始时间</label>
    <input type="text" class="form-control" id="startTime" style="width: 170px;">
    <label for="endTime">截止时间</label>
    <input type="text" class="form-control" id="endTime" style="width: 170px;">
</div>
<script>
    $(function () {
        $('#startTime').datetimepicker({
            format: 'yyyy-mm-dd hh:ii',
            todayHighlight:true,
            initialDate:new Date(),
            keyboardNavigation:true,
            todayBtn:true
        });
        $('#endTime').datetimepicker({
            format: 'yyyy-mm-dd hh:ii',
            todayHighlight:true,
            initialDate:new Date(),
            keyboardNavigation:true,
            todayBtn:true
        });
    });

</script>
</c:if>
<c:if test="${requestScope.essay!=null}">
    <span id="essayId" hidden>${requestScope.essay.third}</span>
    <span id="essayType" hidden>${requestScope.essayType}</span>
    <button type="button" class="btn btn-primary" id="edit">提交</button>
</c:if>
<c:if test="${requestScope.essay==null}">
<button type="button" class="btn btn-primary" id="submit">提交</button>
</c:if>
</body>
</html>
