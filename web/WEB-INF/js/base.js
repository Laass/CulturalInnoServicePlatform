$(document).ready(function(){

    function search(temp){
        var s_url = "search.action";
        $("#lists").empty();

        var aurl = "";
        if($("#selectType option:selected").val() == "Exhibition"){
            aurl = "getExhibitionInfo?exhiId=";
        }
        else if($("#selectType option:selected").val() == "News") {
            aurl = "getNewsById?newsId=";
        }
        else if($("#selectType option:selected").val() == "SD"){
            aurl = "getSDInfo?sdId=?";
        }
        else if($("#selectType option:selected").val() == "PRODUCT"){
            aurl = "getProductById?productId=";
        }

        $.ajax({
            type : "POST",
            contentType : 'application/json;charset=UTF-8',
            url : s_url,
            data : JSON.stringify(temp),
            dataType : 'json',
            success : function(data){
                for (var i = 0; i < data.length; ++i) {
                    if (data[i].fifth == 1) {
                        var str = "<div class='listOuter'>" +
                            "                        <li>\n" +
                            "                            <a href='" + aurl + data[i].third +
                            "' style=\"color: black;\">\n" +
                            data[i].first +
                            "                                <div class=\"subTitle\"> " + data[i].second +
                            "</div>\n" +
                            "                            </a>\n" +
                            "                        </li>\n" +
                            "                    </div>";
                        $("#lists").append(str);
                    }
                }
            },
            error : function(){
                alert("error");
            }
        });
    }

    function manageSearch(temp) {
        var s_url = "../search.action";

        $.ajax({
            type : "POST",
            contentType : 'application/json;charset=UTF-8',
            url : s_url,
            data : JSON.stringify(temp),
            dataType : 'json',
            success : function(data){
                $("tbody").empty();
                for (var i = 0; i < data.length; ++i){
                    var str = "<tr>\n" +
                        "                    <td>\n" +
                        "                        <div class=\"layui-unselect layui-form-checkbox\" lay-skin=\"primary\" data-id='2'><i class=\"layui-icon\">&#xe605;</i></div>\n" +
                        "                    </td>\n" +
                        "                    <td class=\"id\">" +data[i].third + "</td>\n" +
                        "                    <td>" + data[i].first +
                        "</td>\n" +
                        "                    <td>"+data[i].second+"</td>\n" +
                        "                    <td>"+data[i].fourth+"</td>\n" +
                        "                    <td>"+data[i].fifth+"</td>\n" +
                        "                    <td class=\"td-manage\">\n" +
                        "                        <span class=\"layui-btn layui-btn-normal layui-btn-mini deleteButton\">删除</span>\n" +
                        "                    </td>\n" +
                        "                </tr>";
                    $("tbody").append(str);
                }
            },
            error : function(){
                alert("error");
            }
        });
    }

    function publishMessage(temp){
        $.ajax({
            type : "POST",
            contentType : 'application/json;charset=UTF-8',
            url : "addMessage.action",
            data : JSON.stringify(temp),
            dataType : 'json',
            success : function(data){
                alert(data.message);
                window.location.href = window.location.href;
            },
            error : function(){
                alert("error");
            }
        });
    }

    function deleteRecord(node, actionURL){
        var temp = {
            first : $(node).parent().parent().children(".id").text()
        }
        $.ajax({
            type : "POST",
            contentType : 'application/json;charset=UTF-8',
            url : actionURL,
            data : JSON.stringify(temp),
            dataType : 'json',
            success : function(data){
                alert(data.message);
                member_del(node);
            },
            error : function(){
                alert("error");
            }
        });
    }

    function passEssay(temp, actionURL){
        $.ajax({
            type : "POST",
            contentType : 'application/json;charset=UTF-8',
            url : actionURL,
            data : JSON.stringify(temp),
            dataType : 'json',
            success : function(data){
                alert(data.message);
                window.location.href = window.location.href;
            },
            error : function(){
                alert("error");
            }
        });
    }

    function enshrineEssay(temp){
        $.ajax({
            type : "POST",
            contentType : 'application/json;charset=UTF-8',
            url : 'addToCollection.action',
            data : JSON.stringify(temp),
            dataType : 'json',
            success : function(data){
                alert(data.message);
            },
            error : function() {
                alert("error");
            }
        });
    }

    $("#search").click(function () {
        var temp = {
            first : $("#selectType option:selected").val(),
            second : $("input[name='keyword']").val(),
            third : $("#selectMethod option:selected").val()
        }
        search(temp);
    });

    $("#manageSearch").click(function () {
        var temp = {
            first : $("#listType").text(),
            second : $("input[name='keyword']").val(),
            third : $("#selectMethod option:selected").val(),
            fourth : "Manage"
        }
        manageSearch(temp);
    });

    $("#publishMessage").click(function () {
        var temp = {
            originId : $("#oid").text(),
            content : $("#messageContent").val(),
            originType : $("#essayType").text()
        }
        publishMessage(temp);
    });

    //删除按钮点击方法
    $(".deleteButton").click(function (){
        switch ($("#listType").text()) {
            case "News":
                deleteRecord(this, "deleteNew.action");
                break;
            case "Exhibition":
                deleteRecord(this, "deleteExhibition.action");
                break;
            case "SD":
                deleteRecord(this, "deleteSD.action");
                break;
            case "PRODUCT":
                deleteRecord(this, "deleteProduct.action");
                break;
            case "Message" :
                deleteRecord(this, "../delMessage.action");
                break;
        }
    });

    //审核按钮点击方法
    $(".checkButton").click(function (){
        switch ($("#listType").text()) {
            case "News":
                var temp = {
                    newsId : $(this).parent().parent().children(".id").text()
                }
                passEssay(temp, "../passNew.action");
                break;
            case "Exhibition":
                var temp = {
                    exId : $(this).parent().parent().children(".id").text()
                }
                passEssay(temp, "../passExhibition.action");
                break;
            case "SD":
                var temp = {
                    sdId : $(this).parent().parent().children(".id").text()
                }
                passEssay(temp, "../passSD.action");
                break;
            case "PRODUCT":
                var temp = {
                    proId : $(this).parent().parent().children(".id").text()
                }
                passEssay(temp, "../passProduct.action");
                break;
        }
    });

    $(".updateMessage").click(function () {
        var temp = {
            mesId : $(this).parent().parent().children(".id").text(),
            content : $(this).parent().parent().children(".messagecontent").children("textarea").val()
        }
        $.ajax({
            type : "POST",
            contentType : 'application/json;charset=UTF-8',
            url : "../updateMessage.action",
            data : JSON.stringify(temp),
            dataType : 'json',
            success : function(data){
                alert(data.message);
                window.location.href = window.location.href;
            },
            error : function(){
                alert("error");
            }
        });
    });

    $(".editBtn").click(function ()
    {
        var essayId;
        var essayType;
        switch($("#listType").text())
        {
            case "News":
                essayId=$(this).parent().parent().children(".id").text();
                essayType="News";
                break;
            case "Exhibition":
                essayId=$(this).parent().parent().children(".id").text();
                essayType="Exh";
                break;
            case "SD":
                essayId = $(this).parent().parent().children(".id").text();
                essayType="SD";
                break;
            case "PRODUCT":
                essayId = $(this).parent().parent().children(".id").text();
                essayType="Product";
                break;
        }
        window.location.href="goToEdit?essayIdAndType="+essayId+" "+essayType;

    });

    $("#enshrineSD").click(function () {
        var temp = {
            originId : $("#oid").text(),
            originType : "supplydemand"
        }
        enshrineEssay(temp);
    });

    $("#enshrineNews").click(function () {
        var temp = {
            originId : $("#oid").text(),
            originType : "news"
        }
        enshrineEssay(temp);
    });

    $("#enshrineExh").click(function () {
        var temp = {
            originId : $("#oid").text(),
            originType : "exhibition"
        }
        enshrineEssay(temp);
    });

    layui.use('laydate', function(){
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#start' //指定元素
        });

        //执行一个laydate实例
        laydate.render({
            elem: '#end' //指定元素
        });
    });

    /*用户-删除*/
    function member_del(obj){
        $(obj).parents("tr").remove();
    }



});