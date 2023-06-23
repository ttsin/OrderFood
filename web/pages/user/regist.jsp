<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>点点点餐会员注册页面</title>
	<%--	静态包含 base标签、css样式、jQuery文件--%>
	<%@include file="/pages/common/head.jsp"%>
<style type="text/css">

	.login_form{
		height:420px;
		margin-top: 25px;
	}

</style>
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
<script type="text/javascript">


	/*
	* 需要做的工作：
	* 功能1：用户名要符合要求（只能由数字、字母、下划线组成，字段必须在5-12位之间）
	* 功能2：用户密码（只能由数字、字母、下划线组成，字段必须在5-12位之间）
	* 功能3：确认密码（和密码相同）
	* 功能4：邮箱验证格式符合规范（xxxxx@xxx.com(正则表达式)）
	* 功能5：验证码：验证码里面要由内容
	*
	*/
	//加载页面
	$(function () {
		//给验证码图片绑上单击事件，点击切换
		$("#code_img").click(function () {
			// function 有一个this对象，表示正在响应的dom对象，即<img>标签
			this.src="${basePath}kaptcha.jpg?d="+new Date();

		})
		//给username文本框绑上失去焦点事件
		$("#username").change(function () {

			//使用AJAX发起请求给客户端，验证用户名是否存在，能异步更新页面
			var username=this.value;
			$.getJSON("http://localhost:8080/OrderFood2/userServlet","action=ajaxExistsUsername&username="+username,function (data) {
				if (data.existUsername){
					$(".errorMsg").text("用户名已存在");
				}else{
					$(".errorMsg").text("用户名可用");
				}
			})


		})

		//给按钮绑上单击事件
		$("#sub_btn").click(function () {
			//功能1：获取用输入的名字，正则表达式，test()判断，错误给提示
			var usenameText = $("#username").val();
			var nameRule=/^\w{5,12}$/;
			if(!nameRule.test(usenameText)){ //不符合命名规范
				$(".errorMsg").text("用户名格式不正确");
				return false;

			}

			//功能2：获取输入的用户密码，正则表达式，test()判断，错误给提示
			var passwordText = $("#password").val();
			var passwordRule=/^\w{5,12}$/;
			if(!passwordRule.test(passwordText)){
				$(".errorMsg").text("用户密码格式不正确")
				return false;
			}

			//功能3：确认密码（和密码相同） 获取用户密码框，获取确认密码框，判断，错误给提示
			var repwdText = $("#repwd").val();
			if(repwdText != passwordText){
				$(".errorMsg").text("确认密码不正确")
				return false;
			}

			//功能4：邮箱验证格式符合规范 获取邮箱内容，正则表达式，比较，错误给提示
			var emailText = $("#email").val();
			var emailRule = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/
			if(!emailRule.test(emailText)){
				$(".errorMsg").text("邮箱格式不正确")
				return false;
			}

			//功能5：验证码：验证码里面要由内容,去点输入的字段前后的空格，判断不为空（null 和“  ”），
			var codeText = $("#code").val();
			codeText = $.trim(codeText);
			 if(codeText==null || codeText==""){
				 $(".errorMsg").text("验证码不能为空")
				 return false;
			 }

			 //正确，就把错误提示框置空
			$(".errorMsg").text("");



		})

	})


</script>
</head>
<body>

		<div id="header">
			<span class="wel_word">注册</span>
		</div>

		<div class="login_banner">

				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册点点点餐账号</h1>
								<span class="errorMsg">${empty requestScope.errorMsg?"":requestScope.errorMsg}</span>

							</div>
							<div class="form">
								<form action="userServlet" method="post">
									<input type="hidden" name="action" value="regist">
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名"
										   autocomplete="off" tabindex="1" name="username" id="username"
									value="${requestScope.username}"/>
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码"
										   autocomplete="off" tabindex="1" name="password" id="password"
										   value="${requestScope.password}"/>
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码"
										   autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址"
										   autocomplete="off" tabindex="1" name="email" id="email"
										   value="${requestScope.email}" />
									<br />
									<br />
									<label>验证码：</label>
<%--									<img src="http://localhost:8080/tmp/kaptcha.jpg" alt="" style="width: 100px; height: 28px;">--%>
									<input class="itxt" type="text" style="width: 140px;" name="code" id="code"/>
									<img alt="" src="kaptcha.jpg" style="float: right; width: 90px; height: 40px; margin-right: 50px" id="code_img">
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
									
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<%@include file="/pages/common/footer.jsp"%>
</body>
</html>