function CheckStuMail(){
	var mail=document.getElementById("studentEmail").value;
	CheckMail(mail);
}
function checkStuPhone(){
	var phone=document.getElementById("studentPhone").value;
	CheckPhone(phone);
}
//验证邮箱格式
function CheckMail(mail) {
	if(mail==""||mail==null){
		alert("邮箱不能为空");
	}
	///^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/
	///^[A-Za-zd]+([-_.][A-Za-zd]+)*@([A-Za-zd]+[-.])+[A-Za-zd]{2,5}$/
	 var filter  = /^[A-Za-zd]+([-_.][A-Za-zd]+)*@([A-Za-zd]+[-.])+[A-Za-zd]{2,5}$/;
	 if (filter.test(mail)) 
		return true;
	 else {
	 	alert('您的电子邮件格式不正确');
	 	return false;
	 	}
	}
//验证手机格式
function CheckPhone(phone){ 
    var filter = /^1[34578]\d{9}$/;
    if (filter.test(phone)) 
		return true;
	 else {
	 	alert('您的手机格式不正确');
	 	return false;
	 	} 
} 

/*//更换验证码
$("#changeCode").click(function(){
	$.ajax({
		url:"createCode.action",
		data:"{}",
		type:"post",
		success:function(data){
			document.getElementById("code").src=data;
//			$("#code").attr("src",data);
		},
		error:function(){
		}
	});
});*/

//显示学生信息
$("#StuInfocheck").click(function(){
	var name=1;
	$.ajax({
		 url: 'showStuInfo.action',  
         type: 'post',  
         dataType: 'json',  
         data:"{}",
         success:function(result){
        	 //result中为n个对象，proName为第n个对象(学生)
        	 //name取出对象属性，student[name]取出属性值
        	 for(var proName in result){
        		 var student=result[proName];
        		 for(var name in student){
        			var valueStu=student[name];
//	        			$("#studnetName").attr("value",student[studnetName]);
        			if(document.getElementById(name)!=null){
        				document.getElementById(name).value=valueStu;
        			}
        		 }

        	 }
         },
         error:function(XMLHttpRequest,textStatus, errorThrown){
        	 alert(XMLHttpRequest.status);//200正常响应
             alert(XMLHttpRequest.readyState);//4处理状态正常接收
             alert(XMLHttpRequest.responseText);//返回响应文本
             alert(textStatus);
         }
    });
});

//修改学生密码(struts)
function stuModiPass(){
	var stuOldPassword=document.getElementById("stuOldPassword").value;
	var stuNewPassword=document.getElementById("stuNewPassword").value;
	var stuConfirmPassword=document.getElementById("stuConfirmPassword").value;
	if(stuOldPassword==null || stuOldPassword=="" || stuNewPassword==null || stuNewPassword==""|| stuConfirmPassword==null || stuConfirmPassword==""){
		alert("密码不能为空");
		return false;
	}
	if(stuNewPassword!=stuConfirmPassword){
		alert("新密码与确认密码不匹配");
		return false;
	}
	//向StuPasswordcheck发送请求
	$.ajax({
		 url: 'StuPasswordcheck.action',  
         type: 'post',  
         data:{"oldPass":stuOldPassword,"newPass":stuNewPassword,"confirmPass":stuConfirmPassword},  
         cache: false,  
         //beforeSend: LoadFunction, //加载执行方法    
         error: erryFunction,  //错误执行方法    
         success: succFunction //成功执行方法 	         	         
	})
	function erryFunction(XMLHttpRequest,textStatus, errorThrown) {  
        alert("系统异常，修改密码失败");  
        alert(XMLHttpRequest.status);
        alert(XMLHttpRequest.readyState);  
        alert(textStatus);  
    } 
	function succFunction(tt){
		alert(tt);
	}	
}
//****************************注册**************************
var IdCheck=false;
var PassCheck=false;
var ConPassCheck=false;
var CodeCheck=false;
//alert(IdCheck+""+PassCheck+" "+ConPassCheck+" "+CodeCheck);
//$("#registButton").attr("disabled",false);
function checkAllTrue(a,b,c,d,id){
	if(a==true&&b==true&&c==true&&d==true){
		$("#"+id).attr("disabled",false);
		return true;
	}else{
		$("#"+id).attr("disabled",true);
		return false;
	}
}

//判断学生账号是否存在以及非空onkeyUp事件
function stuIdCheck(){
	var stuId=$("#stuId").val();
	if(stuId==""|| stuId==null){
		//alert("用户名不能为空");
		$("#spanId").html("用户名不能为空");
		return false;
	}
	$.ajax({
		url:"stuIdCheck.action",
		data:{"stuId":stuId},
		type:"get",
		success:function(data){
			if(data=="exit"){
				//alert("该用户名已经存在！");
				$("#spanId").html("用户名已经存在");
				return false;
			}else if(data=="noexit"){
				$("#spanId").html("ok");
				IdCheck=true;
				var q=checkAllTrue(IdCheck,PassCheck,ConPassCheck,CodeCheck,"registButton");
				//alert(q);
				return true;
			}		
		},
		error:function(){
			alert("系统问题，请稍后再试！");
			return false;
		}
	});
}

//var password=document.getElementById("stuPassword").value;//写在外面，取不到值，，并不懂
//学生登录密码非空判断
function stuPassCheck(){
	password=$("#stuPassword").val();
	//alert(password);	
	if(password==null|| password==""){
		//alert("密码不能为空");
		$("#spanPass").html("密码不能为空");
		return false;
	}else{
		var length=password.length;
		if(length<6){
			$("#spanPass").html("密码至少6位");
			return false;
		}else{
			$("#spanPass").html("ok");
		}
	}
	PassCheck=true;
	var w=checkAllTrue(IdCheck,PassCheck,ConPassCheck,CodeCheck,"registButton");
	//alert(w);
	return true;
}

function checkConfirmPass(){
	var stuConfirmPassword=document.getElementById("stuConfirmPassword").value;
	//var stuConfirmPassword=$("#stuConfirmPassword").val();
	//alert("密码"+password+"确认密码："+stuConfirmPassword);
	if(password==stuConfirmPassword){
		$("#spanConPass").html("ok");
		ConPassCheck=true;
		var e= checkAllTrue(IdCheck,PassCheck,ConPassCheck,CodeCheck,"registButton");
		//alert(e);
		return true;
	}else{
		$("#spanConPass").html("密码不一致");
		return false;
	}
}

//判断验证码是否正确
function codeCheck(){
	var rand=$("#rand").val();
	//alert(rand);
	$.ajax({
		url:"codeCheck.action",
		data:{"rand":rand},
		type:"get",
		success:function(data){
			if(data=="true"){
				document.getElementById("spanCode").innerHTML="验证码正确";
				CodeCheck=true;
				var r= checkAllTrue(IdCheck,PassCheck,ConPassCheck,CodeCheck,"registButton");
				//alert(r);
				return true;
			}else if(data=="false"){
				document.getElementById("spanCode").innerHTML="验证码错误";
				return false;
			}
		},
		error:function(){
			alert("系统问题，请稍后再试！");
			return false;
		}
	});
}


