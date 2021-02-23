<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="property/text"/>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <title>Title</title>
</head>
<body>
<jsp:include page="/jsp/header.jsp"/>
<h1>List of matches</h1>
<c:forEach items="${sessionScope.matches}" var="match">
    <a><c:out value="${match.homeTeam.name} - ${match.awayTeam.name}"/></a>
    <a><c:out value="${match.homeTeamGoals} : ${match.awayTeamGoals}"/></a>
    <form method="post" action="${pageContext.request.contextPath}/controller?command=to_place_bet_command&matchId=${match.id}">
        <button type="submit" class="btn btn-primary">
            <fmt:message key="unblock.user"/>
        </button>
    </form>
<br>
</c:forEach>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</body>
</html>
