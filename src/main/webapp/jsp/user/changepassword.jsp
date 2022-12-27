<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<c:set var="title" value="Change password" scope="page"/>
<%@ include file="../inc/head.jsp" %>
<body>
<%@ include file="../inc/menu.jsp" %>
<%@ include file="../inc/header.jsp" %>
<c:if test="${user ne null}">
    <div>${user}</div>
    <div>
        <form class="loginform"
              action="${pageContext.request.contextPath}/account/updatepass"
              method="post">
            <label for="password">New password:</label>
            <input name="password" id="password" class="loginlogin" placeholder="Enter new password" type="password">
            <br>
            <label for="password2">Confirm password:</label>
            <input name="password2" id="password2" class="loginlogin" placeholder="Confirm password" type="password">
            <br>
            <input type="submit" class="loginbutton" value="Change password">
        </form>
    </div>
</c:if>
</body>
</html>
