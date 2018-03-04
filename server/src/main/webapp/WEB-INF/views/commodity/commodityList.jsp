<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% String path = request.getContextPath();%>
<html>
<head>
<meta charset="utf-8"/>
<title>后台管理系统</title>
<meta name="author" content="DeathGhost" />
<link rel="stylesheet" type="text/css" href="<%=path %>/static/css/style.css">
<!--[if lt IE 9]>
<script src="js/html5.js"></script>
<![endif]-->
<script src="<%=path %>/static/js/jquery-1.9.1.min.js"></script>
<script src="<%=path %>/static/js/jquery.mCustomScrollbar.concat.min.js"></script>
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
	<!-- header -->
    <!--aside nav-->
<%@include file="../component/menuLeft.jsp"%>
    <!--aside nav-->
<section class="rt_wrap content mCustomScrollbar">
 <div class="rt_content">
      <div class="page_title">
       <h2 class="fl">商品列表</h2>
       <a href="<%=path %>/commodity/commodityForm" class="fr top_rt_btn add_icon">添加药品</a>
      </div>
      <section class="mtb">
          <span>选择大类</span>
       <select class="select">
        <option>药品</option>
        <option>保健品</option>
       </select>
          <span>选择分类</span>
       <select class="select">
        <option>消炎药</option>
        <option>止痛药</option>
       </select>
       <input type="text" class="textbox textbox_225" placeholder="输入产品关键词"/>
       <input type="button" value="查询" class="group_btn"/>
      </section>
      <table class="table">
       <tr>
        <th>ID</th>
        <th>国药准字</th>
        <th>缩略图</th>
        <th>产品名称</th>
        
        <th>柜台价</th>
        <th>促销价（现价）</th>
        <th>销量</th>
        
        <th>库存</th>
        <th>操作</th>
       </tr>
       <tr>
           <td class="center">15902</td>
           <td class="center">AXX15902</td>
        <td class="center"><img src="upload/goods01.jpg" width="50" height="50"/></td>
        <td>这里是产品名称</td>
        
        <td class="center"><strong class="rmb_icon">59.00</strong></td>
        <td class="center"><strong class="rmb_icon">49.00</strong></td>
        <td class="center">5555</td>
        <td class="center">5559</td>
        <td class="center">
        
         <a href="product_detail.html" title="编辑" class="link_icon">&#101;</a>
         <a href="#" title="删除" class="link_icon">&#100;</a>
        </td>
       </tr>
       <tr>
            <td class="center">15904</td>
           <td class="center">AXX15902</td>
        <td class="center"><img src="upload/goods02.jpg" width="50" height="50"/></td>
        <td>这里是产品名称</td>
       
        <td class="center"><strong class="rmb_icon">59.00</strong></td>
            <td class="center"><strong class="rmb_icon">49.00</strong></td>
        <td class="center">5555</td>
        <td class="center">5559</td>
        <td class="center">
        
         <a href="product_detail.html" title="编辑" class="link_icon">&#101;</a>
         <a href="#" title="删除" class="link_icon">&#100;</a>
        </td>
       </tr>
      </table>
      <aside class="paging">
       <a>第一页</a>
       <a>1</a>
       <a>2</a>
       <a>3</a>
       <a>…</a>
       <a>1004</a>
       <a>最后一页</a>
      </aside>
 </div>
</section>
</body>
</html>
