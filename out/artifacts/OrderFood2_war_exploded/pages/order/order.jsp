<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
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
	
	<div id="header">
			<img class="logo_img" alt="" src="../../static/img/logo.gif" >
			<span class="wel_word">我的订单</span>
		<%@include file="/pages/common/login-success_menu.jsp"%>
	</div>
	
	<div id="main">
		
		<table>
			<tr>
				<td>订单编号</td>
				<td>日期</td>
				<td>金额</td>
				<td>发货</td>
				<td>详情</td>
				<td>去评分</td>
			</tr>
			<c:if test="${empty requestScope.myOrders}">
				<tr>
					<td colspan="5"><a href="index.jsp">订单空空如也，去首页逛逛吧</a></td>
				</tr>
			</c:if>
			<c:if test="${not empty requestScope.myOrders}">
				<c:forEach items="${requestScope.myOrders}" var="order">
					<tr>
						<td>${order.orderId}</td>
						<td><fmt:formatDate value="${order.createTime}" pattern="yyyy-MM-dd" /></td>
						<td>${order.price}</td>
						<c:if test="${order.status==0}">
							<td>未发货</td>
						</c:if>
						<c:if test="${order.status==1}">
							<td><a href="client/orderServlet?action=receiveOrder&orderId=${order.orderId}">点击收货</a></td>
						</c:if>
						<c:if test="${order.status==2}">
							<td>已签收</td>
						</c:if>

						<td><a href="client/orderServlet?action=orderDetails&orderId=${order.orderId}">查看详情</a></td>
						<c:if test="${order.status!=2}">
							<td></td>
						</c:if>
						<c:if test="${order.status==2}">
<%--					跳到订单详情页--%>
							<td><a href="client/orderServlet?action=orderDetails&orderId=${order.orderId}&status=${order.status}">去评分</a></td>
						</c:if>
					</tr>
				</c:forEach>

			</c:if>

		</table>
		
	
	</div>

	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>