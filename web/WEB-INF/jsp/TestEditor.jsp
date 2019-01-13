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
    <title>Test Editor</title>
</head>
<body>
<div id="editor">
    <p>欢迎使用<b>wangEditor</b>富文本编辑器</p>
</div>
<script type="text/javascript" src="js/wangEditor.js"></script>
<script type="text/javascript">
    var E=window.wangEditor
    var editor=new E('#editor')
    editor.create()
</script>
</body>
</html>
