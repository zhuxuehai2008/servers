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
		var zNodes =[{name:"分类", id:"0",pid:"-1",isParent:true,nocheck:true}];
		var setting = {async: {url:"<%=path%>/commodity/categoryChildren",
							   dataFilter: filter
							  },
					   callback: {onCheck: onCheck
					              }
					  };
		 function filter(treeId, parentNode, childNodes) {
				if(childNodes.status!=200){return null}
				childNodes = childNodes.result;
				if (!childNodes) return null;
				for (var i=0, l=childNodes.length; i<l; i++) {
					childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
					if(childNodes[i].isEnd==0){
						childNodes[i].isParent=true;
						childNodes[i].chkDisabled=true;
					}else{
						childNodes[i].isParent=false;
					}
					if(childNodes[i].id==oneId){
						childNodes[i].checked=true;
					} 
				}
				return childNodes;
			}
			function onCheck(e, treeId, treeNode) {
				var zTree = $.fn.zTree.getZTreeObj("tree"),
				nodes = zTree.getCheckedNodes(true),
				v = "";m = "";
				for (var i=0, l=nodes.length; i<l; i++) {
					v = nodes[i].name ;
					m = nodes[i].id ;
				}
				oneId=m;
				$("#categoryName").attr("value", v);
				$("#categoryId").val(m);
			} 
			$(function(){
			$.tree("#tree", zNodes,{treeId:"tree",formDataId:"categoryId",viewNameId:"categoryName"},setting);
			});
	function uploadPic(id,succ) { 
		  var form = document.getElementById(id), 
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
		    succ(res);
		   }, 
		   error:function(err){ 
		    alert("网络连接失败,稍后重试",err); 
		   } 
		  }) 
		 } 
	function thumbnailCall(res){
		    $('#showThumbnailImage').attr("src","<%=path%>"+res);
		    $("#thumbnailPic").val(res);
	}
	function banner1Call(res){
		    $('#showBanner1Image').attr("src","<%=path%>"+res);
		    $("#banner1Pic").val(res);
	}
	function banner2Call(res){
		    $('#showBanner2Image').attr("src","<%=path%>"+res);
		    $("#banner2Pic").val(res);
	}
	function banner3Call(res){
		    $('#showBanner3Image').attr("src","<%=path%>"+res);
		    $("#banner3Pic").val(res);
	}
	function detailCall(res){
		    $('#showDetailImage').attr("src","<%=path%>"+res);
		    $("#detailPic").val(res);
	}
	function submit(){
		var data={
		 id:$("#form input[name=id]").val(),
		 name:$("#form input[name=name]").val(),
		 categoryId:$("#form input[name=categoryId]").val(),
		 brandId:$("#form select[name=brandId]").val(),
		 code:$("#form input[name=code]").val(),
		 showPrice:$("#form input[name=showPrice]").val(),
		 realPrice:$("#form input[name=realPrice]").val(),
		 stock:$("#form input[name=stock]").val(),
		 thumbnailPic : $("#form input[name=thumbnailPic]").val(),
		 banner1Pic : $("#form input[name=banner1Pic]").val(),
		 banner2Pic : $("#form input[name=banner2Pic]").val(),
		 banner3Pic : $("#form input[name=banner3Pic]").val(),
		 detailPic : $("#form input[name=detailPic]").val(),
		};
		 $.ajax({ 
			   url:"<%=path %>/commodity/commodityForm", 
			   type:"post", 
			   processData:true,
			   data:data, 
			   dataType:"json",
			   success:function(res){ 
				   alert(res.msg);
				   if(res.status==200){
					   //location.href = "<%=path%>/commodity/commodityList";
				   }
			   }, 
			   error:function(err){ 
			    alert("网络连接失败,稍后重试",err); 
			   } 
		}) 
	}
</script>
<c:if test="${null!=one}">
<script>
var oneId = ${null!=one?one.categoryId:null};
</script>
</c:if>
<c:if test="${null==one}">
<script>
var oneId = null;
</script>
</c:if>
</head>
<body>
	<!-- header -->
<%@include file="../component/header.jsp"%>
	<!-- header -->
    <!--aside nav-->
<%@include file="../component/menuLeft.jsp"%>
    <!--aside nav-->

