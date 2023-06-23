<%--
  Created by IntelliJ IDEA.
  User: 徐亚萍
  Date: 2023/6/9
  Time: 22:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--base 标签要动态获取相对路径--%>
<%
    String basePath = request.getScheme() //http
    +"://"
    +request.getServerName()  //ip地址
    +":"
    +request.getServerPort()  // 端口号 8080
    +request.getContextPath() // 项目名 OrderFood2
    +"/";
    pageContext.setAttribute("basePath",basePath);
%>

<%--<base href="http://localhost:8080/OrderFood2/">--%>
<base href="<%=basePath%>">
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
