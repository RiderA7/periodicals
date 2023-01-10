<h1>${title}</h1>
<c:if test="${sessionScope.err ne null}">
    <div class="bar error">${sessionScope.err}</div>
        <c:remove var="err" scope="session" />
</c:if>
<c:if test="${sessionScope.suc ne null}">
    <div class="bar success">${sessionScope.suc}</div>
        <c:remove var="suc" scope="session" />
</c:if>
