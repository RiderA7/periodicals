<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="messages"/>
<html>
<c:set var="title" value="Users <i class='las la-battery-three-quarters'></i>" scope="page"/>
<%@ include file="../inc/head.jsp" %>
<body>
<%@ include file="../inc/header.jsp" %>
<table class="table">
    <tr>
        <th>User</th>
        <th>Login</th>
        <th>Role</th>
        <th>Balance</th>
        <th>Blocked</th>
        <th>Misc</th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.login}</td>
            <td>${user.name}</td>
            <td><c:if test="${user.roleId == 2}">Admin</c:if>
                <c:if test="${user.roleId == 1}">User</c:if></td>
            <td>${user.money}</td>
            <td><c:if test="${user.blocked == true}">blocked</c:if></td>
            <td>
                <form action="users" method="post">
                    <input type="hidden" name="userId" value="${user.id}">
                    <input type="hidden" name="action" value="user">
                    <c:if test="${user.blocked == false}">
                        <input class="btn" type="submit" name="ban" value="BAN">
                    </c:if>
                    <c:if test="${user.blocked == true}">
                        <input class="btn" type="submit" name="ban" value="UNBAN">
                    </c:if>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<%@ include file="../inc/paging.jsp" %>
<%@ include file="../inc/footer.jsp" %>
</body>
</html>
