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
<%--${subscriptions}<br>--%>
    <table class="table">
        <tr>
            <th><fmt:message key="user.subscriptions.table.title"/></th>
            <th><fmt:message key="user.subscriptions.table.topic"/></th>
            <th><fmt:message key="user.subscriptions.table.price"/></th>
            <th><fmt:message key="user.subscriptions.table.misc"/></th>
        </tr>
        <c:forEach var="sub" items="${subscriptions}">
            <tr>
                <td>${sub.publication.name}</td>
                <td>${sub.publication.topic.name}</td>
                <td>${sub.publication.price}</td>
                <td>
                </td>
            </tr>
        </c:forEach>
    </table>
    <%@ include file="../inc/paging.jsp" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<tags:footer />
</body>
</html>
