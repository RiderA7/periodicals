<c:choose>
    <c:when test="${sessionScope.user.role.id == 1}">
        <%@ include file="usermenu.jsp" %>
    </c:when>
    <c:when test="${sessionScope.user.role.id == 2}">
        <%@ include file="adminmenu.jsp" %>
    </c:when>
    <c:otherwise>
        <%@ include file="othermenu.jsp" %>
    </c:otherwise>
</c:choose>