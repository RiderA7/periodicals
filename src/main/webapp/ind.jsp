<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"
         language="java" pageEncoding="UTF-8" %>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="jsp/inc/menu.jsp" %>
<c:if test="${param.err ne null}">ura</c:if>
<c:set var="salary" scope="page" value="${23400*2}"/>
${param.err+salary}

</body>
</html>
