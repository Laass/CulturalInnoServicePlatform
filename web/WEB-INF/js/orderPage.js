$(document).ready(function ()
{
    function changePage(cur, node, curPageNode)
    {

        var lidtcontain = $(node).children(".card");//所有数据
        var maxlist = 2;
        var curpage = cur;
        var maxpage;
        var num = $(node).children(".card").length;

        if(num == 0)
            return;


        if((num / maxlist) > parseInt(num / maxlist))
            maxpage = parseInt(num / maxlist) + 1;
        else
            maxpage = parseInt(num / maxlist);

        if(parseInt(num)==0)
            maxpage = 1;

        var startrows = (curpage-1) * maxlist;
        var endrows = curpage * maxlist - 1;
        endrows = (endrows > num)? num : endrows;

        debugger;
        if((startrows  >= 0 && endrows <= num) && startrows<endrows)
            for (var i = 0; i < num; ++i){
                var irow = lidtcontain[i];
                if(i >= startrows && i<= endrows)
                    $(irow).css("display","flex");
                else
                    $(irow).css("display","none");
                console.log(num);
                $(curPageNode).text(curpage);
            }
        else
        if(startrows>endrows)
            alert("没有更多!");
        else
            alert("到头!");

        /*var pageEnd = $("#pageEnd").text();*/

    }

    changePage("1",$("#orderSection"),$("#curPage"));

    $("#pre").click(function ()
    {
        console.log("取上个页面");
        var curPage=parseInt($("#curPage").text());
        console.log("当前页："+curPage);
        curPage--;
        changePage(curPage,$("#orderSection"),$("#curPage"));
    });
    $("#next").click(function ()
    {
        console.log("取下个页面")
       var curPage=parseInt($("#curPage").text());
       console.log("当前页："+curPage);
       curPage++;
       changePage(curPage,$("#orderSection"),$("#curPage"));
    })
})