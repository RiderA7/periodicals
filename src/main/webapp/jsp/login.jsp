<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<c:set var="title" value="Login" scope="page"/>
<head>
    <title>${title}</title>
</head>
<body>
<c:out value="16+64*2"/>=<c:out value="${16+64*2}"/>
<c:if test="${param.err != null}">
    <p class="ls_err">You entered wrong login or password</p>
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
    <p class="login_remainder">Don't have an account? <a href="${pageContext.request.contextPath}/account/signup">Sign
        Up</a></p>
</div>
</body>
</html>
