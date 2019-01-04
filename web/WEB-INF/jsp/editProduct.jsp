<%--
  Created by IntelliJ IDEA.
  User: user0
  Date: 2018/12/27
  Time: 18:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑产品信息</title>
    <!-- 新 Bootstrap4 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/css/bootstrap.min.css">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <!-- popper.min.js 用于弹窗、提示、下拉菜单 -->
    <script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
    <!-- 最新的 Bootstrap4 核心 JavaScript 文件 -->
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/wangEditor.js"></script>
    <script>
        $("document").ready(function ()
        {
            $("#submitImages").attr("disabled","disabled");
            $("#submit").click(function ()
            {
                $.post("addProduct",
                    {
                        proName:$("#proName").val(),
                        price:$("#price").val(),
                        proType:$(".proType").val(),
                        info:editor.txt.html()
                    },
                    function (data,status)
                    {
                        alert("数据："+data+"\n状态："+status);
                    }
                );
                $("#submitImages").removeAttr("disabled");
            });
        })
    </script>
</head>
<body>
<div class="form-group betterFont">
    <label for="proName" style="font-size: 20px;font-weight: bold;">商品名称</label>
    <input type="text" class="form-control" id="proName" style="width: 50%">
    <label for="price" style="font-size: 20px;font-weight: bold;">价格</label>
    <input type="text" class="form-control" id="price" style="width: 50%">
</div>

<div class="radio betterFont">
    <span>请选择商品类型:</span>
    <label class="radio-inline"><input type="radio" class="proType" name="proType" value="calligraphy" checked>书法</label>
    <label class="radio-inline"><input type="radio" class="proType" name="proType" value="painting">绘画</label>
    <label class="radio-inline"><input type="radio" class="proType" name="proType" value="musicInstrument">乐器</label>
    <label class="radio-inline"><input type="radio" class="proType" name="proType" value="garment">服饰</label>
</div>

<div id="editor">
    <p>欢迎使用 <b>wangEditor</b> 富文本编辑器</p>
</div>
<script>
    var E=window.wangEditor;
    var editor=new E('#editor');
    //指定editor工具栏的内容
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
    editor.customConfig.showLinkImg=false;
    //wangEditor上传图片的配置文件
    // editor.customConfig.uploadImgServer ="uploadFileFromWang";
    // editor.customConfig.uploadFileName="image";
    // editor.customConfig.uploadImgMaxSize=5*1024*1024;
    // editor.customConfig.uploadImgMaxLength=5;
    // editor.customConfig.uploadImgTimeout=3*60*1000;//三分钟
    // editor.customConfig.uploadImgHooks={
    //     success:function (xhr, editor, result) {
    //         alert("图片上传成功");
    //         console.log(result);
    //     },
    //     fail:function (xhr, editor, result) {
    //         alert("failed");
    //     },
    //     error:function (xhr, editor, result) {
    //         alert("error")
    //     },
    //     customInsert:function (insertImg, result, editor) {
    //
    //     }
    // };
    editor.customConfig.uploadImgShowBase64="true";
    editor.create();
    // editor.$textElem.attr('contenteditable', false);
</script>

<button type="button" class="btn btn-primary" id="submit">提交</button>

<fieldset>
    <legend>图片上传</legend>
    <h6>只能上传每张5M以下的 PNG、JPG、GIF 格式的图片</h6>
    <form action="uploadFile" method="post" enctype="multipart/form-data">
        选择文件:<input type="file" name="files" multiple>
        <input type="submit" value="上传" id="submitImages">
    </form>
</fieldset>

</body>
</html>
