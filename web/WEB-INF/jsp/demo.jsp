<%--
  Created by IntelliJ IDEA.
  User: JY
  Date: 2018/12/16
  Time: 22:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>wangEditor demo</title>
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<div id="editor">
    <p>欢迎使用 <b>wangEditor</b> 富文本编辑器</p>
</div>

<!-- 注意， 只需要引用 JS，无需引用任何 CSS ！！！-->
<script type="text/javascript" src="js/wangEditor.min.js"></script>
<script type="text/javascript">
    var E = window.wangEditor
    var editor = new E('#editor')
    // 或者 var editor = new E( document.getElementById('editor') )
    editor.create();
    $("#btn1").click(function () {
        // 读取 html
        alert(editor.txt.html())
    });
</script>
<button id="btn1">获取html</button>
</body>
</html>