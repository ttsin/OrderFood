<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>菜品管理</title>
    <%--	静态包含 base标签、css样式、jQuery文件--%>
    <%@include file="/pages/common/head.jsp" %>
    <script type="text/javascript">
        $(function () {
            $(".deleteClass").click(function () {
                //在事件的function函数中，有一个this对象，这个this对象，是当前正在响应事件的dom对象
                //什么是dom:就是把文档中的标签，属性，文本，转换成为对象来管理
                //当前正在响应的dom对象是<a class="deleteClass"href="manager/foodServlet?action=deleteFood&id=${food.id}">删除</a>,
                // 即<a></a>标签

                // 当点击删除时，有弹窗弹出，是否确认删除
                /**
                 * confirm() 确认提示框，参数是它的提示内容
                 * 当点击确认，返回true，点击取消，返回flase
                 */
                return confirm("你确定要删除[" + $(this).parent().parent().find("td:first").text() + "]吗?")
                // return false;// 表示阻止元素的默认行为，在这里指的是点击取消后界面的跳转
                // return ture;// 不阻止点击界面的跳转
                //$(this).parent()<td>标签，$(this).parent().parent()是<tr>标签

            })
        })

    </script>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="../../static/img/logo.gif">
    <span class="wel_word">菜品管理</span>
    <%--静态包含 manager管理模块的菜单--%>
    <%@include file="/pages/common/manager_menu.jsp" %>
</div>

<div id="main">
    <table>
        <tr>
            <td>名称</td>
            <td>价格</td>
            <td>类型</td>
            <td>销量</td>
            <td>评分</td>
            <td colspan="2">操作</td>
        </tr>
        <c:forEach items="${requestScope.page.items}" var="food">
<%--            <c:if test="${'粉' eq food.type}">--%>
<%--            </c:if>--%>
            <tr>
                <td>${food.name}</td>
                <td>${food.price}</td>
                <td>${food.type}</td>
                <td>${food.sales}</td>
                <td>${food.rating}</td>
                <td><a href="manager/foodServlet?action=getFood&id=${food.id}&method=updateFood&pageNo=${requestScope.page.pageNo}">修改</a></td>
                <td><a class="deleteClass" href="manager/foodServlet?action=deleteFood&id=${food.id}&pageNo=${requestScope.page.pageNo}">删除</a></td>
            </tr>

        </c:forEach>

        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td><a href="pages/manager/food_edit.jsp?method=addFood&pageNo=${requestScope.page.pageTotal}">添加菜品</a></td>
        </tr>
    </table>

    <%--	静态包含分页条--%>
    <%@include file="/pages/common/page_nav.jsp"%>
</div>

<%@include file="/pages/common/footer.jsp" %>
</body>
</html>