<c:if test="${param.currentPage ne null}">
    <div class="pages">
        <form action="" method="get">
            <input type="hidden" name="action" value="page">
            <button class="btn btn-outline-secondary" name="page" value="1">
                <i class="las la-angle-double-left"></i>
            </button>
            <button class="btn btn-outline-secondary" name="page" value="${param.currentPage}">
                <i class="las la-angle-left"></i>
            </button>
            <fmt:message key="paging.page"/> ${param.currentPage+1}
            <fmt:message key="paging.of"/> ${param.maxPageNum+1}
            <button class="btn btn-outline-secondary" name="page" value="${param.currentPage+2}">
                <i class="las la-angle-right"></i>
            </button>
            <button class="btn btn-outline-secondary" name="page" value="${param.maxPageNum+1}">
                <i class="las la-angle-double-right"></i>
            </button>
        </form>
    </div>
</c:if>
