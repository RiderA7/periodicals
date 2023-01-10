<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<c:set var="title" value="Topics <i class='las la-battery-three-quarters'></i>" scope="page"/>
<%@ include file="../inc/head.jsp" %>
<body>
<%@ include file="../inc/menu.jsp" %>
<%@ include file="../inc/header.jsp" %>
${topics}<br>
<c:if test="${sessionScope.user.roleId == 2}">
<form action="topics" method="post">
    <input class="btn" type="submit" name="add" value="ADD NEW TOPIC">
</form>
</c:if>
<table>
    <tr>
        <th>Topic</th>
        <th>Publications</th>
        <th>Misc</th>
    </tr>
    <c:forEach var="topic" items="${topics}">
        <tr>
            <td>${topic.name}</td>
            <td>${topic.pubs}</td>
            <td>
                <form action="topics" method="post">
                    <input type="hidden" name="topicId" value="${topic.id}">
                    <c:if test="${sessionScope.user.roleId == 2}">
                        <input class="btn" type="submit" name="edit" value="EDIT">
                    </c:if>
                    <c:if test="${topic.pubs!=0}">
                        <input class="btn" type="submit" name="open" value="OPEN">
                    </c:if>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<%@ include file="../inc/paging.jsp" %>
</body>
</html>
