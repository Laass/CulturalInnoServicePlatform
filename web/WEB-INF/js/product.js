$(document).ready(function(){

    function purchasePro(temp){
        $.ajax({
            type : "POST",
            contentType : 'application/json;charset=UTF-8',
            url : 'purchaseProduct.action',
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

    function enshrinePro(temp){
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

    $("#purchase").click(function () {
        var temp = {
            second : $(".proId").text(),
            third : $("input[name=purchasenum]").val()
        }
        purchasePro(temp);
    });

    $("#enshrine").click(function () {
        var temp = {
            originId : $(".proId").text(),
            originType : "product"
        }
        enshrinePro(temp);
    });

    $("#purchaseNum").change(function () {
       $("#priceSum").text(parseInt($("#purchaseNum").val()) * parseInt($("#singlePrice").text()));
    });

});