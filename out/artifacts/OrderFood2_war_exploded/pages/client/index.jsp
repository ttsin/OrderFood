<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>点点点餐餐馆首页</title>
<%--	静态包含 base标签、css样式、jQuery文件--%>
<%@include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		//给加入购物车按钮绑上单击事件
		$(function () {
			$("button.addToCart").click(function () {
				//获取它的FoodId属性的值
				//跳转到cartServlet?action=addItem&id=FoodId
				var foodId = $(this).attr("FoodId");
				$.getJSON("${pageScope.basePath}cartServlet","action=ajaxAddItem&foodId="+foodId,function (data) {
					$(".cartTotalCount").text("您的购物车中有 " + data.totalCount + " 件商品");
					$(".cartLastName").text(data.lastName);


				})
				<%--location.href="${pageScope.basePath}cartServlet?action=addItem&foodId="+foodId;--%>
			})

		})
	</script>
</head>
<body>

	<div id="header">
			<span class="wel_word">点点点餐餐馆</span>
			<div>
				<%--用户没有登录--%>
				<c:if test="${empty sessionScope.loginUser}">
					<a href="pages/user/login.jsp">登录</a> |
					<a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
				</c:if>

				<%--用户已经登录--%>
				<c:if test="${not empty sessionScope.loginUser}">
					<span>欢迎<span class="um_span">${sessionScope.loginUser.username}</span>光临点点点餐餐馆</span>
					<a href="client/orderServlet?action=myOrders">我的订单</a>
				</c:if>
				<a href="pages/cart/cart.jsp">购物车</a>
<%--				<a href="pages/manager/manager.jsp">后台管理</a>--%>
				<c:if test="${sessionScope.loginUser.role eq 'admin'}">
					<a href="pages/manager/manager.jsp">后台管理</a>
				</c:if>
			</div>
	</div>
	<div id="main">
		<div id="book">
			<!--搜索行的开始-->
			<div class="book_cond">

				<form action="client/foodServlet" method="get" class="book_cond_from">
					<input type="hidden" name="action" value="pageByTypePrice">
					<label>菜品类型：</label>
					<select name="type">
						<option name="option1" value="全部" <c:if test="${param.type eq '全部'}">selected</c:if>>全部</option>
						<option name="option2"value="粉" <c:if test="${param.type eq '粉'}">selected</c:if> >粉</option>
						<option name="option3"value="米饭" <c:if test="${param.type eq '米饭'}">selected</c:if> >米饭</option>
						<option name="option4"value="面条" <c:if test="${param.type eq '面条'}">selected</c:if> >面条</option>
						<option name="option5"value="饺子" <c:if test="${param.type eq '饺子'}">selected</c:if> >饺子</option>
					</select>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<lable>价格：</lable>
					<input id="min" type="text" name="min" value="${param.min}"> 元 -
					<input id="max" type="text" name="max" value="${param.max}"> 元
					<input type="submit" value="查询" />
				</form>

			</div>
<%--			购物车为空--%>
			<c:if test="${empty sessionScope.cart}">
			<div style="text-align: center">
				<span class="cartTotalCount"> </span>
				<div>
					<span style="color: red" class="cartLastName">当前购物车为空</span>
				</div>
			</div>
			</c:if>
<%--			购物车非空--%>
			<c:if test="${not empty sessionScope.cart}">
				<div style="text-align: center">
					<span class="cartTotalCount"></span>
					<div>
						您刚刚将<span class="cartLastName" style="color: red"></span>加入到了购物车中
					</div>
				</div>
			</c:if>

			<%--循环显示数据--%>
			<c:forEach items="${requestScope.page.items}" var="food">
			<div class="b_list">
				<div class="img_div">
					<img class="book_img" alt="" src="${food.imgPath}" />
				</div>
				<div class="book_info">
					<div class="book_name">
						<span class="sp1">菜名:</span>
						<span class="sp2">${food.name}</span>
					</div>
					<div class="book_author">
						<span class="sp1">类型:</span>
						<span class="sp2">${food.type}</span>
					</div>
					<div class="book_price">
						<span class="sp1">价格:</span>
						<span class="sp2">${food.price}</span>
					</div>
					<div class="book_sales">
						<span class="sp1">销量:</span>
						<span class="sp2">${food.sales}</span>
					</div>
					<div class="book_amount">
						<span class="sp1">评分:</span>
						<span class="sp2">${food.rating}</span>
					</div>
					<div class="book_add">
						<button FoodId="${food.id}" class="addToCart">加入购物车</button>
					</div>
				</div>
			</div>
			</c:forEach>

		</div>
		<%--	静态包含分页条--%>
		<%@include file="/pages/common/page_nav.jsp"%>
	
	</div>

	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>