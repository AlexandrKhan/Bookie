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
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <c:if test="${sessionScope.user.role=='ADMIN'}">
                <li class="nav-item" aria-expanded="true">
                    <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=admin_panel">
                        <fmt:message key="admin.panel"/>
                    </a>
                </li>
                <li class="nav-item" aria-expanded="true">
                    <a href="${pageContext.request.contextPath}/controller?command=create_match"
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
                <a href="${pageContext.request.contextPath}/controller?command=messages" class="nav-link">
                    <fmt:message key="personal.messages"/>
                </a>
            </li>


            <li class="nav-item" aria-expanded="true">
                <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=match_list">
                    <fmt:message key="match.list"/>
                </a>
            </li>
            <li>
            <div id="cashIn">
                <a type='button' class='nav-link' aria-expanded="true" data-toggle='modal'
                        data-target='#CASHINMODAL'>
                    <fmt:message key="cash.in"/>
                </a>

                <div class='modal fade' id='CASHINMODAL' tabindex='-1' role='dialog'
                     aria-labelledby='exampleModalLabel' aria-hidden='false'>
                    <div class='modal-dialog' role='document'>
                        <div class='modal-content'>
                            <form method="post"
                                  action="${pageContext.request.contextPath}/controller?command=cash_in">
                                <div class='modal-header'>
                                    <h5 class='modal-title'><fmt:message key='cash.in'/></h5>
                                </div>
                                <div class="modal-body">
                                    <label for="cashInSum"></label><input type="number" name="cashInSum"
                                                                          class="form-control"
                                                                          id="cashInSum" step="0.01" min="5" value="5">
                                </div>

                                <button type='button' class='btn btn-alert' data-dismiss='modal'>
                                    <fmt:message
                                            key='button.close'/></button>
                                <input type='submit' value='<fmt:message key='button.save'/>'
                                       class='btn btn-primary'/>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
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
    .modal-content {
        height: 210px;
    }
</style>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<%--<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>--%>
<%--<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>--%>
</html>