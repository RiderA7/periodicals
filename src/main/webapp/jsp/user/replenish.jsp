<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="messages"/>
<html>
<fmt:message key="user.replenish.title" var="title"/>
<%--<c:set var="title" value="Replenish balance" scope="page"/>--%>
<%@ include file="../inc/head.jsp" %>
<body>
<%@ include file="../inc/header.jsp" %>
<c:if test="${user ne null}">
    <%--    <div>${user}</div>--%>
    <div>
        <form class="form-floating"
              action="${pageContext.request.contextPath}/account/replenish"
              method="post">
            <div class="col-md-12">
                <div class="form-floating">
                    <input name="replenish" id="replenish" class="form-control" placeholder="100">
                    <label for="replenish"><fmt:message key="user.replenish.amount"/>:</label>
                </div>
            </div>
            <input type="submit" class="btn btn-primary" value="<fmt:message key="user.replenish.button"/>">
        </form>
    </div>
</c:if>
<%@ include file="../inc/footer.jsp" %>
</body>
</html>
