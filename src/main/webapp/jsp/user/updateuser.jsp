<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="messages"/>
<html>
<fmt:message key="user.update.title" var="title"/>
<%--<c:set var="title" value="Update User" scope="page"/>--%>
<%@ include file="../inc/head.jsp" %>
<body>
<%@ include file="../inc/header.jsp" %>
<c:if test="${user ne null}">
    <%--    <div>${user}</div>--%>
    <div>
        <form class="row g-2"
              action="${pageContext.request.contextPath}/account/update"
              method="post">
                <%--            <input type="hidden" name="id" value="${user.id}">--%>
            <div class="col-md-12">
                <div class="form-floating">
                    <input name="login" id="login" class="form-control" value="${user.login}">
                    <label for="login"><fmt:message key="user.update.login"/>:</label>
                </div>
            </div>
            <div class="col-md-12">
                <div class="form-floating">
                    <input name="name" id="name" class="form-control" value="${user.name}">
                    <label for="name"><fmt:message key="user.update.name"/>:</label>
                </div>
            </div>
            <input type="submit" class="btn btn-outline-primary" value="<fmt:message key="user.update.button"/>">
        </form>
    </div>
</c:if>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<tags:footer />
</body>
</html>
