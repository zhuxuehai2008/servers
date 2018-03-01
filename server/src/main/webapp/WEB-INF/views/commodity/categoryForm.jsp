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
<!--header-->
<!--aside nav-->
<%@include file="../component/menuLeft.jsp"%>
<!--aside nav-->

<section class="rt_wrap content mCustomScrollbar">
 <div class="rt_content">
      <div class="page_title">
       <h2 class="fl">分类详情</h2>
       <a class="fr top_rt_btn">返回分类列表</a>
      </div>
     <section>
         <p>分类就是首页 - 页面内部（非底部导航）显示的导航栏内的内容</p>
      <ul class="ulColumn2 flex-column" id="form">
        <li>
        <span class="item_name" style="width:120px;">所选大类：</span>
        <input type="hidden" name="parentId" class="textbox textbox_295"/>
       <select>
        <option>药品</option>  
        <option>保健品</option>  
       </select>
        <span class="errorTips">注意所选大类，以保证添加分类正确    </span>
       </li>
       <li>
        <span class="item_name" style="width:120px;">分类名称：</span>
        <input type="text" name="name" class="textbox textbox_295" placeholder="商品名称..."/>
        <span class="errorTips">不要超过4字</span>
       </li>
       
       <li>
        <span class="item_name" style="width:120px;">是否是末端节点：</span>
        <input type="checkbox" name="isEnd" >
       </li>
       <li>
       <form action="<%=path %>/servlet/imageUpload" method = "post" id="uploadFile" enctype="multipart/form-data">
        <span class="item_name" style="width:120px;">上传图片：</span>
        <label class="uploadImg">
         <input type="file" name="file" onchange="uploadPic()"/>
         <span>上传图片</span>
        </label>
        </form>
       </li>
       <li>
        <span class="item_name" style="width:120px;">已上传：</span>
        <label class="uploadImg">
        	<input type="hidden" name="picture" id="picture">
       		<img src="" id="showImage">
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
	  console.log(formData);
	  $.ajax({ 
	   url:"<%=path %>/servlet/imageUpload", 
	   type:"post", 
	   data:formData, 
	   processData:false, 
	   contentType:false/* "multipart/form-data;boundary="+new Date().getTime() */, 
	   success:function(res){ 
	    if(res){ 
	     alert("上传成功！"); 
	    } 
	    $('#showImage').attr("src","<%=path%>"+res);
	    $("#picture").val(res);
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
	 parentId:$("#form input[name=parentId]").val(),
	 picture : $("#form input[name=picture]").val(),
	 isEnd : $("#form input[name=isEnd]").is(":checked")?1:0
	};
	console.log(data);
	 $.ajax({ 
		   url:"<%=path %>/commodity/categoryForm", 
		   type:"post", 
		   processData:true,
		   //contentType:"application/json;charset=UTF-8",
		   data:data, 
		   dataType:"json",
		   success:function(res){ 
			   alert(res.msg);
			   if(res.status==200){
				   //location.href = "<%=path%>/commodity/categoryList";
			   }
		   }, 
		   error:function(err){ 
		    alert("网络连接失败,稍后重试",err); 
		   } 
	}) 
}
</script>
<script src="<%=path %>/static/js/ueditor.config.js"></script>
<script src="<%=path %>/static/js/ueditor.all.min.js"> </script>
<script type="text/javascript">

    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('editor');


    function isFocus(e){
        alert(UE.getEditor('editor').isFocus());
        UE.dom.domUtils.preventDefault(e)
    }
    function setblur(e){
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

    function getLocalData () {
        alert(UE.getEditor('editor').execCommand( "getlocaldata" ));
    }

    function clearLocalData () {
        UE.getEditor('editor').execCommand( "clearlocaldata" );
        alert("已清空草稿箱")
    }
</script>
</body>
</html>
