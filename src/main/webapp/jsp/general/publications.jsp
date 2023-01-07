<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<c:set var="title" value="Publications <i class='las la-battery-three-quarters'></i>" scope="page"/>
<%@ include file="../inc/head.jsp" %>
<body>
<%@ include file="../inc/menu.jsp" %>
<%@ include file="../inc/header.jsp" %>
${publications}<br>
<c:if test="${sessionScope.user.roleId == 2}">
<form action="publications" method="post">
    <input class="btn" type="submit" name="add" value="ADD NEW PUBLICATION">
</form>
</c:if>
<table>
    <tr>
        <th>Title</th>
        <th>Topic</th>
        <th>Price</th>
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
                        <input class="btn" type="submit" name="open" value="OPEN">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<%@ include file="../inc/paging.jsp" %>
</body>
</html>
