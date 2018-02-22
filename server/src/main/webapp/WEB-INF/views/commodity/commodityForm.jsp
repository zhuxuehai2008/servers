<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% String path = request.getContextPath();%>
<html>
<head>
<meta charset="utf-8"/>
<title>后台管理系统</title>
<meta name="author" content="DeathGhost" />
<link rel="stylesheet" type="text/css" href="css/style.css">
<!--[if lt IE 9]>
<script src="js/html5.js"></script>
<![endif]-->
<script src="js/jquery.js"></script>
<script src="js/jquery.mCustomScrollbar.concat.min.js"></script>
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
<!--header-->
<header>
 <h1><img src="images/admin_logo.png"/></h1>
 <ul class="rt_nav">
           
            <li><a href="#" class="admin_icon">DeathGhost</a></li>
           
            <li><a href="login.html" class="quit_icon">安全退出</a></li>
        </ul>
</header>
<!--aside nav-->
<!--aside nav-->
<aside class="lt_aside_nav content mCustomScrollbar">
 <h2><a href="index.html">起始页</a></h2>
 <ul>
      <li>
   <dl>
    <dt>首页信息管理</dt>
    <!--当前链接则添加class:active-->
   <dd><a href="index_swiper.html">首页轮播图管理</a></dd>
       <dd><a href="hot_productlist.html">热销商品管理</a></dd>
   </dl>
  </li>
  <li>
   <dl>
    <dt>商品信息管理</dt>
    <!--当前链接则添加class:active-->
    <dd><a href="type_list.html" >分类管理</a></dd>
       <dd><a href="type_list.html">品牌管理</a></dd>
    <dd><a href="product_list.html"  class="active">药品管理</a></dd>
    
   </dl>
  </li>
  <li>
   <dl>
    <dt>订单信息</dt>
    <dd><a href="order_list.html" >订单列表</a></dd>
   
   </dl>
  </li>
  
  
  <li>
   <dl>
    <dt>配送与支付设置</dt>
    <dd><a href="express_list.html">配送方式</a></dd>
    <dd><a href="youfei_set.html" >邮费设置</a></dd>
   </dl>
  </li>
  <li>
   <dl>
    <dt>在线统计</dt>
    
    <dd><a href="sales_volume.html">销售额统计</a></dd>
   </dl>
  </li>
 
 </ul>
</aside>

<section class="rt_wrap content mCustomScrollbar">
 <div class="rt_content">
      <div class="page_title">
       <h2 class="fl">商品详情</h2>
       <a class="fr top_rt_btn" href="product_list.html">返回产品列表</a>
      </div>
     <section>
      <ul class="ulColumn2">
           <li>
        <span class="item_name" style="width:120px;">大类：</span>
        <select class="select">
         <option>选择产品大</option>
        </select>
        <span class="errorTips">注意所选大类，以保证添加分类正确    </span>
       </li>
           <li>
        <span class="item_name" style="width:120px;">分类：</span>
        <select class="select">
         <option>选择产品分类</option>
        </select>
        <span class="errorTips">注意所选分类，以保证添加品牌正确    </span>
       </li>
           <li>
        <span class="item_name" style="width:120px;">品牌：</span>
        <select class="select">
         <option>选择品牌</option>
        </select>
        <span class="errorTips">注意所选品牌，以保证添加药品正确    </span>
       </li>
      
       <li>
        <span class="item_name" style="width:120px;">药品名称：</span>
        <input type="text" class="textbox textbox_295" placeholder="商品名称..."/>
        <span class="errorTips">错误提示信息...</span>
       </li>
       <li>
        <span class="item_name" style="width:120px;">国药准字号：</span>
        <input type="text" class="textbox" placeholder="国药准字号..."/>
        <span class="errorTips">您输入的准字号有重复，同种药品已经存在！</span>
       </li>
      <li>
        <span class="item_name" style="width:120px;">当前库存：</span>
        <input type="text" class="textbox" placeholder="当前库存..."/>
        <span class="errorTips">库存数量不能是空或0！</span>
       </li>
       <li>
        <span class="item_name" style="width:120px;">柜台价：</span>
        <input type="text" class="textbox" placeholder="柜台价..."/>
        <span class="errorTips">柜台价格应高于热销价</span>
       </li>
          <li>
        <span class="item_name" style="width:120px;">热销价：</span>
        <input type="text" class="textbox" placeholder="柜台价..."/>
        <span class="errorTips">热销价格应低于柜台价</span>
       </li>
       <li>
        <span class="item_name" style="width:120px;">药品缩略图：</span>
        <label class="uploadImg">
         <input type="file"/>
         <span>上传图片</span>
        </label>
       </li>
        <li>
        <span class="item_name" style="width:120px;">药品详情图：</span>
        <label class="uploadImg">
         <input type="file"/>
         <span>上传图片</span>
        </label>
            <label class="uploadImg">
         <input type="file"/>
         <span>上传图片</span>
        </label>
            <label class="uploadImg">
         <input type="file"/>
         <span>上传图片</span>
        </label>
       </li>
          <li>
        <span class="item_name" style="width:120px;">药品说明图：</span>
        <label class="uploadImg">
         <input type="file"/>
         <span>上传图片</span>
        </label>
       
       </li>
       <li>
        <span class="item_name" style="width:120px;"></span>
        <input type="submit" class="link_btn"/>
       </li>
      </ul>
     </section>
 </div>
</section>
<script src="js/ueditor.config.js"></script>
<script src="js/ueditor.all.min.js"> </script>

</body>
</html>
