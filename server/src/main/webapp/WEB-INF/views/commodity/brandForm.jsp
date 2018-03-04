<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% String path = request.getContextPath();%>
<html>
<head>
<meta charset="utf-8"/>
<title>后台管理系统</title>
<meta name="author" content="DeathGhost" />
<link rel="stylesheet" type="text/css" href="<%=path %>/static/css/style.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css" />
<style>
ul .ztree{
	margin-top: 10px;
    border: 1px solid #617775;
    background: #f0f6e4;
    width: 220px;
    height: 160px;
    overflow-y: scroll;
    overflow-x: auto;
   }

</style>
<!--[if lt IE 9]>
<script src="js/html5.js"></script>
<![endif]-->
<script src="<%=path %>/static/js/jquery-1.9.1.min.js"></script>
<script src="<%=path %>/static/js/jquery.mCustomScrollbar.concat.min.js"></script>
<script type="text/javascript" src="<%=path%>/static/js/ztree/js/jquery.ztree.all.js"></script>	
<script type="text/javascript" src="<%=path%>/static/js/component/ztree.js"></script>	
<script>

	(function($){
		$(window).load(function(){
			
			$("a[rel='load-content']").click(function(e){
				e.preventDefault();
				var url=$(this).attr("href");
				$.get(url,function(data){
					$(".content .mCSB_container").append(data); //load new content inside .mCSB_container
					//scroll-to appended content 
					$(".content").mCustomScrollbar("scrollTo","h2:last");
				});
			});
			
			$(".content").delegate("a[href='top']","click",function(e){
				e.preventDefault();
				$(".content").mCustomScrollbar("scrollTo",$(this).attr("href"));
			});
            
		});
	})(jQuery);
</script>
</head>
<body>
<!-- header -->
<%@include file="../component/header.jsp"%>
<!--header-->
<!--aside nav-->
<%@include file="../component/menuLeft.jsp"%>
<!--aside nav-->

<section class="rt_wrap content mCustomScrollbar">
 <div class="rt_content">
      <div class="page_title">
       <h2 class="fl">品牌详情</h2>
       <a href="<%=path %>/commodity/brandList" class="fr top_rt_btn">返回品牌列表</a>
      </div>
     <section>
      <ul class="ulColumn2 flex-column" id="form">
       <li>
       		<c:if test="${null!=one}">
		        <input id = "id" name="id" type="hidden" value="${one.id}">
       		</c:if>
       </li>
       <li>
        <span class="item_name" style="width:120px;">品牌名称：</span>
        <input type="text" name="name" class="textbox textbox_295" placeholder="品牌名称..." value="${null!=one?one.name:null}"/>
        <span class="errorTips">不限字数，但为保证展示效果，最好不要超过8字</span>
       </li>
       <li>
       <form action="<%=path %>/servlet/imageUpload" method = "post" id="uploadFile" enctype="multipart/form-data">
        <span class="item_name" style="width:120px;">上传图片：</span>
        <label class="uploadImg">
         <input type="file" name="file"  onchange="uploadPic()"/>
         <span>上传图片</span>
        </label>
        </form>
       </li>
       <li>
        <span class="item_name" style="width:120px;">已上传：</span>
        <label class="uploadImg">
        	<input type="hidden" name="pic" id="pic" value="${null!=one?one.pic:null}">
       		<img src="<%=path %>${null!=one?one.pic:null}" id="showImage">
        </label>
       </li>
       <li>
        <span class="item_name" style="width:120px;"></span>
        <input type="submit" class="link_btn" onclick="submit()"/>
       </li>
      </ul>
     </section>
 </div>
</section>
<script>
function uploadPic() { 
	  var form = document.getElementById('uploadFile'), 
	  formData = new FormData(form); 
	  $.ajax({ 
	   url:"<%=path %>/servlet/imageUpload", 
	   type:"post", 
	   data:formData, 
	   processData:false, 
	   cache: false,
	   contentType:false,
	   success:function(res){ 
	    if(res){ 
	     alert("上传成功！"); 
	    } 
	    $('#showImage').attr("src","<%=path%>"+res);
	    $("#pic").val(res);
	    console.log(res); 
	   }, 
	   error:function(err){ 
	    alert("网络连接失败,稍后重试",err); 
	   } 
	  }) 
	 } 
function submit(){
	var data={
	 id:$("#form input[name=id]").val(),
	 name:$("#form input[name=name]").val(),
	 pic : $("#form input[name=pic]").val(),
	};
	 $.ajax({ 
		   url:"<%=path %>/commodity/brandForm", 
		   type:"post", 
		   processData:true,
		   data:data, 
		   dataType:"json",
		   success:function(res){ 
			   alert(res.msg);
			   if(res.status==200){
				   //location.href = "<%=path%>/commodity/brandList";
			   }
		   }, 
		   error:function(err){ 
		    alert("网络连接失败,稍后重试",err); 
		   } 
	}) 
}
</script>
</body>
</html>
