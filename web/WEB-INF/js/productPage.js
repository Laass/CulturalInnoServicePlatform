$(document).ready(function(){

    function gocalligraphypage(cur, node, curPageNode){

        var lidtcontain = $(node).children(".card");//所有数据
        var maxlist = 12;
        var curpage = cur;
        var maxpage;
        var num = $(node).children(".card").length;

        if(num==0)
            return;

        if((num / maxlist) > parseInt(num / maxlist))
            maxpage = parseInt(num / maxlist) + 1;
        else
            maxpage = parseInt(num / maxlist);

        if(num==0)
            maxpage = 1;

        var startrows = (curpage-1) * maxlist;
        var endrows = curpage * maxlist - 1;
        endrows = (endrows > num)? num : endrows;

        if((startrows  >= 0 && endrows <= num) && startrows<endrows)
            for (var i = 0; i < num; ++i){
                var irow = lidtcontain[i];
                if(i >= startrows && i<= endrows)
                    $(irow).css("display","inline-block");
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

    gocalligraphypage("1",$("#v-pills-calligraphy"),$("#curPage"));
   //ocalligraphypage("1",$("#orderLists"),$("#ordercurPage"));

    $("#pre").click(function(){
        var curpage = parseInt($("#curPage").text());
        console.log("当前页："+curpage);
        curpage -= 1;
        gocalligraphypage(curpage,$("#v-pills-calligraphy"),$("#curPage"));
    });

    $("#next").click(function(){
        var curpage = parseInt($("#curPage").text());
        console.log("当前页："+curpage);
        curpage += 1;
        gocalligraphypage(curpage,$("#v-pills-calligraphy"),$("#curPage"));
    });

    $("#Paintingpre").click(function(){
        var curpage = parseInt($("#PaintingcurPage").text());
        console.log("当前页："+curpage);
        curpage -= 1;
        gocalligraphypage(curpage,$("#v-pills-Painting"),$("#PaintingcurPage"));
    });

    $("#Paintingnext").click(function(){
        var curpage = parseInt($("#PaintingcurPage").text());
        console.log("当前页："+curpage);
        curpage += 1;
        gocalligraphypage(curpage,$("#v-pills-Painting"),$("#PaintingcurPage"));
    });

    $("#Instrumentpre").click(function(){
        var curpage = parseInt($("#InstrumentcurPage").text());
        console.log("当前页："+curpage);
        curpage -= 1;
        gocalligraphypage(curpage,$("#v-pills-Instrument"),$("#InstrumentcurPage"));
    });

    $("#Instrumentnext").click(function(){
        var curpage = parseInt($("#InstrumentcurPage").text());
        console.log("当前页："+curpage);
        curpage += 1;
        gocalligraphypage(curpage,$("#v-pills-Instrument"),$("#InstrumentcurPage"));
    });

    $("#Dresspre").click(function(){
        var curpage = parseInt($("#DresscurPage").text());
        console.log("当前页："+curpage);
        curpage -= 1;
        gocalligraphypage(curpage,$("#v-pills-Dress"),$("#DresscurPage"));
    });

    $("#Dressnext").click(function(){
        var curpage = parseInt($("#DresscurPage").text());
        console.log("当前页："+curpage);
        curpage += 1;
        gocalligraphypage(curpage,$("#v-pills-Dress"),$("#DresscurPage"));
    });

    $("#orderpre").click(function(){
        var curpage = parseInt($("#ordercurPage").text());
        console.log("当前页："+curpage);
        curpage -= 1;
        gocalligraphypage(curpage,$("#orderLists"),$("#ordercurPage"));
    });

    $("#ordernext").click(function(){
        var curpage = parseInt($("#ordercurPage").text());
        console.log("当前页："+curpage);
        curpage += 1;
        gocalligraphypage(curpage,$("#orderLists"),$("#ordercurPage"));
    });

    $(function () {
        $("#v-pills-tabContent").children(".tab-pane").each(function () {
            $(this).removeClass("active");
            $(this).removeClass("show");
        });
        $("#v-pills-tab").children(".nav-link").each(function () {
            $(this).removeClass("active");
            $(this).removeClass("show");
        })
        if ($(".curColumn").text() == "call"){
            $("#v-pills-calligraphy").addClass("show active");
            $("#v-pills-calligraphy-tab").addClass("show active");

        }
        else if ($(".curColumn").text() == "draw"){
            $("#v-pills-Painting").addClass("show active");
            $("#v-pills-Painting-tab").addClass("show active");
        }
        else if ($(".curColumn").text() == "ins"){
            $("#v-pills-Instrument").addClass("show active");
            $("#v-pills-Instrument-tab").addClass("show active");
        }
        else{
            $("#v-pills-Dress").addClass("show active");
            $("#v-pills-Dress-tab").addClass("show active");
        }
    })

});