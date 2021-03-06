<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%  String path = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>career</title>
<script src="<%=path %>/static/js/jquery-1.9.1.min.js"></script>
<script src="<%=path %>/static/js/echart4.0/echarts.min.js"></script>
<script src="<%=path %>/static/js/echart4.0/echarts-gl.js"></script>
</head>
<body>
	<input type ="text" placeholder="隐含层数量" value="4">
	<input id ="serialNum" type ="text"  placeholder="序列化数量" value="4">
	测试序列化数量和隐含侧数量对结果准确性的影响
	隐含层最大数量(最小4)<input type ="text" id="maxHiddenNum" placeholder="隐含层最大数量" value="20">
	序列化最大数量(最小4)<input type ="text" id="maxSerialNum" placeholder="序列化最大数量" value="20">
	<input type ="button" placeholder="准备数据" value="准备数据" id="prepareData">
	<input type ="button" placeholder="分析网络参数" value="分析网络参数" id="analysisArgs">
	<div id="chartDom" class="chart"v style="width:600px;height:400px"></div>
	<script>
    var formdiv = document.getElementById("chartDom");
    var form_chart = echarts.init(formdiv);
    var hours = ['12a', '1a', '2a', '3a', '4a', '5a', '6a',
                 '7a', '8a', '9a','10a','11a',
                 '12p', '1p', '2p', '3p', '4p', '5p',
                 '6p', '7p', '8p', '9p', '10p', '11p'];
   var days = ['Saturday', 'Friday', 'Thursday',
                 'Wednesday', 'Tuesday', 'Monday', 'Sunday'];

   var data = [[0,0,5],[0,1,1],[0,2,0],[0,3,0],[0,4,0],[0,5,0],[0,6,0],[0,7,0],[0,8,0],[0,9,0],[0,10,0],[0,11,2],[0,12,4],[0,13,1],[0,14,1],[0,15,3],[0,16,4],[0,17,6],[0,18,4],[0,19,4],[0,20,3],[0,21,3],[0,22,2],[0,23,5],[1,0,7],[1,1,0],[1,2,0],[1,3,0],[1,4,0],[1,5,0],[1,6,0],[1,7,0],[1,8,0],[1,9,0],[1,10,5],[1,11,2],[1,12,2],[1,13,6],[1,14,9],[1,15,11],[1,16,6],[1,17,7],[1,18,8],[1,19,12],[1,20,5],[1,21,5],[1,22,7],[1,23,2],[2,0,1],[2,1,1],[2,2,0],[2,3,0],[2,4,0],[2,5,0],[2,6,0],[2,7,0],[2,8,0],[2,9,0],[2,10,3],[2,11,2],[2,12,1],[2,13,9],[2,14,8],[2,15,10],[2,16,6],[2,17,5],[2,18,5],[2,19,5],[2,20,7],[2,21,4],[2,22,2],[2,23,4],[3,0,7],[3,1,3],[3,2,0],[3,3,0],[3,4,0],[3,5,0],[3,6,0],[3,7,0],[3,8,1],[3,9,0],[3,10,5],[3,11,4],[3,12,7],[3,13,14],[3,14,13],[3,15,12],[3,16,9],[3,17,5],[3,18,5],[3,19,10],[3,20,6],[3,21,4],[3,22,4],[3,23,1],[4,0,1],[4,1,3],[4,2,0],[4,3,0],[4,4,0],[4,5,1],[4,6,0],[4,7,0],[4,8,0],[4,9,2],[4,10,4],[4,11,4],[4,12,2],[4,13,4],[4,14,4],[4,15,14],[4,16,12],[4,17,1],[4,18,8],[4,19,5],[4,20,3],[4,21,7],[4,22,3],[4,23,0],[5,0,2],[5,1,1],[5,2,0],[5,3,3],[5,4,0],[5,5,0],[5,6,0],[5,7,0],[5,8,2],[5,9,0],[5,10,4],[5,11,1],[5,12,5],[5,13,10],[5,14,5],[5,15,7],[5,16,11],[5,17,6],[5,18,0],[5,19,5],[5,20,3],[5,21,4],[5,22,2],[5,23,0],[6,0,1],[6,1,0],[6,2,0],[6,3,0],[6,4,0],[6,5,0],[6,6,0],[6,7,0],[6,8,0],[6,9,0],[6,10,1],[6,11,0],[6,12,2],[6,13,1],[6,14,3],[6,15,4],[6,16,0],[6,17,0],[6,18,0],[6,19,0],[6,20,1],[6,21,2],[6,22,2],[6,23,6]];
   function options(){return option = {tooltip: {},
             visualMap: {max: 4,
                 inRange: {
                     color: ['#313695', '#4575b4', '#74add1', '#abd9e9', '#e0f3f8', '#ffffbf', '#fee090', '#fdae61', '#f46d43', '#d73027', '#a50026']
                 }
             },
             xAxis3D: {type: 'category',
                 data: hours
             },
             yAxis3D: { type: 'category',
                 data: days
             },
             zAxis3D: {type: 'value'
             },
             grid3D: {boxWidth: 200,
                 boxDepth: 80,
                 viewControl: {// projection: 'orthographic'
                	 },
                 light: { main: {intensity: 1.2,shadow: true},
                     ambient: {intensity: 0.3}
                 }
             },
             series: [{type: 'bar3D',
	                   data: [],//data.map(function (item) { return {value: [item[1], item[0], item[2]],}}),
	                   shading: 'lambert',
	                   label: {textStyle: {fontSize: 16,borderWidth: 1}},
	                   emphasis: {label: {textStyle: {fontSize: 20,color: '#900'}},
	                 		      itemStyle: {color: '#900'}
	                   }
	               }]
         };
   }
   // form_chart.setOption(option);
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
		var width =0;
		window.result=[];
		var serialArr=[]
		var maxSerialNum = $("#maxSerialNum").val();
		var maxHiddenNum = $("#maxHiddenNum").val();
		for(var i=4;i<=maxSerialNum;i++){
			serialArr.push(i);
			(function(i,result){
				$.ajax({
			        type: "post",
			        dataType: "json",
			        url: "<%=request.getContextPath()%>/neural/analysisArgs/RecurrentNN",
			        data:{serialNum:i,maxhiddenNum:maxHiddenNum},
			        success : function(data) {
			        	if(data.status==200){
			        		console.log(result,data.result);
			        		result.concat();
			        		result.push.apply(result,data.result);
			        		console.log(result,"@@@@@!!!!",[].concat(data.result));
			        	}else{
			        		alert(data.msg);
			        	}
			        	console.log(data);
						width +=1/(maxSerialNum-3)*100;
						$("#bar").css({width:width+"%"});
			        },
			        error: function(XMLHttpRequest, textStatus, errorThrown){
			        	alert(textStatus);
			        }
				});
			})(i,result)
		}
			
							  function interval(){
									var hiddenArr=[];
									for(var i=0;i<=20;i++){
										hiddenArr.push(i)
									}
									var serialArr=[];
									for(var i=0;i<=20;i++){
										serialArr.push(i);
									}
									console.log("@11");
									if(width==100){
										window.clearInterval(window.intervals);
										window.option = options();
										console.log(result,result.map(function (item) {return {value: [item["serialNum"], item["hiddenNum"], item["value"]]}}));
										var r = result.map(function (item) {return {value: [item["serialNum"], item["hiddenNum"], item["value"]]}})
										//debugger;;
										option.series[0].data=r;
										option.xAxis3D.data= serialArr;
										option.yAxis3D.data= hiddenArr;
										console.log("@",option);
										console.log(JSON.stringify(window.option));
										form_chart.setOption(window.option);
									}
								}
							  
							  window.intervals = setInterval(interval,2000);
		//$("#barContainer").css({display:"none"});
	})
	</script>
	<div class="barContainer" id="barContainer" style="display:none"><div class="bar" id="bar"></div></div>
	<style>
	.barContainer{height:20px; border:1px solid #000;width:200px}
	.bar{width:0%;background:#09f;height:20px}
	.chart{
		width:600px;
		height:400px;
		margin: 0 auto;
	}
	</style>
</body>
</html>