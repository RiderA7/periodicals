<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="messages"/>
<html>
<c:set var="title" value="User Account" scope="page"/>
<%@ include file="../inc/head.jsp" %>
<body>
<%@ include file="../inc/header.jsp" %>
<c:if test="${sessionScope.user ne null}">
    <div class="greetings">Hello ${sessionScope.user.name}</div>
</c:if>
<%@ include file="../inc/footer.jsp" %>
</body>
</html>
