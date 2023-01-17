<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="messages"/>
<html>
<fmt:message key="title.general.register" var="title"/>
<%--<c:set var="title" value="Register" scope="page"/>--%>
<%@ include file="../inc/head.jsp" %>
<body>
<%@ include file="../inc/header.jsp" %>
<div>
    <form class="row g-3"
          action="${pageContext.request.contextPath}/account/register"
          method="post">
        <div class="col-md-12">
            <div class="form-floating">
                <input name="login" id="login"
                       class="form-control" placeholder="<fmt:message key="general.register.login.placeholder"/>">
                <label for="login"><fmt:message key="general.register.login.label"/>:</label>
            </div>
        </div>
        <div class="col-md-12">
            <div class="form-floating">
                <input name="password" id="password"
                       class="form-control" type="password" placeholder="<fmt:message key="general.register.password.placeholder"/>">
                <label for="password"><fmt:message key="general.register.password.label"/>:</label>
            </div>
        </div>
        <div class="col-md-12">
            <div class="form-floating">
                <input name="password2" id="password2"
                       class="form-control" type="password" placeholder="<fmt:message key="general.register.password2.placeholder"/>">
                <label for="password2"><fmt:message key="general.register.password2.label"/>:</label>
            </div>
        </div>
        <div class="col-md-12">
            <div class="form-floating">
                <input name="name" id="name"
                       class="form-control" placeholder="<fmt:message key="general.register.name.placeholder"/>">
                <label for="name"><fmt:message key="general.register.name.label"/>:</label>
            </div>
        </div>
        <input type="submit" class="btn btn-primary" value="<fmt:message key="general.register.button"/>">
    </form>
</div>
<%@ include file="../inc/footer.jsp" %>
</body>
</html>
