$(document).ready(function(){

    function gopage(cur){

        var lidtcontain = $("#lists").children();//所有数据
        var maxlist = 12;
        var curpage = cur;
        var maxpage = 0;
        var num = $("#lists").children().length;

        if(num == 0)
            return;

        if((num / maxlist) > parseInt(num / maxlist))
            maxpage = parseInt(num / maxlist) + 1;
        else
            maxpage = parseInt(num / maxlist);

        if(maxpage == 0)
            maxpage = 1;

        var startrows = (curpage-1) * maxlist;
        var endrows = curpage * maxlist - 1;
        endrows = (endrows > num)? num : endrows;

        if((startrows  >= 0 || endrows <= num) && (curpage <= maxpage && curpage > 0))
            for (var i = 0; i < num; ++i){
                var irow = lidtcontain[i];
                if(i >= startrows && i<= endrows)
                    $(irow).removeClass("hideLi");
                else
                    $(irow).addClass("hideLi");
                console.log(num);
                $("#curPage").text(curpage);
            }
        else
            alert("到头!");

        /*var pageEnd = $("#pageEnd").text();*/

    }

    gopage(1);

    $("#curPage").text("1");

    $("#pre").click(function(){
        var curpage = $("#curPage").text();
        curpage = parseInt(curpage);
        console.log("当前页："+curpage);
        curpage -= 1;
        gopage(curpage)
    });

    $("#next").click(function(){
        var curpage = $("#curPage").text();
        curpage = parseInt(curpage);
        console.log("当前页："+curpage);
        curpage += 1;
        gopage(curpage)
    });

    $(".listOuter").mouseover(function(){
        $(this).css("background-color","#eeeeee");
    });

    $(".listOuter").mouseout(function(){
        $(this).css("background-color","white");
    });

});