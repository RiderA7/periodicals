<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="messages"/>
<html>
<fmt:message key="title.general.topics" var="title"/>
<%--<c:set var="title" value="Topics <i class='las la-battery-half'></i>" scope="page"/>--%>
<%@ include file="../inc/head.jsp" %>
<body>
<%@ include file="../inc/header.jsp" %>
<c:if test="${sessionScope.user.roleId == 2}">
    <form action="topics" method="post">
        <input class="btn btn-secondary" type="submit" name="add" value="<fmt:message key="general.topic.add"/>">
    </form>
</c:if>
<table class="table">
    <tr>
        <th><fmt:message key="general.topic.table.topic"/></th>
        <th><fmt:message key="general.topic.table.publications"/></th>
        <th><fmt:message key="general.topic.table.misc"/></th>
    </tr>
    <c:forEach var="topic" items="${topics}">
        <tr>
            <td>${topic.name}</td>
            <td>${topic.pubs}</td>
            <td>
                <c:if test="${sessionScope.user.roleId == 2}">
                    <form action="topics" method="post">
                        <input type="hidden" name="topicId" value="${topic.id}">
                        <input type="hidden" name="edit">
                        <input class="btn btn-success" type="submit"
                               value="<fmt:message key="general.topic.table.edit"/>">
                    </form>
                </c:if>
                <c:if test="${topic.pubs!=0}">
                    <form action="topics" method="post">
                        <input type="hidden" name="topicId" value="${topic.id}">
                        <input type="hidden" name="open">
                        <input class="btn btn-info" type="submit"
                               value="<fmt:message key="general.topic.table.open"/>">
                    </form>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>
<%@ include file="../inc/paging.jsp" %>
<%@ include file="../inc/footer.jsp" %>
</body>
</html>
