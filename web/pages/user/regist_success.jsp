<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>点点点餐会员注册页面</title>
	<%--	静态包含 base标签、css样式、jQuery文件--%>
	<%@include file="/pages/common/head.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}

	h1 a {
		color:red;
	}
	.log_name{
		float: left;
		padding-top: 50px;
		font: normal 700 50px/20px "黑体";
		/* font: font-style font-weight font-size/line-height font-family; */
		color: #ffae4e;

	}

</style>
</head>
<body>
		<div id="header">
<!--				<img class="logo_img" alt="" src="static/img/logo.gif" >-->
<%--			<span class="log_name">点点点餐</span>--%>
			<span class="wel_word">注册成功</span>
			<%@include file="/pages/common/login-success_menu.jsp"%>
		</div>
		
		<div id="main">
		
			<h1>注册成功! <a href="index.jsp">转到主页</a></h1>
	
		</div>

		<%@include file="/pages/common/footer.jsp"%>
</body>
</html>