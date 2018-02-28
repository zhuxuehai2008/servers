<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <script src="<%=path %>/static/js/jquery.js"></script>
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
                <span>选择大类</span>
                <select class="select">
        <option>药品</option>
        <option>保健品</option>
       </select>

                <input type="button" value="查询" class="group_btn" />
            </section>
            <table class="table">
                <tr>
                    <th>分类名称</th>
                    <th>分类图片</th>

                    <th>操作</th>
                </tr>
                <tr>
                    <td class="center">美容养颜</td>
                    <td class="center">
                        <img src="<%=path %>/static/images/index_navico/1.png" width="50" height="50">
                    </td>

                    <td class="center">
                        <a href="<%=path %>/static/type_detail.html" title="编辑" class="link_icon" target="_blank">&#118;</a>
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
    <script src="<%=path %>/static/js/ueditor.config.js"></script>
    <script src="<%=path %>/static/js/ueditor.all.min.js">
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