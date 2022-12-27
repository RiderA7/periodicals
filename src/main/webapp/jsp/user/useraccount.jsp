<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<c:set var="title" value="User Account" scope="page"/>
<%@ include file="../inc/head.jsp" %>
<body>
<%@ include file="../inc/menu.jsp" %>
<%@ include file="../inc/header.jsp" %>
<c:if test="${sessionScope.user ne null}">
    <div class="greetings">Hello ${sessionScope.user.name}</div>
</c:if>
</body>
</html>
