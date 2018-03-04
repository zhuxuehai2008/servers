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
    	url:"<%=path%>/commodity/categoryPage",
    	success:function(data){
    		console.log(data);	
			var l = data.list;
			showList(l);
    	}
    }
    function showList(l){
	    	for(var i=0;i<l.length;i++){
	    		$("#pageTarget").append("<tr><td class='center'>"+l[i].name+"</td><td><img src='<%=path%>"+l[i].pic+"'></td><td class='center'>"+(l[i].isEnd==1?"末端":"父节点")+"</td><td class='center'><a href='<%=path %>/commodity/categoryForm?id="+l[i].id+"' title='编辑' class='link_icon' target='_blank'>&#118;</a><a onclick='del("+l[i].id+")' title='删除' class='link_icon'>&#100;</a></td>");
	    	}
    }
    function search(){
	   	 $.ajax({ 
			   url:"<%=path %>/commodity/categorySearch", 
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
			   url:"<%=path %>/commodity/categoryDelete", 
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
                <h2 class="fl">分类管理列表</h2>
                <a class="fr top_rt_btn add_icon" href="<%=path %>/commodity/categoryForm">添加分类</a>
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
	                    <th>是否末端</th>
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
    <style>
    	.pageButton {
		    float: left;
		    border: #ddd 1px solid;
		    cursor: pointer;
		    padding: 4px 15px;
		    border-right: none;
		}
		div#page {
		    float: right;
		    margin-top: 20px;
		}
		wlht_styles.css:359
		.pagestir {
		    float: right;
		    margin-right: 50px;
		}
		    </style>
    <%-- <script src="<%=path %>/static/js/ueditor.config.js"></script>
    <script src="<%=path %>/static/js/ueditor.all.min.js">
    </script> --%>
    <!-- <script type="text/javascript">
        //实例化编辑器
        //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
        var ue = UE.getEditor('editor');


        function isFocus(e) {
            alert(UE.getEditor('editor').isFocus());
            UE.dom.domUtils.preventDefault(e)
        }

        function setblur(e) {
            UE.getEditor('editor').blur();
            UE.dom.domUtils.preventDefault(e)
        }

        function insertHtml() {
            var value = prompt('插入html代码', '');
            UE.getEditor('editor').execCommand('insertHtml', value)
        }

        function createEditor() {
            enableBtn();
            UE.getEditor('editor');
        }

        function getAllHtml() {
            alert(UE.getEditor('editor').getAllHtml())
        }

        function getContent() {
            var arr = [];
            arr.push("使用editor.getContent()方法可以获得编辑器的内容");
            arr.push("内容为：");
            arr.push(UE.getEditor('editor').getContent());
            alert(arr.join("\n"));
        }

        function getPlainTxt() {
            var arr = [];
            arr.push("使用editor.getPlainTxt()方法可以获得编辑器的带格式的纯文本内容");
            arr.push("内容为：");
            arr.push(UE.getEditor('editor').getPlainTxt());
            alert(arr.join('\n'))
        }

        function setContent(isAppendTo) {
            var arr = [];
            arr.push("使用editor.setContent('欢迎使用ueditor')方法可以设置编辑器的内容");
            UE.getEditor('editor').setContent('欢迎使用ueditor', isAppendTo);
            alert(arr.join("\n"));
        }

        function setDisabled() {
            UE.getEditor('editor').setDisabled('fullscreen');
            disableBtn("enable");
        }

        function setEnabled() {
            UE.getEditor('editor').setEnabled();
            enableBtn();
        }

        function getText() {
            //当你点击按钮时编辑区域已经失去了焦点，如果直接用getText将不会得到内容，所以要在选回来，然后取得内容
            var range = UE.getEditor('editor').selection.getRange();
            range.select();
            var txt = UE.getEditor('editor').selection.getText();
            alert(txt)
        }

        function getContentTxt() {
            var arr = [];
            arr.push("使用editor.getContentTxt()方法可以获得编辑器的纯文本内容");
            arr.push("编辑器的纯文本内容为：");
            arr.push(UE.getEditor('editor').getContentTxt());
            alert(arr.join("\n"));
        }

        function hasContent() {
            var arr = [];
            arr.push("使用editor.hasContents()方法判断编辑器里是否有内容");
            arr.push("判断结果为：");
            arr.push(UE.getEditor('editor').hasContents());
            alert(arr.join("\n"));
        }

        function setFocus() {
            UE.getEditor('editor').focus();
        }

        function deleteEditor() {
            disableBtn();
            UE.getEditor('editor').destroy();
        }

        function disableBtn(str) {
            var div = document.getElementById('btns');
            var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
            for (var i = 0, btn; btn = btns[i++];) {
                if (btn.id == str) {
                    UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
                } else {
                    btn.setAttribute("disabled", "true");
                }
            }
        }

        function enableBtn() {
            var div = document.getElementById('btns');
            var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
            for (var i = 0, btn; btn = btns[i++];) {
                UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
            }
        }

        function getLocalData() {
            alert(UE.getEditor('editor').execCommand("getlocaldata"));
        }

        function clearLocalData() {
            UE.getEditor('editor').execCommand("clearlocaldata");
            alert("已清空草稿箱")
        }
    </script> -->
</body>

</html>