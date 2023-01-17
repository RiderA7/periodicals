<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="messages"/>
<html>
<fmt:message key="title.general.publications" var="title"/>
<%--<c:set var="title" value="Publications <i class='las la-battery-three-quarters'></i>" scope="page"/>--%>
<%@ include file="../inc/head.jsp" %>
<body>
<%@ include file="../inc/header.jsp" %>
<c:if test="${sessionScope.user.roleId == 2}">
    <form action="publications" method="post">
        <input class="btn btn-secondary" type="submit" name="add" value="<fmt:message key="general.publication.add"/>">
    </form>
</c:if>
<c:if test="${sessionScope.activeTopic ne null}">
    <form action="publications" method="post">
        <div class="alert alert-primary alert-dismissible fade show" role="alert">
            <i class="bi bi-star me-1"></i>
            <fmt:message key="general.publication.current.topic"/>: ${sessionScope.activeTopic.name}
            <button class="btn-close" name="removeTopic" value="removeTopic" title="<fmt:message key="general.publication.remove.topic"/>">
                <i class="las la-times"></i>
            </button>
        </div>
    </form>
</c:if>
<c:if test="${publications.size() > 0}">
    <table class="table">
        <tr>
            <th><fmt:message key="general.publication.table.title"/></th>
            <th><fmt:message key="general.publication.table.topic"/></th>
            <th><fmt:message key="general.publication.table.price"/></th>
            <th><fmt:message key="general.publication.table.misc"/></th>
        </tr>
        <c:forEach var="pub" items="${publications}">
            <tr>
                <td>${pub.name}</td>
                <td>${pub.topic.name}</td>
                <td>${pub.price}</td>
                <td>
                    <c:if test="${sessionScope.user.roleId == 2}">
                        <form action="publications" method="post">
                            <input type="hidden" name="pubId" value="${pub.id}">
                            <input class="btn btn-success" type="submit"
                                   name="edit" value="<fmt:message key="general.publication.table.edit"/>">
                        </form>
                    </c:if>
                    <c:if test="${sessionScope.user.roleId == 1}">
                        <form action="account/subscribe" method="post">
                            <input type="hidden" name="pubId" value="${pub.id}">
                            <input class="btn btn-primary" type="submit"
                                   name="subscribe" value="<fmt:message key="general.publication.table.subscribe"/>">
                        </form>
                    </c:if>
                    <form action="publications" method="post">
                        <input type="hidden" name="pubId" value="${pub.id}">
                        <input class="btn btn-info" type="submit"
                               name="view" value="<fmt:message key="general.publication.table.view"/>">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <%@ include file="../inc/paging.jsp" %>
</c:if>
<c:if test="${publications.size() == 0}">
    <div class="bar info">
        <a href="?page=1"><fmt:message key="general.publication.table.nodata"/></a>
    </div>
</c:if>
<%@ include file="../inc/footer.jsp" %>
</body>
</html>
