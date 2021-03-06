<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8" />
    <title>后台管理系统</title>
    <meta name="author" content="DeathGhost" />
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <!--[if lt IE 9]>
<script src="js/html5.js"></script>
<![endif]-->
    <script src="js/jquery.js"></script>
    <script src="js/jquery.mCustomScrollbar.concat.min.js"></script>
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
</head>

<body>
    <!--header-->
    <header>
        <h1><img src="images/admin_logo.png" /></h1>
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
       <dd><a href="type_list.html">品牌管理</a></dd>
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
                <a class="fr top_rt_btn add_icon" href="type_detail.html">添加轮播图</a>
            </div>
           
            <table class="table">
                <tr>
                    <th>图片顺序</th>
                    <th>图片缩略图</th>

                    <th>操作</th>
                </tr>
                <tr>
                    <td class="center">1</td>
                    <td class="center">
                        <img src="images/index_navico/1.png" width="150" >
                    </td>

                    <td class="center">
                        <a href="index_swiperDetail.html" title="编辑" class="link_icon" target="_blank">&#118;</a>
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
    <script src="js/ueditor.config.js"></script>
    <script src="js/ueditor.all.min.js">
    </script>
    <script type="text/javascript">
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
    </script>
</body>

</html>