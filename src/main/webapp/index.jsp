<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="messages"/>
<html>
<fmt:message key="title.welcome" var="title"/>
<%@ include file="jsp/inc/head.jsp" %>
<body>
<%@ include file="jsp/inc/header.jsp" %>
${sessionScope.user}
<br/>
${sessionScope.user.login}
Hellllllllloooooooooo!!!!
<h2><fmt:message key="label.welcome" /></h2>
<br/>
<%--<a href="hello-servlet">Hello Servlet</a>--%>
<%--<a href="probe">probe</a>--%>
<%--<a href="ind.jsp">ind</a></body>--%>
<%--<a href="account/login">login</a>--%>
<%@ include file="jsp/inc/footer.jsp" %>
</body>
</html>