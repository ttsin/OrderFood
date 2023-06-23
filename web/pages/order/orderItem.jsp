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
	<script type="text/javascript">
		$(function () {
			//给评分输入框绑上onchange（改变事件）
			//当输入框的的内容发生变化时，弹出是否修改的弹窗，确定执行，取消就把其中的值改成0
			//输入的是数字，并且在0-10之间
			$('.ratingInput').change('input', function() {
				// 获取输入的值
				var input = $(this).val();
				// 检查输入是否为数字
				if (!$.isNumeric(input)) {
					$(this).val('');
					confirm("请输入数字")
					return;
				}
				// 转换为数字并检查是否在0到10之间
				var number = parseFloat(input);
				if (number < 0 || number > 10) {
					$(this).val('');
					confirm("请输入0到10之间的数字")
					return;

				}
				var name = $(this).parent().parent().find("td:first").text();
				if(confirm("你确定要把【"+name+"】的评分改成【"+number+"】吗")){
					//确定，跳转到
					var orderItemName = $(this).attr("orderItemName");
					location.href="${pageScope.basePath}client/foodServlet?action=ratingFood&foodName="+orderItemName+"&rating="+number;
				}else{
					//取消
					$(this).val('');

				}

			})

		})
	</script>
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
			<span class="wel_word">订单详情</span>
		<%@include file="/pages/common/login-success_menu.jsp"%>
	</div>
	
	<div id="main">
		
		<table>
			<tr>
				<td>商品名称</td>
				<td>单价</td>
				<td>数量</td>
				<td>总价</td>
				<c:if test="${param.status==2}">
					<td>评分</td>
				</c:if>
			</tr>
			<c:if test="${not empty requestScope.orderItems}">
				<c:forEach items="${requestScope.orderItems}" var="orderItem">
					<tr>
						<td>${orderItem.name}</td>
						<td>${orderItem.price}</td>
						<td>${orderItem.count}</td>
						<td>${orderItem.totalPrice}</td>
						<c:if test="${param.status==2}">
							<td><input type="text" orderItemName="${orderItem.name}" style="width: 50px;" class="ratingInput"></td>
						</c:if>
					</tr>
				</c:forEach>
			</c:if>
		</table>

	</div>

	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>