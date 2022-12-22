<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${sessionScope.user.roleId == 1}">
    <%@ include file="usermenu.jsp" %>
</c:if>
<c:if test="${sessionScope.user.roleId == 2}">
    <%@ include file="adminmenu.jsp" %>
</c:if>