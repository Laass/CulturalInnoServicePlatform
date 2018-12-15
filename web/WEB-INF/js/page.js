$(document).ready(function(){

    function gopage(cur){

        var lidtcontain = $("#lists").children("li");//所有数据 也就是所有ul下的所有li
        var maxlist = 6; //每页最多内容条数
        var curpage = cur;//当前页
        var maxpage;//所有内容可以填充的最多页面数
        var num = $("#lists").children("li").length;//内容条数

        if((num / maxlist) > parseInt(num / maxlist))//如果最后一个页面没有被填满
            maxpage = parseInt(num / maxlist) + 1;//页面数=被填满的页面数+1
        else
            maxpage = parseInt(num / maxlist);//页面数=被填满的页面数

        if(maxpage == null)
            maxpage = 1;

        var startrows = (curpage-1) * maxlist;//所有内容的第stratrows个是这个页面的第一条
        var endrows = curpage * maxlist - 1;//所有内容的第endrows个是这个页面的最后一条
        endrows = (endrows > num)? num : endrows;//当最后一个页面没有没填满时 将endrows设为最后一条内容

        if(startrows  >= 0 && endrows <= num && startrows<endrows)
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
            if(startrows>endrows)
                alert("没有更多！");
            else
                alert("到头!");

        /*var pageEnd = $("#pageEnd").text();*/

    }

    gopage(1);

    $("#curPage").text("1");

    $("#pre").click(function(){
        console.log("取下一页");
        var curpage = $("#curPage").text();
        curpage = parseInt(curpage);
        console.log("当前页："+curpage);
        curpage -= 1;
        gopage(curpage)
    });

    $("#next").click(function(){
        console.log("取上一页");
        var curpage = $("#curPage").text();
        curpage = parseInt(curpage);
        console.log("当前页："+curpage);
        curpage += 1;
        gopage(curpage)
    });

});