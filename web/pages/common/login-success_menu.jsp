<%--
  Created by IntelliJ IDEA.
  User: 徐亚萍
  Date: 2023/6/9
  Time: 22:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <span>欢迎<span class="um_span">${sessionScope.loginUser.username}</span>光临点点点餐餐馆</span>
    <a href="client/orderServlet?action=myOrders">我的订单</a>
    <a href="userServlet?action=loginOut">注销</a>&nbsp;&nbsp;
    <a href="index.jsp">返回</a>
</div>
