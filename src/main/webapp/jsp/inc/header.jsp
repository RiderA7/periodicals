<header id="header" class="header fixed-top d-flex align-items-center">

    <div class="d-flex align-items-center justify-content-between">
        <a href="${pageContext.request.contextPath}/" class="logo d-flex align-items-center">
            <img src="${pageContext.request.contextPath}/assets/img/logo.png" alt="">
            <span class="d-none d-lg-block">Periodicals</span>
        </a>
        <i class="bi bi-list toggle-sidebar-btn"></i>
    </div><!-- End Logo -->

    <div class="search-bar">
        <form class="search-form d-flex align-items-center" method="POST" action="#">
            <input type="text" name="query" placeholder="Search" title="Enter search keyword">
            <button type="submit" title="Search"><i class="bi bi-search"></i></button>
        </form>
    </div><!-- End Search Bar -->

    <nav class="header-nav ms-auto">
        <ul class="d-flex align-items-center">

            <li class="nav-item d-block d-lg-none">
                <a class="nav-link nav-icon search-bar-toggle " href="#">
                    <i class="bi bi-search"></i>
                </a>
            </li><!-- End Search Icon-->
        </ul>

        <div class="nav-item dropdown">
            <form action="" method="post">
                <select name="locale" onchange='submit();'>
                    <option value="en" ${cookie['lang'].value eq 'en' ? 'selected' : ''}>
                        <fmt:message key="en"/>
                    </option>
                    <option value="ua" ${cookie['lang'].value eq 'ua' ? 'selected' : ''}>
                        <fmt:message key="ua"/>
                    </option>
                </select>
            </form>
        </div>

    </nav><!-- End Icons Navigation -->

</header>
<!-- End Header -->
<!-- ======= Sidebar ======= -->
<aside id="sidebar" class="sidebar">

    <ul class="sidebar-nav" id="sidebar-nav">

        <%@ include file="../inc/menu.jsp" %>

    </ul>

</aside>
<!-- End Sidebar-->
<main id="main" class="main">
    <h1>${title}</h1>
    <c:if test="${sessionScope.err ne null}">
    <div class="alert alert-warning alert-dismissible fade show" role="alert">
        <i class="bi bi-exclamation-triangle me-1"></i>
        <fmt:message key="${sessionScope.err}"/>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
        <c:remove var="err" scope="session"/>
    </c:if>
    <c:if test="${sessionScope.suc ne null}">
    <div class="alert alert-success alert-dismissible fade show" role="alert">
        <i class="bi bi-check-circle me-1"></i>
        <fmt:message key="${sessionScope.suc}"/>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
        <c:remove var="suc" scope="session"/>
    </c:if>
