<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<c:set var="title" value="Welcome to Periodicals" scope="page"/>
<%@ include file="jsp/inc/head.jsp" %>
<body>
<%@ include file="jsp/inc/menu.jsp" %>
<%@ include file="jsp/inc/header.jsp" %>
${sessionScope.user}
<br/>
${sessionScope.user.login}
Hellllllllloooooooooo!!!!
<br/>
<a href="hello-servlet">Hello Servlet</a>
<a href="probe">probe</a>
<a href="ind.jsp">ind</a></body>
<a href="account/login">login</a>
</body>
</html>