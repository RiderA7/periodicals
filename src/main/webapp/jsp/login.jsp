<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<c:set var="title" value="Login" scope="page"/>
<%@ include file="inc/head.jsp" %>
<body>
<%@ include file="inc/menu.jsp" %>
<c:if test="${err ne null}">
    <p class="login_error">${err}</p>
</c:if>
<div>
    <form class="loginform"
          action="${pageContext.request.contextPath}/account/login"
          method="post">
        <label for="login">Input your login:</label>
        <input name="login" id="login" class="loginlogin" placeholder="Your Login">
        <br>
        <label for="password">Enter your password:</label>
        <input name="password" id="password" class="loginpassword" type="password" placeholder="Your Password">
        <input type="submit" class="loginbutton" value="Login">
    </form>
    <p class="login_remainder">Don't have an account? <a href="${pageContext.request.contextPath}/account/register">Sign
        Up</a></p>
</div>
</body>
</html>
