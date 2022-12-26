<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<c:set var="title" value="Register" scope="page"/>
<%@ include file="../inc/head.jsp" %>
<body>
<%@ include file="../inc/menu.jsp" %>
<c:if test="${err ne null}">
    <p class="login_error">${err}</p>
</c:if>
<div>
    <form class="loginform"
          action="${pageContext.request.contextPath}/account/register"
          method="post">
        <label for="login">Input your login:</label>
        <input name="login" id="login" class="loginlogin" placeholder="Your Login">
        <br>
        <label for="password">Enter your password:</label>
        <input name="password" id="password" class="loginpassword" type="password" placeholder="Your Password">
        <br>
        <label for="password2">Repeat your password:</label>
        <input name="password2" id="password2" class="loginpassword" type="password" placeholder="Repeat Password">
        <br>
        <label for="name">Input your name:</label>
        <input name="name" id="name" class="loginlogin" placeholder="Your Name">
        <br>
        <input type="submit" class="loginbutton" value="Register">
    </form>
</div>
</body>
</html>
