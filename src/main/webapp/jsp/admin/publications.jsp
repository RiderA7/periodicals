<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="messages"/>
<html>
<fmt:message key="title.admin.publications" var="title"/>
<%--<c:set var="title" value=<fmt:message key='Publications'/> + " Admin Page <i class='las la-battery-two-quarters'></i>" scope="page"/>--%>
<%@ include file="../inc/head.jsp" %>
<body>
<%@ include file="../inc/header.jsp" %>
<%--${sessionScope.publication}--%>
<form class="row g-2"
      action="${pageContext.request.contextPath}/publications"
      method="post">
    <input type="hidden" name="action" value="${sessionScope.action}">
    <input type="hidden" name="id" value="${sessionScope.publication.id}">
    <div class="col-md-12">
        <div class="form-floating">
            <input name="publicationName" id="publicationName" class="form-control"
                   value="${sessionScope.publication.name}">
            <label for="publicationName"><fmt:message key="admin.publication.new.title"/>:</label>
        </div>
    </div>
    <%--        <br>--%>
    <div class="col-md-12">
        <div class="form-floating">
            <input name="publicationPrice" id="publicationPrice" class="form-control"
                   value="${sessionScope.publication.price}">
            <label for="publicationPrice"><fmt:message key="admin.publication.price"/>:</label>
        </div>
    </div>
    <div class="col-md-12">
        <div class="form-floating mb-3">
            <select class="form-select" id="topicId" name="topicId" aria-label="<fmt:message key="admin.publication.topic"/>">
                <c:forEach var="topic" items="${sessionScope.topics}">
                    <option
                            <c:if test="${topic.id == sessionScope.publication.topic.id}">selected</c:if>
                            value="${topic.id}">
                            ${topic.name}
                    </option>
                </c:forEach>
            </select>
            <label for="topicId"><fmt:message key="admin.publication.topic"/>:</label>
        </div>
    </div>
    <c:if test="${sessionScope.action == 'add'}">
        <fmt:message key="admin.publication.button.add" var="button"/>
    </c:if>
    <c:if test="${sessionScope.action == 'edit'}">
        <fmt:message key="admin.publication.button.edit" var="button"/>
    </c:if>
    <input type="submit" class="btn btn-primary" value="${button}">
</form>
<c:remove var="publication" scope="session" />
<c:remove var="action" scope="session" />
<%@ include file="../inc/footer.jsp" %>
</body>
</html>
