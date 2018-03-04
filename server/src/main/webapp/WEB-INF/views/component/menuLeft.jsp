<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<aside class="lt_aside_nav content mCustomScrollbar">
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
    <dd><a href="<%=request.getContextPath() %>/commodity/categoryList" <%-- class="active" --%>>分类管理</a></dd>
       <dd><a href="<%=request.getContextPath() %>/commodity/brandList">品牌管理</a></dd>
    <dd><a href="<%=request.getContextPath() %>/commodity/commodityList">商品管理</a></dd>
    
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