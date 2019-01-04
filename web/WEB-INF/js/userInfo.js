$("document").ready(function ()
{
    var imgChanged=false;
    $("#submit").attr("disabled","disabled");
    $("#enableEdit").click(function ()
    {
        $("form input").removeAttr("readonly");
        $("#submit").removeAttr("disabled");
        $("#enableEdit").attr("disabled","disabled");
        return false;//禁用表单的提交
    });
    $("#portrait").click(function ()
    {
        debugger;
        console.log(imgChanged);
        $("#choosePortrait").click();
        console.log(imgChanged);
        $("#choosePortrait").change(function ()
        {
            console.log("in change");
            console.log(imgChanged);
            var objUrl=getObjectUrl(this.files[0]);
            if(objUrl)
                $("#portrait").attr("src",objUrl);
            imgChanged=true;
            console.log(imgChanged);
        });
        console.log(imgChanged);
    });
    $("#submit").click(function ()
    {
        var imgUrl=$("#choosePortrait").val();
        console.log("about to save img");
        if(imgChanged)
        {
            $.ajaxFileUpload({
                url: "savePortrait",
                fileElementId: "choosePortrait",
                dataType: 'json',
                contentType: "application/x-www-form-urlencoded; charset=utf-8",
                success: function (data) {
                    debugger;
                    //console.log(data.code);
                    console.log(data.msg);
                    console.log(data.picLoc);
                    alert(data.msg);
                }
            });
        }
        console.log("about to save info");
        $.post("editUserInfo",
            {
                nickName:$("#nickName").val(),
                realName:$("#realName").val(),
                intro:$("#intro").val(),
                email:$("#email").val(),
                address:$("#address").val(),
                qq:$("#qq").val(),
                tel:$("#tel").val()
            },
            function (data,status)
            {
                $("form input").attr("readonly","readonly");
                $("#submit").attr("disabled","disabled");
                $("#enableEdit").removeAttr("disabled");
                alert("返回信息："+data+"\n状态："+status);
            }
        );
    });
    function getObjectUrl(file)
    {
        var url=null;
        if(window.createObjectURL!=undefined) // basic
            url=window.createObjectURL(file);
        else if(window.URL!=undefined)// mozilla(firefox)
            url=window.URL.createObjectURL(file);
        else if(window.webkitURL!=undefined)// webkit or chrome
            url=window.webkitURL.createObjectURL(file);
        return url;
    }
});