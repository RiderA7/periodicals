<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="messages"/>
<html>
<fmt:message key="title.user.change.password" var="title"/>
<%--<c:set var="title" value="Change password" scope="page"/>--%>
<%@ include file="../inc/head.jsp" %>
<body>
<%@ include file="../inc/header.jsp" %>
<c:if test="${user ne null}">
<%--    <div>${user}</div>--%>
    <div>
        <form class="row g-2"
              action="${pageContext.request.contextPath}/account/updatepass"
              method="post">
            <div class="col-md-12">
                <div class="form-floating">
                    <input name="password" id="password"
                           class="form-control" placeholder="<fmt:message key="user.change.password"/>" type="password">
                    <label for="password"><fmt:message key="user.change.password.label"/>:</label>
                </div>
            </div>
            <div class="col-md-12">
                <div class="form-floating">
                    <input name="password2" id="password2"
                           class="form-control" placeholder="<fmt:message key="user.change.password"/>" type="password">
                    <label for="password2"><fmt:message key="user.change.password2.label"/>:</label>
                </div>
            </div>
            <input type="submit" class="btn btn-primary" value="<fmt:message key="user.change.password.button"/>">
        </form>
    </div>
</c:if>
<%@ include file="../inc/footer.jsp" %>
</body>
</html>
