<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="text"/>
<fmt:message key="register.email.placeHolder" var="emailPlaceHolder"/>
<fmt:message key="register.password.placeHolder" var="passwordPlaceHolder"/>
<fmt:message key="register.username.placeHolder" var="usernamePlaceHolder"/>
<fmt:message key="register.firstname" var="firstnamePlaceHolder"/>
<fmt:message key="register.lastname" var="lastnamePlaceholder"/>
<fmt:message key="register.dateOfBirth" var="dateOfBirthPlaceholder"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/register.css"/>
    <title>Register</title>
</head>
<body>
<jsp:include page="/jsp/header.jsp"/>
<div>
    <form id="registration_form" action="${pageContext.request.contextPath}/controller?command=registration" method="post">
        <div class="form-group">
            <label for="username">
                <fmt:message key="register.username.label"/>
            </label>
            <input type="text" name="username" class="form-control" id="username" aria-describedby="usernameHelp" placeholder="${usernamePlaceHolder}"  required pattern="[a-zA-Z0-9]+([_-]?[a-zA-Z0-9]+){8,40}">
            <small id="usernameHelp" class="form-text text-muted">
                <fmt:message key="register.username.help"/>
            </small>
        </div>
        <div class="form-group">
            <label for="first_name">
                <fmt:message key="register.firstname"/>
            </label>
            <input type="text" name="first_name" class="form-control" id="first_name" aria-describedby="firstnameHelp" placeholder="${firstnamePlaceHolder}" >
            <small id="firstnameHelp" class="form-text text-muted">
                <fmt:message key="register.firstname.help"/>
            </small>
        </div>
        <div class="form-group">
            <label for="last_name">
                <fmt:message key="register.lastname"/>
            </label>
            <input type="text" name="last_name" class="form-control" id="last_name" aria-describedby="lastnameHelp" placeholder="${lastnamePlaceholder}"  >
            <small id="lastnameHelp" class="form-text text-muted">
                <fmt:message key="register.lastname.help"/>
            </small>
        </div>
        <div class="form-group">
            <label for="username">
                <fmt:message key="register.email"/>
            </label>
            <input type="text" name="email" class="form-control" id="email" aria-describedby="emailHelp" placeholder="${emailPlaceHolder}"  >
            <small id="emailHelp" class="form-text text-muted">
                <fmt:message key="register.email.help"/>
            </small>
        </div>
        <div class="form-group">
            <label for="password">
                <fmt:message key="register.password.label"/>
            </label>
            <input type="text" name="password" class="form-control" id="password" aria-describedby="passwordHelp" placeholder="${passwordPlaceHolder}" required  pattern="[a-zA-Z0-9@#$%!]{8,40}">
            <small id="passwordHelp" class="form-text text-muted">
                <fmt:message key="register.password.help"/>
            </small>
        </div>

        <div class="form-group">
            <label for="username">
                <fmt:message key="register.dateOfBirth"/>
            </label>
            <input type="date" name="date_of_birth" class="form-control" id="date_of_birth" aria-describedby="dateOfBirthHelp" placeholder="${dateOfBirthPlaceholder}" >
            <small id="dateOfBirthHelp" class="form-text text-muted">
                <fmt:message key="register.dateOfBirth.help"/>
            </small>
        </div>

        <button type="submit" class="btn btn-primary">
            <fmt:message key="register.register"/>
        </button>
    </form>
    <c:if test="${requestScope.errorMessage}">
        <div id="error" class="p-3 mb-2 bg-danger text-white">
            <fmt:message key="register.errorMessage"/>
        </div>
    </c:if>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</body>
</html>
