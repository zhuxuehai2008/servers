<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8"/>
<title>后台登录</title>
<meta name="author" content="DeathGhost" />
<link rel="stylesheet" type="text/css" href="<%=path %>/static/css/style.css" />
<style>
body{height:100%;background:#16a085;overflow:hidden;}
canvas{z-index:-1;position:absolute;}
</style>
<script src="<%=path %>/static/js/jquery.js"></script>
<script src="<%=path %>/static/js/Particleground.js"></script>
<script>
$(document).ready(function() {
  //粒子背景特效
  $('body').particleground({
    dotColor: '#5cbdaa',
    lineColor: '#5cbdaa'
  });
});
	  function changeJCode(){
	  $(".J_codeimg").attr("src","<%=path%>/servlet/randImage.cl?timestamp="+new Date().getTime());
	  }
	  function check(){
		  return true;
	  }
</script>
</head>
<body>
<form action="<%=path%>/login" method="post" onsubmit="return check();">
<dl class="admin_login">
 <dt>
  <strong>站点后台管理系统</strong>
  <em>Management System</em>
 </dt>
 <dd class="user_icon">
  <input type="text" placeholder="账号" class="login_txtbx"/>
 </dd>
 <dd class="pwd_icon">
  <input type="password" placeholder="密码" class="login_txtbx"/>
 </dd>
 <dd class="val_icon">
  <div class="checkcode">
    <input type="text" id="J_codetext" placeholder="验证码" maxlength="4" class="login_txtbx">
    <img class="J_codeimg" src="<%=path%>/servlet/randImage.cl?timestamp=<%=System.currentTimeMillis()%>">
  </div>
  <input type="button" value="更换验证码" class="ver_btn" onClick="changeJCode();">
 </dd>
 <dd>
  <input type="submit" value="立即登录" class="submit_btn"/>
 </dd>
 </form>
 <dd>
  <p>© 2018-2019 ZW 版权所有</p>
  <p>吉B2-20080224-1</p>
 </dd>
</dl>
</body>
</html>
