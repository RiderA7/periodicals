<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="messages"/>
<html>
<c:set var="title" value="User Profile" scope="page"/>
<%@ include file="../inc/head.jsp" %>
<body>
<%@ include file="../inc/header.jsp" %>
${user}
<div class="profile">
    <div class="profile-userlogin">Your login: <span>${user.login}</span></div>
    <div class="profile-username">Your name: <span>${user.name}</span></div>
    <a href="${pageContext.request.contextPath}/account/update">Update Profile</a>
    <br>
    <a href="${pageContext.request.contextPath}/account/updatepass">Change password</a>
    <div class="profile-money">Your balance: <span>${user.money}</span></div>
    <a href="${pageContext.request.contextPath}/account/replenish">Replenish balance</a>
</div>
<%@ include file="../inc/footer.jsp" %>
</body>
</html>
