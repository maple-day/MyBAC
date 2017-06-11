<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags"  prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css" type="text/css">



<link rel="stylesheet" href="${pageContext.request.contextPath }/js/login/log_in.css">
<title>权限管理系统</title>

</head>
<body style="background-image: url(${pageContext.request.contextPath }/images/log_in_bg.jpg);"> 
	<div class="log-in-wrapper">
        <span class="close-btn"></span>
        <div class="main-content">
       		<div>
				<font color="red">
					<s:actionerror/>
					<s:fielderror></s:fielderror>
				</font>
			</div>		
			<form method="post" class="niceform"
					action="${pageContext.request.contextPath }/userAction_login.action">
				<div class="item item-username" >
	                <input name="username"  placeholder="登录账号(用户名或邮箱)" class="input-box ng-pristine ng-untouched ng-valid" >
	                <span class="icon"></span>
	                <span   class="tooltip ng-binding ng-hide"></span>
            	</div>

            	<div class="item item-password" >
	                <input name="password"  placeholder="登录密码" class="input-box ng-pristine ng-untouched ng-valid"  type="password">
	                <span class="icon"></span>
	                <span class="tooltip ng-binding ng-hide"></span>
            	</div>

            	<div class="item item-captcha" ">
	                <input placeholder="验证码" name="checkcode" class="input-box ng-pristine ng-untouched ng-valid" >
	                <img id="captcha-img" class="captcha-img" src="${pageContext.request.contextPath }/validatecode.jsp" onClick="javascript:document.getElementById('captcha-img').src='${pageContext.request.contextPath }/validatecode.jsp?'+Math.random();">
	                <span class="icon" onClick="javascript:document.getElementById('captcha-img').src='${pageContext.request.contextPath }/validatecode.jsp?'+Math.random();" ></span>
	                <span   class="tooltip ng-binding ng-hide"></span>
            	</div>
				
            	<button  class="login-btn">登录</button>
			</form>
        </div>
    </div>
	
</body>
</html>