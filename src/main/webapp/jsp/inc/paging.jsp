<c:if test="${param.currentPage ne null}">
    <div class="pages">
        <form action="" method="get">
            <input type="hidden" name="action" value="page">
            <button class="btn" name="page" value="1">
                <i class="las la-angle-double-left"></i>
            </button>
            <button class="btn" name="page" value="${param.currentPage}">
                <i class="las la-angle-left"></i>
            </button>
            Page ${param.currentPage+1} of ${param.maxPageNum+1}
            <button class="btn" name="page" value="${param.currentPage+2}">
                <i class="las la-angle-right"></i>
            </button>
            <button class="btn" name="page" value="${param.maxPageNum+1}">
                <i class="las la-angle-double-right"></i>
            </button>
        </form>
    </div>
</c:if>
