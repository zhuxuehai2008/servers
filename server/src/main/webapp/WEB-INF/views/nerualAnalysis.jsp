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
	<input type ="text"  placeholder="序列化数量" value="4">
	测试序列化数量和隐含侧数量对结果准确性的影响
	<input type ="text"  placeholder="隐含层最大数量" value="20">
	<input type ="text" placeholder="序列化最大数量" value="20">
	<input type ="button" placeholder="准备数据" value="准备数据" id="prepareData">
	<script>
	$("#prepareData").click(function(){
		$.ajax({
	        type: "post",
	        dataType: "json",
	        url: "<%=request.getContextPath()%>/neural/prepareData",
	        data:{id:${id},columnId:i},
	        success : function(data) {
	        	list[i].data=data.result;
	        },
	        error: function(XMLHttpRequest, textStatus, errorThrown){
	        	alert(textStatus);
	        }
		});
	})
	</script>
</body>
</html>