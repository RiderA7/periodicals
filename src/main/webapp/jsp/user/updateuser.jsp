<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="messages"/>
<html>
<c:set var="title" value="Update User" scope="page"/>
<%@ include file="../inc/head.jsp" %>
<body>
<%@ include file="../inc/header.jsp" %>
<c:if test="${user ne null}">
    <div>${user}</div>
    <div>
        <form class="loginform"
              action="${pageContext.request.contextPath}/account/update"
              method="post">
<%--            <input type="hidden" name="id" value="${user.id}">--%>
            <label for="login">New User login:</label>
            <input name="login" id="login" class="loginlogin" value="${user.login}">
            <br>
            <label for="name">New User name:</label>
            <input name="name" id="name" class="loginlogin" value="${user.name}">
            <br>
            <input type="submit" class="loginbutton" value="Update User">
        </form>
    </div>
</c:if>
<%@ include file="../inc/footer.jsp" %>
</body>
</html>
