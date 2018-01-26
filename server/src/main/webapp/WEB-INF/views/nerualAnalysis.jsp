<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%  String path = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>career</title>
<script src="<%=path %>/static/js/jquery-1.9.1.min.js"></script>
</head>
<body>
	<input type ="text" placeholder="隐含层数量" value="4">
	<input id ="serialNum" type ="text"  placeholder="序列化数量" value="4">
	测试序列化数量和隐含侧数量对结果准确性的影响
	隐含层最大数量<input type ="text"  placeholder="隐含层最大数量" value="20">
	序列化最大数量<input type ="text" id="maxSerialNum" placeholder="序列化最大数量" value="20">
	<input type ="button" placeholder="准备数据" value="准备数据" id="prepareData">
	<input type ="button" placeholder="分析网络参数" value="分析网络参数" id="analysisArgs">
	<script>
	$("#prepareData").click(function(){
		$.ajax({
	        type: "post",
	        dataType: "json",
	        url: "<%=request.getContextPath()%>/neural/prepareData/RecurrentNN",
	        data:{id:1,columnId:1},
	        success : function(data) {
	        	console.log(data);
	        },
	        error: function(XMLHttpRequest, textStatus, errorThrown){
	        	alert(textStatus);
	        }
		});
	})
	$("#analysisArgs").click(function(){
		$("#barContainer").css({display:"block"});
		var maxSerialNum = $("#maxSerialNum").val();
		for(var i=4;i<=maxSerialNum;i++){
			(function(i){
				$.ajax({
			        type: "post",
			        dataType: "json",
			        url: "<%=request.getContextPath()%>/neural/analysisArgs/RecurrentNN",
			        data:{serialNum:4,maxhiddenNum:8},
			        success : function(data) {
			        	console.log(data);
						$("#barContainer").css({display:"none"});
			        },
			        error: function(XMLHttpRequest, textStatus, errorThrown){
			        	alert(textStatus);
			        }
				});
				$("#barContainer").css({width:(i-3)/(maxSerialNum-3)+"%"})
			})(i)
		}
	})
	</script>
	<div class="barContainer" id="barContainer" style="display:none"><div class="bar"></div></div>
	<style>
	.barContainer{height:50px; }
	.bar{width:10%;background:#09f}
	</style>
</body>
</html>