<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>后台管理</title>
	<%--	静态包含 base标签、css样式、jQuery文件--%>
	<%@include file="/pages/common/head.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;

	}

</style>
</head>
<body>
	<div id="bak">

	<div id="header">

			<span class="wel_word">后台管理</span>
		<%--静态包含 manager管理模块的菜单--%>
		<%@include file="/pages/common/manager_menu.jsp"%>
	</div>
	
	<div id="main">
		<h1>欢迎管理员进入后台管理界面</h1>
	</div>
	</div>

	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>