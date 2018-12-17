$(document).ready(function(){

    function search(temp){
        var s_url = "search.action";
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
                        "                            <a href=\"#\" style=\"color: black;\">\n" +
                        data[i].theme +
                        "                                <div class=\"subTitle\"> "+data[i].establishTime+
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

    $("#search").click(function () {
        var temp = {
            first : $("#selectType option:selected").val(),
            second : $("input[name='keyword']").val()
        }
        search(temp);
    });

});