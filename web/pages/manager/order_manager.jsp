<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
	<%--	静态包含 base标签、css样式、jQuery文件--%>
	<%@include file="/pages/common/head.jsp"%>
</head>
<body>
	
	<div id="header">
<%--			<img class="logo_img" alt="" src="../../static/img/logo.gif" >--%>
			<span class="wel_word">订单管理</span>
			<%--静态包含 manager管理模块的菜单--%>
			<%@include file="/pages/common/manager_menu.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>订单编号</td>
				<td>日期</td>
				<td>金额</td>
				<td>详情</td>
				<td>发货</td>
				
			</tr>
			<c:forEach items="${requestScope.allOrders}" var="order">
				<tr>
					<td>${order.orderId}</td>
					<td><fmt:formatDate value="${order.createTime}" pattern="yyyy-MM-dd" /></td>
					<td>${order.price}</td>
					<td><a href="client/orderServlet?action=orderDetails&orderId=${order.orderId}">查看详情</a></td>
					<c:if test="${order.status==0}">
						<td><a href="manager/orderServlet?action=sendOrder&orderId=${order.orderId}">点击发货</a></td>
					</c:if>
					<c:if test="${order.status==1}">
						<td>等待客户收货</td>
					</c:if>
					<c:if test="${order.status==2}">
						<td>客户已收货</td>
					</c:if>

				</tr>

			</c:forEach>


		</table>
	</div>

	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>