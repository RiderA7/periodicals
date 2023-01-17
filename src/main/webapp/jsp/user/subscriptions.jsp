<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="messages"/>
<html>
<c:set var="title" value="Subscriptions <i class='las la-battery-one-quarters'></i>" scope="page"/>
<%@ include file="../inc/head.jsp" %>
<body>
<%@ include file="../inc/header.jsp" %>
${publications}<br>
<c:if test="${sessionScope.user.roleId == 2}">
    <form action="publications" method="post">
        <input class="btn" type="submit" name="add" value="ADD NEW PUBLICATION">
    </form>
</c:if>
<c:if test="${sessionScope.activeTopic ne null}">
<form action="publications" method="post">
    <div class="bar info">Current topic: ${sessionScope.activeTopic.name}
        <button class="btn" name="removeTopic" value="removeTopic" title="remove">
            <i class="las la-times"></i>
        </button>
    </div>
    </c:if>
    <table>
        <tr>
            <th>Title</th>
            <th>Topic</th>
            <th>Price</th>
            <th>Misc</th>
        </tr>
        <c:forEach var="pub" items="${publications}">
            <tr>
                <td>${pub.name}</td>
                <td>${pub.topic.name}</td>
                <td>${pub.price}</td>
                <td>
                    <c:if test="${sessionScope.user.roleId == 2}">
                        <form action="publications" method="post">
                            <input type="hidden" name="pubId" value="${pub.id}">
                            <input class="btn" type="submit" name="edit" value="EDIT">
                        </form>
                    </c:if>
                    <c:if test="${sessionScope.user.roleId == 1}">
                        <form action="account/subscribe" method="post">
                            <input type="hidden" name="pubId" value="${pub.id}">
                            <input class="btn" type="submit" name="subscribe" value="SUBSCRIBE">
                        </form>
                    </c:if>
                    <form action="publications" method="post">
                        <input type="hidden" name="pubId" value="${pub.id}">
                        <input class="btn" type="submit" name="view" value="VIEW">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <%@ include file="../inc/paging.jsp" %>
    <%@ include file="../inc/footer.jsp" %>
</body>
</html>