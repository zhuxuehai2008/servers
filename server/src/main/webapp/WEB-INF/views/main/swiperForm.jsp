<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
   <dd><a href="index_swiper.html" class="active">首页轮播图管理</a></dd>
       <dd><a href="hot_productlist.html">热销商品管理</a></dd>
   
   </dl>
  </li>
  <li>
   <dl>
    <dt>商品信息管理</dt>
    <!--当前链接则添加class:active-->
    <dd><a href="type_list.html" >分类管理</a></dd>
       <dd><a href="type_list.html" >品牌管理</a></dd>
    <dd><a href="product_list.html">药品管理</a></dd>
    
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
    <dd><a href="youfei_set.html"  >邮费设置</a></dd>
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
       <h2 class="fl">轮播图详情</h2>
       <a class="fr top_rt_btn" href="index_swiper.html">返回图片轮播列表</a>
      </div>
     <section>
        
      <ul class="ulColumn2">
          <li>
        <span class="item_name" style="width:120px;">图片位置：</span>
        <input type="text" class="textbox textbox_295" placeholder="商品名称..."/>
        <span class="errorTips">注意所选大类，以保证添加分类正确    </span>
       </li>
         
       
       <li>
        <span class="item_name" style="width:120px;">上传轮播图：</span>
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
    