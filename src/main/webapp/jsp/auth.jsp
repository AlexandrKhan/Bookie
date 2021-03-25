<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="property/text"/>
<fmt:message key="login.username.placeHolder" var="usernamePlaceHolder"/>
<fmt:message key="login.password.placeHolder" var="passwordPlaceHolder"/>
<fmt:message key="register.email.placeHolder" var="emailPlaceHolder"/>
<fmt:message key="register.password.placeHolder" var="passwordPlaceHolder"/>
<fmt:message key="register.username.placeHolder" var="usernamePlaceHolder"/>
<fmt:message key="register.firstname" var="firstnamePlaceHolder"/>
<fmt:message key="register.lastname" var="lastnamePlaceholder"/>
<fmt:message key="register.dateOfBirth" var="dateOfBirthPlaceholder"/>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/auth.css"/>
    <title>Title</title>
</head>
<body>
<%--<jsp:include page="/jsp/header.jsp"/>--%>
<div class="form-body">
<div class="form-structor">
    <div class="signup">
        <h2 class="form-title" id="signup"><span>or</span>Sign up</h2>
        <form id="registration_form" action="${pageContext.request.contextPath}/controller?command=registration" method="post">
        <div class="form-holder">
            <input type="text" name="username" class="input" aria-describedby="usernameHelp" placeholder="${usernamePlaceHolder}"  required pattern="[a-zA-Z0-9]+([_-]?[a-zA-Z0-9]+){5,20}"/>
            <input type="text" name="firstName" class="input" aria-describedby="firstnameHelp" placeholder="${firstnamePlaceHolder}"  required pattern="[a-zA-Z0-9]+([_-]?[a-zA-Z0-9]+){5,20}"/>
            <input type="text" name="lastName" class="input" aria-describedby="lastnameHelp" placeholder="${lastnamePlaceholder}"  required pattern="[a-zA-Z0-9]+([_-]?[a-zA-Z0-9]+){5,20}"/>
            <input type="text" name="email" class="input" aria-describedby="emailHelp" placeholder="${emailPlaceHolder}" required pattern="^([A-Za-z0-9_-]+\.)*[A-Za-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$"/>
            <input type="password" name="password" class="input" aria-describedby="passwordHelp" placeholder="${passwordPlaceHolder}" required  pattern="[a-zA-Z0-9@#$%!]{8,20}"/>
            <input type="password" name="repeatPassword" class="input" aria-describedby="repeatPasswordHelp" placeholder="${passwordPlaceHolder}" required  pattern="[a-zA-Z0-9@#$%!]{8,20}"/>
            <input type="date" name="dateOfBirth" class="input" aria-describedby="dateOfBirthHelp" placeholder="${dateOfBirthPlaceholder}" required>
        </div>
        <button type="submit" class="submit-btn"><fmt:message key="sign.up"/></button>
        </form>
    </div>

    <div class="login slide-up">
        <div class="center">
            <h2 class="form-title" id="login"><span>or</span>Log in</h2>
            <form id="login_form" action="${pageContext.request.contextPath}/controller?command=login" method="post">
            <div class="form-holder">
                <input type="text" name="username" id="username" class="input" placeholder="${usernamePlaceHolder}" required pattern="[a-zA-Z0-9]{5,20}"/>
                <input type="password" name="password" id="password" class="input" placeholder="${passwordPlaceHolder}" required pattern="[a-zA-Z0-9@#$%!]{8,20}"/>
            </div>
            <button type="submit" class="submit-btn"><fmt:message key="login.login"/></button>
                <c:if test="${not empty requestScope.errorSet}">
                    <label style="color: red; font-size: medium"><fmt:message
                            key="login.errorMessage"/></label>
                </c:if>
            </form>
        </div>
    </div>
</div>
</div>
<script src="${pageContext.request.contextPath}/js/auth.js"></script>
</body>
</html>
