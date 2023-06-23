<%--
  Created by IntelliJ IDEA.
  User: 徐亚萍
  Date: 2023/6/14
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="page_nav">
    <c:if test="${requestScope.page.pageNo>1}">
        <a href="${requestScope.page.url}&pageNo=1">首页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
    </c:if>

    <%--		以下是动态页面跳转的开始--%>
    <%--		需求：显示 5 个连续的页码，而且当前页码在中间。除了当前页码之外，每个页码都可以点击跳到指定页。--%>
    <%--		情况 1：如果总页码小于等于 5 的情况，页码的范围是：1-总页码--%>
    <%--		情况 2：总页码大于 5 的情况。假设一共 10 页--%>
    <%--		小情况 1：当前页码为前面 3 个：1，2，3 的情况，页码范围是：1-5.--%>
    <%--		小情况 2：当前页码为最后 3 个，8，9，10，页码范围是：总页码减 4 - 总页码--%>
    <%--		小情况 3：4，5，6，7，页码范围是：当前页码减 2 - 当前页码加 2--%>
    <c:choose>
        <c:when test="${requestScope.page.pageTotal<6}">
            <c:set var="begin" value="1"></c:set>
            <c:set var="end" value="${requestScope.page.pageTotal}"></c:set>
        </c:when>
        <c:when test="${requestScope.page.pageTotal>5}">
            <c:choose>
                <c:when test="${requestScope.page.pageNo<4}">
                    <c:set var="begin" value="1"></c:set>
                    <c:set var="end" value="5"></c:set>
                </c:when>

                <c:when test="${requestScope.page.pageNo>requestScope.page.pageTotal-3}">
                    <c:set var="begin" value="${requestScope.page.pageTotal-4}"></c:set>
                    <c:set var="end" value="${requestScope.page.pageTotal}"></c:set>
                </c:when>

                <c:otherwise>
                    <c:set var="begin" value="${requestScope.page.pageNo-2}"></c:set>
                    <c:set var="end" value="${requestScope.page.pageNo+2}"></c:set>
                </c:otherwise>

            </c:choose>

        </c:when>
    </c:choose>
    <c:forEach begin="${begin}" end="${end}" var="i">
        <c:if test="${i==requestScope.page.pageNo}">
            【${i}】
        </c:if>
        <c:if test="${i!=requestScope.page.pageNo}">
            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
        </c:if>

    </c:forEach>

    <%--		动态页面跳转的结束--%>

    <c:if test="${requestScope.page.pageNo< requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>

    共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录
    到第<input value="${requestScope.page.pageNo}" name="pn" id="pn_input"/>页
    <input id="searchPageBtn" type="button" value="确定">
    <script type="text/javascript">
        $(function () {
            $("#searchPageBtn").click(function () {

                var pageNo = $("#pn_input").val();
                //javaScript语言中提供了一个location 地址栏对象
                //它有一个href属性，可以获取浏览器地址栏中的地址，并且可读可写,即可以更改浏览器地址的值
                location.href = "${pageScope.basePath}${requestScope.page.url}&pageNo=" + pageNo;

            })
        })
    </script>

</div>
