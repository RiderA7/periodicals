<h1>${title}</h1>
<c:if test="${err ne null}">
    <p class="login_error">${err}</p>
</c:if>
<c:if test="${suc ne null}">
    <p class="login_success">${suc}</p>
</c:if>
