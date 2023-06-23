<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>编辑菜品</title>
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
	
	input {
		text-align: center;
	}
</style>
</head>
<body>
		<div id="header">
			<img class="logo_img" alt="" src="../../static/img/logo.gif" >
			<span class="wel_word">编辑菜品</span>
			<%--静态包含 manager管理模块的菜单--%>
			<%@include file="/pages/common/manager_menu.jsp"%>
		</div>
		
		<div id="main">
			<form action="manager/foodServlet" method="get">
				<input type="hidden" name="pageNo" value="${param.pageNo}">
				<input type="hidden" name="pageTotal" value="${param.pageNo}">
				<input type="hidden" name="action" value="${param.method}">
				<input type="hidden" name="id" value="${requestScope.food.id}">
				<table>
					<tr>
						<td>名称</td>
						<td>价格</td>
						<td>类型</td>
						<td>销量</td>
						<td>评分</td>
						<td colspan="2">操作</td>
					</tr>		
					<tr>
						<td><input name="name" type="text" value="${requestScope.food.name}"/></td>
						<td><input name="price" type="text" value="${requestScope.food.price}"/></td>
						<td><input name="type" type="text" value="${requestScope.food.type}"/></td>
						<td><input name="sales" type="text" value="${requestScope.food.sales}"/></td>
						<td><input name="rating" type="text" value="${requestScope.food.rating}"/></td>
						<td><input type="submit" value="提交"/></td>
					</tr>	
				</table>
			</form>
			
	
		</div>

		<%@include file="/pages/common/footer.jsp"%>
</body>
</html>