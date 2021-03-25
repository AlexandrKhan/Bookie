<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="property/text"/>
<html lang="${sessionScope.lang}">
<head>
    <meta name="viewport" content="width=device-width">
</head>
<div>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <c:if test="${sessionScope.user.role=='ADMIN'}">
                <li class="nav-item" aria-expanded="true">
                    <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=admin_panel">
                        <fmt:message key="admin.panel"/>
                    </a>
                </li>
                <li class="nav-item" aria-expanded="true">
                    <a href="${pageContext.request.contextPath}/controller?command=to_create_match_command"
                       class="nav-link">
                        <fmt:message key="create.match"/>
                    </a>
                </li>
            </c:if>
            <li class="nav-item" aria-expanded="true">
                <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=home">
                    <fmt:message key="header.homePage"/>
                </a>
            </li>
            <li class="nav-item" aria-expanded="true">
                <a href="${pageContext.request.contextPath}/controller?command=personal_cabinet" class="nav-link">
                    <fmt:message key="personal.cabinet"/>
                </a>
            </li>
            <li class="nav-item" aria-expanded="true">
                <a href="${pageContext.request.contextPath}/controller?command=to_messages_command" class="nav-link">
                    <fmt:message key="personal.messages"/>
                </a>
            </li>


            <li class="nav-item" aria-expanded="true">
                <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=match_list">
                    <fmt:message key="match.list"/>
                </a>
            </li>
            <li class="nav-item dropdown" id="language" aria-expanded="true">
                <a class="nav-link dropdown-toggle" role="button" id="dropdownMenuButton"
                   data-bs-toggle="dropdown" aria-haspopup="true">
                    <fmt:message key="header.language"/>
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="${pageContext.request.requestURL}?sessionLocale=en">
                        <fmt:message key="header.language.en"/>
                    </a>
                    <a class="dropdown-item" href="${pageContext.request.requestURL}?sessionLocale=ru">
                        <fmt:message key="header.language.ru"/>
                    </a>
                </div>
            </li>
        </ul>
        <div class="collapse navbar-collapse" id="navbar">
            <ul class="navbar-nav mr-auto">
            </ul>
        </div>
        <div class="collapse navbar-collapse flex-grow-1 text-left" id="login">
            <ul class="navbar-nav ms-auto flex-nowrap">
                <c:choose>
                    <c:when test="${sessionScope.authorised}">
                        <li class="nav-item" aria-expanded="true">
                            <a href="#" class="nav-link m-2 menu-item ">${sessionScope.user.firstName} ${sessionScope.user.lastName}</a>
                        </li>
                        <li class="nav-item" aria-expanded="true">
                            <a href="${pageContext.request.contextPath}/controller?command=logout"
                               class="nav-link m-2 menu-item">
                                <fmt:message key="header.logoutPage"/>
                            </a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item" aria-expanded="true">
                            <a href="${pageContext.request.contextPath}/controller?command=authorisation"
                               class="nav-link m-2 menu-item">
                                <fmt:message key="header.loginPage"/>
                            </a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>
</div>
<style>
    .navbar {
        -webkit-text-stroke-width: medium;
        white-space: nowrap;
    }
</style>
</html>