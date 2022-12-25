<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<%@ include file="jsp/inc/menu.jsp" %>
<h1><%= "Hello World!" %>
</h1>
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