<section class="rt_wrap content mCustomScrollbar">
 <div class="rt_content">
      <div class="page_title">
       <h2 class="fl">商品详情</h2>
       <a class="fr top_rt_btn" href="<%=path %>/commodity/commodityList">返回产品列表</a>
      </div>
     <section>
      <ul class="ulColumn2" id="form">
         <li>
	        <span class="item_name" style="width:120px;">分类：</span>
	        <input id = "categoryId" name="categoryId" type="hidden" value="${null!=one?one.categoryId:null}">
	        <label onclick="$.showTree()">
	        <input type="text" readonly name="categoryName" id="categoryName" class="textbox textbox_295" value="${null!=category?category.name:null}"/>
			<input readonly class="link_btn" type="button" value="选择类别" >
			</label>
			<div tabindex="0" id="treeContent" class="treeContent" style="display:none; position:fixed;z-index:1000;" >
				   <ul id="tree" class="ztree" style="margin-top:0; width:170px; height: 200px;"></ul>
			</div>        
	        <span class="errorTips">注意所选大类，以保证添加分类正确    </span>
         </li>
         <li>
	        <span class="item_name" style="width:120px;">品牌：</span>
	        <select class="select" name="brandId">
	        	<c:forEach items="${brandList}" var="item">
	        		 <option value="${item.id}" <c:if test="${null!=one?(one.brandId==item.id?true:false):false}">selected</c:if>>${item.name}</option>
	        	</c:forEach>
	        </select>
	        <span class="errorTips">注意所选品牌，以保证添加药品正确    </span>
         </li>
       <li>
        <span class="item_name" style="width:120px;">商品名称：</span>
        <input type="text" class="textbox textbox_295" name = "name" placeholder="商品名称..." value="${null!=one?one.name:null}"/>
        <span class="errorTips">错误提示信息...</span>
       </li>
       <li>
        <span class="item_name" style="width:120px;">代码：</span>
        <input type="text" class="textbox" name = "code" placeholder="国药准字号..." value="${null!=one?one.code:null}"/>
        <span class="errorTips">您输入的准字号有重复，同种药品已经存在！</span>
       </li>
      <li>
        <span class="item_name" style="width:120px;">当前库存：</span>
        <input type="text" class="textbox" name = "stock"placeholder="当前库存..." value="${null!=one?one.stock:null}"/>
        <span class="errorTips">库存数量不能是空或0！</span>
       </li>
       <li>
        <span class="item_name" style="width:120px;">柜台价：</span>
        <input type="text" class="textbox" name = "showPrice" placeholder="柜台价..." value="${null!=one?one.showPrice:null}"/>
        <span class="errorTips">柜台价格应高于热销价</span>
       </li>
          <li>
        <span class="item_name" style="width:120px;">热销价：</span>
        <input type="text" class="textbox" name = "realPrice" placeholder="热销价..." value="${null!=one?one.realPrice:null}"/>
        <span class="errorTips">热销价格应低于柜台价</span>
       </li>
       <li>
        <span class="item_name" style="width:120px;">药品缩略图：</span>
        <label class="uploadImg">
        <form action="<%=path %>/servlet/imageUpload" method = "post" id="thumbnailForm" enctype="multipart/form-data">
         <input type="file" name="file"  onchange="uploadPic('thumbnailForm',thumbnailCall)"/>
         <span>上传图片</span>
        </form>
        </label>
        <label class="uploadImg">
        	<input type="hidden" name="thumbnailPic" id="thumbnailPic" value="${null!=one?one.pic:null}">
       		<img src="<%=path %>${null!=one?one.thumbnailPic:null}" id="showThumbnailImage">
        </label>
       </li>
        <li>
        <span class="item_name" style="width:120px;">药品详情图：</span>
        <label class="uploadImg">
        <form action="<%=path %>/servlet/imageUpload" method = "post" id="banner1Form" enctype="multipart/form-data">
         <input type="file" name="file"  onchange="uploadPic('banner1Form',banner1Call)"/>
         <span>上传图片</span>
        </form>
        </label>
        <label class="uploadImg">
        	<input type="hidden" name="banner1Pic" id="banner1Pic" value="${null!=one?one.pic:null}">
       		<img src="<%=path %>${null!=one?one.banner1Pic:null}" id="showBanner1Image">
        </label>
        <label class="uploadImg">
        <form action="<%=path %>/servlet/imageUpload" method = "post" id="banner2Form" enctype="multipart/form-data">
         <input type="file" name="file"  onchange="uploadPic('banner2Form',banner2Call)"/>
         <span>上传图片</span>
        </form>
        </label>
        <label class="uploadImg">
        	<input type="hidden" name="banner2Pic" id="banner2Pic" value="${null!=one?one.pic:null}">
       		<img src="<%=path %>${null!=one?one.banner2Pic:null}" id="showBanner2Image">
        </label>
        <label class="uploadImg">
        <form action="<%=path %>/servlet/imageUpload" method = "post" id="banner3Form" enctype="multipart/form-data">
         <input type="file" name="file"  onchange="uploadPic('banner3Form',banner3Call)"/>
         <span>上传图片</span>
        </form>
        </label>
        <label class="uploadImg">
        	<input type="hidden" name="banner3Pic" id="banner3Pic" value="${null!=one?one.pic:null}">
       		<img src="<%=path %>${null!=one?one.banner3Pic:null}" id="showBanner3Image">
        </label>
       </li>
          <li>
        <span class="item_name" style="width:120px;">药品说明图：</span>
        <label class="uploadImg">
        <form action="<%=path %>/servlet/imageUpload" method = "post" id="detailForm" enctype="multipart/form-data">
         <input type="file" name="file"  onchange="uploadPic('detailForm',detailCall)"/>
         <span>上传图片</span>
        </form>
        </label>
        <label class="uploadImg">
        	<input type="hidden" name="detailPic" id="detailPic" value="${null!=one?one.pic:null}">
       		<img src="<%=path %>${null!=one?one.detailPic:null}" id="showDetailImage">
        </label>
        <label class="uploadImg">
         <input type="file"/>
         <span>上传图片</span>
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
<script src="js/ueditor.config.js"></script>
<script src="js/ueditor.all.min.js"> </script>

</body>
</html>
