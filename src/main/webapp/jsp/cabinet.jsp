<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="property/text"/>
<fmt:message key="login.username.placeHolder" var="usernamePlaceHolder"/>
<fmt:message key="login.password.placeHolder" var="passwordPlaceHolder"/>
<!DOCTYPE html>
<html lang=${sessionScope.lang}>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css"/>
    <title>Cabinet</title>
</head>
<body>
<jsp:include page="/jsp/header.jsp"/>
<div>
    <c:forEach items="${sessionScope.myBets}" var="bet">
        <a><c:out value="${bet.id}"/></a>
        <a><c:out value="${bet.userId}"/></a>
        <a><c:out value="${bet.matchId}"/></a>
        <a><c:out value="${bet.betDate}"/></a>
        <a><c:out value="${bet.betTime}"/></a>
        <a><c:out value="${bet.betAmount}"/></a>
        <a><c:out value="${bet.betOnResult}"/></a>
        <a><c:out value="${bet.betStatus}"/></a>
        <a><c:out value="${bet.betCoeff}"/></a>
    </c:forEach>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</body>
</html>