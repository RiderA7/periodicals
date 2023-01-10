<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<c:set var="title" value="Publications Admin Page <i class='las la-battery-two-quarters'></i>" scope="page"/>
<%@ include file="../inc/head.jsp" %>
<body>
<%@ include file="../inc/menu.jsp" %>
<%@ include file="../inc/header.jsp" %>
${sessionScope.publication}
    <form class="loginform"
          action="${pageContext.request.contextPath}/publications"
          method="post">
        <input type="hidden" name="action" value="${sessionScope.action}">
        <input type="hidden" name="id" value="${sessionScope.publication.id}">
        <label for="publicationName">New Publication name:</label>
        <input name="publicationName" id="publicationName" class="loginlogin" value="${sessionScope.publication.name}">
        <br>
        <label for="publicationPrice">Price:</label>
        <input name="publicationPrice" id="publicationPrice" class="loginlogin" value="${sessionScope.publication.price}">
        <br>
        <label for="topicId">Topic:</label>
        <select id="topicId" name="topicId">
<c:forEach var="topic" items="${sessionScope.topics}">
    <option <c:if test="${topic.id == sessionScope.publication.topic.id}">selected</c:if> value="${topic.id}">
    ${topic.name}
    </option>
</c:forEach>
</select>
        <br>
        <input type="submit" class="loginbutton" value="Update Publication">
    </form>
</body>
</html>
