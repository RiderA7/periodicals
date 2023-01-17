<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="messages"/>
<html>
<fmt:message key="title.admin.topic" var="title"/>
<%--<c:set var="title" value="Topics Admin Page <i class='las la-battery-three-quarters'></i>" scope="page"/>--%>
<%@ include file="../inc/head.jsp" %>
<body>
<%@ include file="../inc/header.jsp" %>
<%--${sessionScope.topic}--%>
<form class="row g-2"
      action="${pageContext.request.contextPath}/topics"
      method="post">
    <input type="hidden" name="action" value="${sessionScope.action}">
    <input type="hidden" name="id" value="${sessionScope.topic.id}">
    <div class="col-md-12">
        <div class="form-floating">
            <input name="topicName" id="topicName" class="form-control"
                   value="${sessionScope.topic.name}">
            <label for="topicName"><fmt:message key="admin.topic.new.title"/>:</label>
        </div>
    </div>
    <c:if test="${sessionScope.action == 'add'}">
        <fmt:message key="admin.topic.button.add" var="button"/>
    </c:if>
    <c:if test="${sessionScope.action == 'edit'}">
        <fmt:message key="admin.topic.button.edit" var="button"/>
    </c:if>
    <input type="submit" class="btn btn-primary" value="${button}">
</form>
<c:remove var="topic" scope="session" />
<c:remove var="action" scope="session" />
<%--<c:if test="${sessionScope.topic == null}">--%>
<%--    <form class="loginform"--%>
<%--          action="${pageContext.request.contextPath}/topics"--%>
<%--          method="post">--%>
<%--        <input type="hidden" name="action" value="add">--%>
<%--        <label for="topicName">New Topic:</label>--%>
<%--        <input name="topicName" id="topicName" class="loginlogin" value="Enter Topic name">--%>
<%--        <br>--%>
<%--        <input type="submit" class="loginbutton" value="Add Topic">--%>
<%--    </form>--%>
<%--</c:if>--%>
<%--<c:if test="${sessionScope.topic ne null}">--%>
<%--    <form class="loginform"--%>
<%--          action="${pageContext.request.contextPath}/topics"--%>
<%--          method="post">--%>
<%--        <input type="hidden" name="id" value="${sessionScope.topic.id}">--%>
<%--        <input type="hidden" name="action" value="edit">--%>
<%--        <label for="topicName">New Topic name:</label>--%>
<%--        <input name="topicName" id="topicName" class="loginlogin" value="${sessionScope.topic.name}">--%>
<%--        <br>--%>
<%--        <input type="submit" class="loginbutton" value="Update Topic">--%>
<%--    </form>--%>
<%--    <c:remove var="topic" scope="session" />--%>
<%--</c:if>--%>
<%@ include file="../inc/footer.jsp" %>
</body>
</html>
