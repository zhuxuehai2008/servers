(function($){
	var defaults={
			id:"#page",
			url:"/pageTest",
			targetId:"#pageTarget",
			name:"pageSetting",
			data:{},
			success:function(data){
				console.log(data);
			}
	}
	var pageSize=10;
	$.extend({
	    "page": function (index,options) {
	    	console.log(options);
	    	var option = $.extend(defaults,options||{})
	    	queryList(index,option)
	    	}
	});
	function queryList(index,option){
		var url=option.url;
		var targetId =option.targetId;
		var data =option.data;
		var id=option.id;
		$(targetId).html("");
		$.ajax({
			type: "post",
			dataType: "json",
			url: url,
			data:$.extend(data,{pageSize:pageSize,pageBegin:index*pageSize}),
			success : function(data) {
				console.log(data);
				if(data.status==200){
					option.success(data.result);
		        	pageCountAll = Math.ceil(data.result.totalCount/pageSize);
		        	console.log(pageCountAll==NaN);
		        	if(isNaN(pageCountAll)){alert("分页出错")}
		        	pageControll(pageCountAll,index,option);
	        	}else{
	        		alert(data.msg);
	        	} 
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				console.log(textStatus);
			}
		});
	}
	function pageControll(pageCountAll,index,option){
		var id=option.id;
		if(index<0){return false;}
		pageTemplate='<div class="pageButton" onclick="$.page(0,'+option.name+')">&lt;&lt;</div><div class="pageButton" onclick="$.page({$pre})">&lt;</div>{$tags}<div class="pageButton" onclick="$.page({$next})">&gt;</div><div class="pageButton" onclick="$.page({$maxNum})">&gt;&gt;</div>';
		var MaxTagNum=10;var between=5;
		var realTagNum=MaxTagNum;
		if(pageCountAll<MaxTagNum){realTagNum=pageCountAll}
		str ="";
		var firstTag=0;
		if(index<=between||realTagNum<MaxTagNum){
			for(var i=firstTag;i<realTagNum;i++){
				str+= tagStr(i,index)
			}
		}else if(index>pageCountAll-between){
			firstTag=pageCountAll-MaxTagNum;
			for(var i=firstTag;i<realTagNum+firstTag;i++){
				str+= tagStr(i,index)
			}
		}else{
			firstTag=index-between;
			for(var i=firstTag;i<realTagNum+firstTag;i++){
				str+=tagStr(i,index);
			}
		}
		var dom = pageTemplate.replace("{$tags}",str);
		var dom = dom.replace("{$pre}",(index==0?0:index-1)+","+option.name);
		var dom = dom.replace("{$next}",(index==(pageCountAll-1)?(pageCountAll-1):(index+1))+","+option.name);
		var dom = dom.replace("{$maxNum}",(pageCountAll-1)+","+option.name);
		$(id).html(dom);
		function tagStr(i,index){
			if(i==index){
				return '<div class="pageButton pageCurrent" onclick="$.page('+i+','+option.name+')">'+(i+1)+'</div>';
			}else{
				return '<div class="pageButton" onclick="$.page('+i+','+option.name+')">'+(i+1)+'</div>';
			}
		}
	}
})(jQuery)