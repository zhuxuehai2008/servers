<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% String path = request.getContextPath();%>
<html>
<head>
    <meta charset="utf-8" />
    <title>后台管理系统</title>
    <meta name="author" content="DeathGhost" />
    <link rel="stylesheet" type="text/css" href="<%=path %>/static/css/style.css">
    <!--[if lt IE 9]>
	<script src="js/html5.js"></script>
	<![endif]-->
    <script src="<%=path %>/static/js/jquery-1.9.1.min.js"></script>
    <script src="<%=path %>/static/js/component/jquery.page.js"></script>
    <script src="<%=path %>/static/js/jquery.mCustomScrollbar.concat.min.js"></script>
    <script>
        (function($) {
            $(window).load(function() {

                $("a[rel='load-content']").click(function(e) {
                    e.preventDefault();
                    var url = $(this).attr("href");
                    $.get(url, function(data) {
                        $(".content .mCSB_container").append(data); //load new content inside .mCSB_container
                        //scroll-to appended content 
                        $(".content").mCustomScrollbar("scrollTo", "h2:last");
                    });
                });

                $(".content").delegate("a[href='top']", "click", function(e) {
                    e.preventDefault();
                    $(".content").mCustomScrollbar("scrollTo", $(this).attr("href"));
                });

            });
        })(jQuery);
    </script>
    <script>
    var pageSetting= {
    	url:"<%=path%>/commodity/brandPage",
    	success:function(data){
    		console.log(data);	
			var l = data.list;
			showList(l);
    	}
    }
    function showList(l){
	    	for(var i=0;i<l.length;i++){
	    		$("#pageTarget").append("<tr><td class='center'>"+l[i].name+"</td><td><img src='<%=path%>"+l[i].pic+"'></td><td class='center'><a href='<%=path %>/commodity/brandForm?id="+l[i].id+"' title='编辑' class='link_icon' target='_blank'>&#118;</a><a onclick='del("+l[i].id+")' title='删除' class='link_icon'>&#100;</a></td>");
	    	}
    }
    function search(){
	   	 $.ajax({ 
			   url:"<%=path %>/commodity/brandSearch", 
			   type:"post", 
			   data:{key:$("#searchKey").val()}, 
			   dataType:"json",
			   success:function(res){ 
				   if(res.status==200){
					   $("#pageTarget").html("");
					   showList(res.result)
				   }else{
					   alert(res.msg)
				   }
			   }, 
			   error:function(err){ 
			    alert("网络连接失败,稍后重试",err); 
			   } 
		})
    }
    function del(id){
	   	 $.ajax({ 
			   url:"<%=path %>/commodity/brandDelete", 
			   type:"post", 
			   data:{id:id}, 
			   dataType:"json",
			   success:function(res){ 
					   alert(res.msg);
					   $.page(0,pageSetting);
			   }, 
			   error:function(err){ 
			    alert("网络连接失败,稍后重试",err); 
			   } 
		})
    }
    $(function(){$.page(0,pageSetting);})
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
                <h2 class="fl">品牌管理列表</h2>
                <a class="fr top_rt_btn add_icon" href="<%=path %>/commodity/brandForm">添加品牌</a>
            </div>
            <section class="mtb">
                <span>关键字</span>
				<input type="text" id="searchKey"placeholder="查询关键字">
                <input type="button" value="查询" class="group_btn" onclick="search()"/>
            </section>
            <table class="table">
           		 <thead>
	                <tr>
	                    <th>分类名称</th>
	                    <th>分类图片</th>
	                    <th>操作</th>
	                </tr>
                </thead>
                <tbody id="pageTarget">
	                <tr>
	                    <td class="center">美容养颜</td>
	                    <td class="center">
	                        <img src="<%=path %>/static/images/index_navico/1.png" width="50" height="50">
	                    </td>
	
	                    <td class="center">
	                        <a href="<%=path %>/commodity/categoryUpdate" title="编辑" class="link_icon" target="_blank">&#118;</a>
	                        <a href="#" title="删除" class="link_icon">&#100;</a>
	                    </td>
	                </tr>
				</tbody>
            </table>
            <div id ="page" class="pagestir"></div>
        </div>
    </section>
</body>
</html>