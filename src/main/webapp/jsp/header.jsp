<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="property/text"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css"/>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="collapse navbar-collapse" id="admin">
        <ul class="navbar-nav mr-auto">

            <c:if test="${sessionScope.user.role=='ADMIN'}">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=admin_panel">
                        Admin panel
                    </a>
                </li>
                <li class="nav-item">
                    <a href="${pageContext.request.contextPath}/controller?command=to_create_match_command" class="nav-link">
                        <fmt:message key="create.match"/>
                    </a>
                </li>
            </c:if>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=home">
                    <fmt:message key="header.homePage"/>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=match_list">
                    <fmt:message key="match.list"/>
                </a>
            </li>
        </ul>
    </div>
    <div class="collapse navbar-collapse" id="navbar">
        <ul class="navbar-nav mr-auto">

        </ul>
    </div>
    <div class="collapse navbar-collapse flex-grow-1 text-right" id="login">
        <ul class="navbar-nav ms-auto flex-nowrap">
            <li class="nav-item dropdown" id="language">
                <button class="btn btn-light btn-sm dropdown-toggle " type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
                    <fmt:message key="header.language"/>
                </button>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="${pageContext.request.requestURL}?sessionLocale=en">
                        <fmt:message key="header.language.en"/>
                    </a>
                    <a class="dropdown-item" href="${pageContext.request.requestURL}?sessionLocale=ru">
                        <fmt:message key="header.language.ru"/>
                    </a>
                </div>
            </li>
            <c:choose>
                <c:when test="${sessionScope.authorised}">
                    <li class="nav-item ">
                        <a href="#" class="nav-link m-2 menu-item ">${sessionScope.user.username}</a>
                    </li>
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/controller?command=logout" class="nav-link m-2 menu-item">
                            <fmt:message key="header.logoutPage"/>
                        </a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/controller?command=to_login_page_command" class="nav-link m-2 menu-item">
                            <fmt:message key="header.loginPage"/>
                        </a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</nav>
</html>