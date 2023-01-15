<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="messages"/>
<html>
<c:set var="title" value="Replenish balance" scope="page"/>
<%@ include file="../inc/head.jsp" %>
<body>
<%@ include file="../inc/header.jsp" %>
<c:if test="${user ne null}">
    <div>${user}</div>
    <div>
        <form class="loginform"
              action="${pageContext.request.contextPath}/account/replenish"
              method="post">
            <label for="replenish">Enter amount of money to deposit:</label>
            <input name="replenish" id="replenish" class="replenish" placeholder="100">
            <br>
            <input type="submit" class="loginbutton" value="Deposit">
        </form>
    </div>
</c:if>
<%@ include file="../inc/footer.jsp" %>
</body>
</html>
