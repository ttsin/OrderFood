<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%--	静态包含 base标签、css样式、jQuery文件--%>
	<%@include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function () {
			//给删除操作绑上单击事件
			$("a.deleteItem").click(function () {
				return confirm("你确定要删除【"+$(this).parent().parent().find("td:first").text()+"】吗？")
			})
			//给清空购物车操作绑上单击事件
			$("#cartClean").click(function () {
				return confirm("你确定要清空购物车吗？")
			})
			//1.文本输入框，绑定失去焦点事件blur，（或者是onchange事件），
			// 2.弹出提示框，是否要更改，
			// 3.确定（就跳转到cartServlet?action=updateCount），取消（就把文本框改回原来的值）
			//(需要判断，输入的数字是否和之前的一样，不一样，触发失去焦点blur事件,即当从文本框中离开，就会触发事件)
			//onchange事件，onchange 事件是在用户改变 输入字段的内容 并且 失去焦点（即点击其他地方）时触发的事件
			$(".updateCount").change(function () {
				var name = $(this).parent().parent().find("td:first").text();
				var count = $(this).val();
				if(confirm("你确定要修改【"+name+"】的数量为：【"+count+"】吗？")){
					//确定
					//请求转发
					var id = $(this).attr("foodId");
					location.href="${pageScope.basePath}cartServlet?action=updateCount&count="+count+"&id="+id;

				}else{
					//取消
					//defaultValue 属性是表单项Dom对象的属性，它表示默认的value属性值（即<input value="">）
					this.value=this.defaultValue;

				}
			})


		})
	</script>
</head>
<body>
	
	<div id="header">

			<span class="wel_word">购物车</span>
		<%@include file="/pages/common/login-success_menu.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
<%--			购物车不是空的--%>
			<c:if test="${not empty sessionScope.cart.items}">
				<c:forEach items="${sessionScope.cart.items}" var="entry">
					<tr>
						<td>${entry.value.name}</td>
						<td><input style="width: 50px" type="text" foodId="${entry.value.id}" class="updateCount" value="${entry.value.count}"></td>
						<td>${entry.value.price}</td>
						<td>${entry.value.totalPrice}</td>
						<td><a class="deleteItem" href="cartServlet?action=deleteItem&id=${entry.value.id}">删除</a></td>
					</tr>
				</c:forEach>
			</c:if>
<%--			购物车是空的--%>
			<c:if test="${empty sessionScope.cart.items}">
				<tr>
					<td colspan="5"><a href="${pageScope.basePath}">空空如也~去首页转转吧</a></td>
				</tr>
			</c:if>
		</table>
<%--		购物车不是空时，才输出下面的--%>
		<c:if test="${not empty sessionScope.cart.items}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
				<span class="cart_span"><a id="cartClean" href="cartServlet?action=clean">清空购物车</a></span>
				<span class="cart_span"><a href="client/orderServlet?action=createOrder">去结账</a></span>
			</div>
		</c:if>

	</div>

	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>