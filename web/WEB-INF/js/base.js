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
                for (var i = 0; i < data.length; ++i){
                    var str = "<div class='listOuter'>" +
                        "                        <li>\n" +
                        "                            <a href='"+aurl+data[i].third+
                        "' style=\"color: black;\">\n" +
                        data[i].first+
                        "                                <div class=\"subTitle\"> "+data[i].second+
                        "</div>\n" +
                        "                            </a>\n" +
                        "                        </li>\n" +
                        "                    </div>";
                    $("#lists").append(str);
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
            },
            error : function(){
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

    $("#publishMessage").click(function () {
        var temp = {
            originId : $("#oid").text(),
            content : $("#messageContent").val()
        }
        publishMessage(temp);
    });

});