            var pageSize = 6, curPage = 0;
        	//定义总页数
        	var pageNum;
        	
        	//页面加载/刷新时  执行的ajax方法
        	$(function(){
        		$.ajax({
        			  type:"get",
        			  url:"comPaging.action",
        			  //只传递当前页和每页行数
        			  data:{
        				  "pageSize":6,
        				  "curPage":0
        			  },
        			  dataType:"json",
        			  success:function(data){
        				  $("#myTable .tr").remove();
        				 // //alert("刚开始数据："+data);
        				  console.log(data);
        				  var totalRecord = data.totalRecord;
        				  pageNum = totalRecord%pageSize==0?(totalRecord/pageSize):Math.floor(totalRecord/pageSize)+1;
        				  var json = data.list;
        				  for (var i=0;i<json.length;i++) {
        					  if(json[i].type==0)
        						  {
        						  	comType="竞赛"
        						  }else{
        							  comType="大创"
        						  }
        					  if(json[i].flag==0){
        						  comFlag="未立项"
        					  }else{
        						  comFlag="已立项"
        					  }
        						$("#myTable").append($("<tr class='tr'><td>"+comType+"</td><td>"+json[i].comName+"</td><td>"+json[i].publishTime+"</td><td>"+comFlag+"</td><td><a href='check.action?competition.comId='"+json[i].comId+">"+"<input type='button' value='查看'></a>"+"</td><td><a href='addsubcompetition.jsp?comId="+json[i].comId+"><input type='button' value='报名'></a></td></tr>"));
        				  }
        			  },
        			  error:function(XMLHttpRequest, textStatus, errorThrown){
        				  alert("error");                
        				} 
        		});
        	})
        	
        	
        	
        	$("#submit").on("click",function(){
        		//alert("查询")
        		//中间加空格------表示子类
        		var comname = $("#comName").val();
        		var comtype = $("#comType :checked").val();
        		var comscale = $("#comScale :checked").val();
        		var comflag = $("#comFlag :checked").val();
        		//alert(comname+"  "+comtype+"  "+comscale+"  "+comflag);
        		$.ajax({
        			  type:"post",
        			  url:"comPaging.action",
        			  dataType:"json",
        			  data:{
        				  "pageSize":pageSize,
        				  "curPage":curPage,
        				  "comName":comname,
        				  "scale":comscale,
        				  "type":comtype,
        				  "flag":comflag},
        			  success:function(data){
        				  $("#myTable .tr").remove();
        				  //alert("查询数据："+data)
        				  console.log("模拟查询"+data);
        				  var json = data.list;
        				  var totalRecord = data.totalRecord;
        				  pageNum = totalRecord%pageSize==0?(totalRecord/pageSize):Math.floor(totalRecord/pageSize)+1;
        				  for (var i=0;i<json.length;i++) {
        					  if(json[i].type==0)
        						  {
        						  	comType="竞赛"
        						  }else{
        							  comType="大创"
        						  }
        					  if(json[i].flag==0){
        						  comFlag="未立项"
        					  }else{
        						  comFlag="已立项"
        					  }
        						$("#myTable").append($("<tr class='tr'><td>"+comType+"</td><td>"+json[i].comName+"</td><td>"+json[i].publishTime+"</td><td>"+comFlag+"</td><td><a href='check.action?competition.comId='"+json[i].comId+">"+"<input type='button' value='查看'></a>"+"</td><td><a href='addsubcompetition.jsp?comId="+json[i].comId+"><input type='button' value='报名'></a></td></tr>"));
        				  }
        			  },
        			  error:function(XMLHttpRequest, textStatus, errorThrown){
        				  alert("error");                
        				} 
        		  });
        	})

        	
        	//首页
        	$("#a1").click(function(){
        		if(curPage==0){
        			alert("已经是第一页")
        		}
        		curPage = 0;
        		
        		ajax();
        		}
        	);
        	//上一页
        	$("#a2").click(function(){
        		if(curPage <=0){
        			alert("已经是第一页");
        		}else{
        			curPage = curPage -1;
        		}
        		ajax();

        		}
        	);
        	//下一页
        	$("#a3").click(function(){
        		alert("curPage:"+curPage+"pageNum:"+pageNum)
        		if(curPage >=pageNum-1){
        			alert("已经是最后一页");
        		}else{
        			curPage = curPage +1;
        		}
        		ajax();

        		}
        	);
        	//尾页
        	$("#a4").click(function(){
        		if(curPage==pageNum-1){
        			alert("已经是最后一页")
        		}
        		curPage = pageNum-1;
        		ajax();
        		}
        	);
        	
        	//ajax()点击按钮(查询，上一页，下一页，首尾页需要执行ajax)
        	function ajax(){
        		
        		var comname = $("#comName").val();
        		var comtype = $("#comType :checked").val();
        		var comscale = $("#comScale :checked").val();
        		var comflag = $("#comFlag :checked").val();
        		//alert(comname+"  "+comtype+"  "+comscale+"  "+comflag);
        		$.ajax({
        			  type:"post",
        			  url:"comPaging.action",
        			  dataType:"json",
        			  data:{
        				  "pageSize":pageSize,
        				  "curPage":curPage,
        				  "comName":comname,
        				  "scale":comscale,
        				  "type":comtype,
        				  "flag":comflag},
        			  success:function(data){
        				  $("#myTable .tr").remove();
        				  //alert("上下页数据：："+data);
        				  var json = data.list;
        				  for (var i=0;i<json.length;i++) {
        					  if(json[i].type==0)
        						  {
        						  	comType="竞赛"
        						  }else{
        							  comType="大创"
        						  }
        					  if(json[i].flag==0){
        						  comFlag="未立项"
        					  }else{
        						  comFlag="已立项"
        					  }
        						$("#myTable").append($("<tr class='tr'><td>"+comType+"</td><td>"+json[i].comName+"</td><td>"+json[i].publishTime+"</td><td>"+comFlag+"</td><td><a href='check.action?competition.comId='"+json[i].comId+">"+"<input type='button' value='查看'></a>"+"</td><td><a href='addsubcompetition.jsp?comId="+json[i].comId+"><input type='button' value='报名'></a></td></tr>"));
        				  }
        			  },
        			  error:function(XMLHttpRequest, textStatus, errorThrown){
        				  //alert(XMLHttpRequest.status);//200正常响应
        		             //alert(XMLHttpRequest.readyState);//4处理状态正常接收
        		            // alert(XMLHttpRequest.responseText);//返回响应文本
        		             //alert(textStatus);
        				  alert("error");                
        				} 
        		  });
        	}
