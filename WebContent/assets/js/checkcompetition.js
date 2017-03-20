$("#comcontent").focusout(function(){
    var InpValue = $("#comcontent").val();
    var Inp = $.trim(InpValue).length;
    console.log(Inp);
    console.log(InpValue);
    
    if(Inp>2147483647){
        alert("请您重新输入2147483647字以内的新闻发布者");
        $("#comcontent").attr("value","");
    }
});

$("#issuer").focusout(function(){
    var InpValue = $("#issuer").val();
    var Inp = $.trim(InpValue).length;
    console.log(Inp);
    console.log(InpValue);
    
    if(Inp>100){
        alert("请您重新输入100字以内的新闻发布者");
        $("#issuer").attr("value","");
    }
});

$("#holdingunit").focusout(function(){
    var InpValue = $("#holdingunit").val();
    var Inp = $.trim(InpValue).length;
    console.log(Inp);
    console.log(InpValue);
    
    if(Inp>200){
        alert("请您重新输入200字以内的新闻发布者");
        $("#holdingunit").attr("value","");
    }
});

$("#comname").focusout(function(){
    var InpValue = $("#comname").val();
    var Inp = $.trim(InpValue).length;
    console.log(Inp);
    console.log(InpValue);
    
    if(Inp>200){
        alert("请您重新输入200字以内的新闻发布者");
        $("#comname").attr("value","");
    }
});