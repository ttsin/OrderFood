<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>点点点餐会员登录页面</title>
<%--	静态包含 base标签、css样式、jQuery文件--%>
<%@include file="/pages/common/head.jsp"%>
<%--<style>--%>
<%--	body {--%>
<%--		background-image:none;--%>
<%--	}--%>
<%--</style>--%>
<script type="text/javascript">
	/*
        * 需要做的工作：
        * 功能1：用户名要符合要求（只能由数字、字母、下划线组成，字段必须在5-12位之间）
        * 功能2：用户密码（只能由数字、字母、下划线组成，字段必须在5-12位之间）

	 */
	//加载页面
	$(function () {
		//给按钮绑上单击事件
		$("#sub_btn").click(function () {
			//功能1：获取用输入的名字，正则表达式，test()判断，错误给提示
			var usenameText = $("input[name='usename']").val();
			var nameRule = /^\w{5,12}$/;
			if (!nameRule.test(usenameText)) { //不符合命名规范
				$(".errorMsg").text("用户名格式不正确");
				return false;

			}

			//功能2：获取输入的用户密码，正则表达式，test()判断，错误给提示
			var passwordText = $("input[name='password']").val();
			var passwordRule = /^\w{5,12}$/;
			if (!passwordRule.test(passwordText)) {
				$(".errorMsg").text("用户密码格式不正确")
				return false;
			}
		})
	})

</script>
</head>
<body>
<%--		<div id="login_header">--%>
<%--<!--			<img class="logo_img" alt="" src="../../static/img/logo.gif" >-->--%>
<%--&lt;%&ndash;			<div><h1>点点点餐</h1></div>&ndash;%&gt;--%>
<%--			--%>
<%--		</div>--%>
		<div id="header">
			<span class="wel_word">登录</span>
		</div>

			<div class="login_banner">
			
<%--				<div id="l_content">--%>
<%--					<span class="login_word">欢迎登录</span>--%>
<%--				</div>--%>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>点点点餐会员</h1>
								<a href="pages/user/regist.jsp">立即注册</a>
							</div>
							<div class="msg_cont">
								<b></b>
								<span class="errorMsg">${empty requestScope.errorMsg?"请输入用户名和密码":requestScope.errorMsg}</span>

							</div>
							<div class="form">
								<form action="userServlet" method="post">
									<input type="hidden" name="action" value="login">
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username"
										   value="${requestScope.uesrname}" />
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password"
										   value="${requestScope.password}" />
									<br />
									<br />
									<input type="submit" value="登录" id="sub_btn" />
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<%@include file="/pages/common/footer.jsp"%>
</body>
</html>