            var pageSize = 10, curPage = 0;
        	//定义总页数
        	var pageNum;
        	
        	//alert("执行");
        	//页面加载/刷新时  执行的ajax方法
        	$(function(){
        		$.ajax({
        			  type:"post",
        			  url:"comPaging.action",
        			  //只传递当前页和每页行数
        			  data:{
        				  "pageSize":10,
        				  "curPage":0
        			  },
        			  dataType:"json",
        			  success:function(data){
        				  $("#myTable .tr").remove();
        				  //alert(data);
        				  console.log(data);
        				  var totalRecord = data.totalRecord;
        				  pageNum = totalRecord%pageSize==0?(totalRecord/pageSize):Math.floor(totalRecord/pageSize)+1;
        				  
        				  var json = data.list;
        				  for (var i=0;i<json.length;i++) {
        						$("#myTable").append($("<tr class='tr'><td>"+json[i].comName+"</td><td>"+json[i].publishTime+"</td><td><a href='managecheck.action?competition.comId="+json[i].comId+"'"+" class='btn-check'><i class='fa fa-search-plus'></i>&nbsp;&nbsp;&nbsp;详情</a></td><td><a href='modify.action?competition.comId="+json[i].comId+"'"+" class='btn-modify'><i class='fa fa-edit'></i>&nbsp;&nbsp;&nbsp;修改</a></td><td><a onclick='return del()' href='delete.action?competition.comId="+json[i].comId+"'"+" class='btn-delete'><i class='fa fa-trash-o'></i>&nbsp;&nbsp;&nbsp;删除</a></td></tr>"));
        				  }
        			  },
        			  error:function(XMLHttpRequest, textStatus, errorThrown){
        				  alert("error");                
        				} 
        		});
        	})
        	//点击查询按钮发起的查询，与ajax()方法不同在于点击查询按钮时要计算总页数，每次点击换页都计算总页数效率不高
        	//缺点代码复用率低
        	//改进方法：将ajax执行成功的方法抽取出来，分别来写
        	$("#submit").on("click",function(){
        		//中间加空格------表示子类
        		var comname = $("#comName").val();
        		var comtype = $("#radio3:checked").val();
        		var comscale = $("#radio1:checked").val();
        		var comflag = $("#radio2:checked").val();
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
        				  //alert("OK");
        				  $("#myTable .tr").remove();
        				  var json = data.list;
        				  var totalRecord = data.totalRecord;
        				  pageNum = totalRecord%pageSize==0?(totalRecord/pageSize):Math.floor(totalRecord/pageSize)+1;
        				  for (var i=0;i<json.length;i++) {
      						$("#myTable").append($("<tr class='tr'><td>"+json[i].comName+"</td><td>"+json[i].publishTime+"</td><td><a href='managecheck.action?competition.comId="+json[i].comId+"'"+" class='btn-check'><i class='fa fa-search-plus'></i>&nbsp;&nbsp;&nbsp;详情</a></td><td><a href='modify.action?competition.comId="+json[i].comId+"'"+" class='btn-modify'><i class='fa fa-edit'></i>&nbsp;&nbsp;&nbsp;修改</a></td><td><a onclick='return del()' href='delete.action?competition.comId="+json[i].comId+"'"+" class='btn-delete'><i class='fa fa-trash-o'></i>&nbsp;&nbsp;&nbsp;删除</a></td></tr>"));
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
        		//alert("curPage:"+curPage+"pageNum:"+pageNum)
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
        				  var json = data.list;
        				  $("#myTable .tr").remove();
        				  for (var i=0;i<json.length;i++) {
      						$("#myTable").append($("<tr class='tr'><td>"+json[i].comName+"</td><td>"+json[i].publishTime+"</td><td><a href='managecheck.action?competition.comId="+json[i].comId+"'"+" class='btn-check'><i class='fa fa-search-plus'></i>&nbsp;&nbsp;&nbsp;详情</a></td><td><a href='modify.action?competition.comId="+json[i].comId+"'"+" class='btn-modify'><i class='fa fa-edit'></i>&nbsp;&nbsp;&nbsp;修改</a></td><td><a onclick='return del()' href='delete.action?competition.comId="+json[i].comId+"'"+" class='btn-delete'><i class='fa fa-trash-o'></i>&nbsp;&nbsp;&nbsp;删除</a></td></tr>"));
      				  }
        			  },
        			  error:function(XMLHttpRequest, textStatus, errorThrown){
        				  alert("error");                
        				} 
        		  });
        	}
