<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="messages"/>
<html>
<fmt:message key="error.title" var="title"/>
<%@ include file="jsp/inc/head.jsp" %>
<body>
<%@ include file="jsp/inc/header.jsp" %>
<%--${sessionScope.user}--%>
<br/>

<h2>errorCode: ${sessionScope.statusCode}</h2>
<br/>
<h3>servletName: ${sessionScope.servletName}</h3>
<br/>
<h3>requestUri: ${sessionScope.requestUri}</h3>
<br/>
<c:remove var="statusCode" scope="session"/>
<c:remove var="servletName" scope="session"/>
<c:remove var="requestUri" scope="session"/>
<c:remove var="throwable" scope="session"/>

<%--<a href="hello-servlet">Hello Servlet</a>--%>
<%--<a href="probe">probe</a>--%>
<%--<a href="ind.jsp">ind</a></body>--%>
<%--<a href="account/login">login</a>--%>
<%@ include file="jsp/inc/footer.jsp" %>
</body>
</html>