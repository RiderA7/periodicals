<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="messages"/>
<html>
<fmt:message key="title.user.profile" var="title"/>
<%--<c:set var="title" value="User Profile" scope="page"/>--%>
<%@ include file="../inc/head.jsp" %>
<body>
<%@ include file="../inc/header.jsp" %>
<%--${user}--%>
<section class="section profile">
    <div class="tab-pane fade show active profile-overview" id="profile-overview">
        <h5 class="card-title"><fmt:message key="user.profile.details"/></h5>
        <div class="row">
            <div class="col-lg-3 col-md-4 label "><fmt:message key="user.profile.login"/></div>
            <div class="col-lg-9 col-md-8">${user.login}</div>
        </div>
        <div class="row">
            <div class="col-lg-3 col-md-4 label"><fmt:message key="user.profile.name"/></div>
            <div class="col-lg-9 col-md-8">${user.name}</div>
        </div>
        <div class="row">
            <a href="${pageContext.request.contextPath}/account/update"
               class="btn btn-outline-primary"><fmt:message key="user.profile.update"/></a>
        </div>
        <div class="row">
            <div class="col-lg-3 col-md-4 label"><fmt:message key="user.profile.balance"/></div>
            <div class="col-lg-9 col-md-8">${user.money}</div>
        </div>
        <div class="row">
            <a href="${pageContext.request.contextPath}/account/replenish"
               class="btn btn-outline-primary"><fmt:message key="user.profile.replenish"/></a>
        </div>
    </div>
</section>
<%@ include file="../inc/footer.jsp" %>
</body>
</html>
