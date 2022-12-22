<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User Account</title>
</head>
<body>
<%@ include file="inc/menu.jsp" %>
<h1>User Account</h1>
<c:if test="${sessionScope.user ne null}">
    <div class="greetings">Hello ${sessionScope.user.name}</div>
</c:if>
</body>
</html>
