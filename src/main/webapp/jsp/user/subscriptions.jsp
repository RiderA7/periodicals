<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="messages"/>
<html>
<fmt:message key="user.subscriptions.title" var="title"/>
<%--<c:set var="title" value="Subscriptions <i class='las la-battery-one-quarters'></i>" scope="page"/>--%>
<%@ include file="../inc/head.jsp" %>
<body>
<%@ include file="../inc/header.jsp" %>
${subscriptions}<br>
    <table class="table">
        <tr>
            <th><fmt:message key="user.subscriptions.table.title"/>Title</th>
            <th><fmt:message key="user.subscriptions.table.topic"/>Topic</th>
            <th><fmt:message key="user.subscriptions.table.price"/>Price</th>
            <th><fmt:message key="user.subscriptions.table.misc"/>Misc</th>
        </tr>
        <c:forEach var="sub" items="${subscriptions}">
            <tr>
                <td>${sub.publication.name}</td>
                <td>${sub.publication.topic}</td>
                <td>${sub.publication.price}</td>
                <td>
                </td>
            </tr>
        </c:forEach>
    </table>
    <%@ include file="../inc/paging.jsp" %>
    <%@ include file="../inc/footer.jsp" %>
</body>
</html>
