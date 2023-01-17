<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="messages"/>
<html>
<fmt:message key="title.general.login" var="title"/>
<%--<c:set var="title" value="Login" scope="page"/>--%>
<%@ include file="../inc/head.jsp" %>
<body>
<%@ include file="../inc/header.jsp" %>
<div>
    <form class="row g-3"
          action="${pageContext.request.contextPath}/account/login"
          method="post">
        <div class="col-md-12">
            <div class="form-floating">
                <input name="login" id="login"
                       class="form-control" placeholder="<fmt:message key="general.login.login.placeholder"/>">
                <label for="login"><fmt:message key="general.login.login.label"/>:</label>
            </div>
        </div>
        <div class="col-md-12">
            <div class="form-floating">
                <input name="password" id="password"
                       class="form-control" type="password" placeholder="<fmt:message key="general.login.password.placeholder"/>">
                <label for="password"><fmt:message key="general.login.password.label"/>:</label>
            </div>
        </div>
        <input type="submit" class="btn btn-primary" value="Login">
    </form>
    <div class="alert alert-light border-light alert-dismissible fade show" role="alert">
        <i class="bi bi-collection me-1"></i><fmt:message key="general.login.register1"/>
        <a href="${pageContext.request.contextPath}/account/register"><fmt:message key="general.login.register2"/></a>
    </div>
</div>
<%@ include file="../inc/footer.jsp" %>
</body>
</html>
