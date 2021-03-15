<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="custom" uri="/WEB-INF/tld/custom.tld"%>
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

<c:if test="${not empty param.filter}">
    <c:choose>
        <c:when test="${param.filter eq 'home'}">
            <c:set var="matches" value="${custom:sortByHomeTeam(sessionScope.matches)}"/>
        </c:when>
        <c:when test="${param.filter eq 'away'}">
            <c:set var="matches" value="${custom:sortByAwayTeam(sessionScope.matches)}"/>
        </c:when>
        <c:when test="${param.filter eq 'date'}">
            <c:set var="matches" value="${custom:sortByDateThenTime(sessionScope.matches)}"/>
        </c:when>
    </c:choose>
</c:if>
<div class="container">
    <form class="d-flex justify-content-end g-1 me-5" action="${pageContext.request.contextPath}/controller?command=${param.command}">
        <input type="hidden" name="command" value="${param.command}"/>
        <c:if test="${not empty param.text}">
            <input type="hidden" name="text" value="${param.text}"/>
        </c:if>
        <label>
            <select name="filter">
                <option value="home">
                    <fmt:message key="match.by.home"/>
                </option>
                <option value="away">
                    <fmt:message key="match.by.away"/>
                </option>
                <option value="date">
                    <fmt:message key="match.by.date"/>
                </option>
            </select>
        </label>
        <button type="submit" class="btn btn-primary">
            <fmt:message key="match.filter"/>
        </button>
    </form>
</div>

<c:forEach items="${sessionScope.matches}" var="match">
    <img src="${pageContext.request.contextPath}/images/EPL_teams/${match.homeTeam}.png" width="100" height="100">
    <a><c:out value="${match.homeTeam.name} - ${match.awayTeam.name}"/></a>
    <img src="${pageContext.request.contextPath}/images/EPL_teams/${match.awayTeam}.png" width="100" height="100">
    <a><c:out value="${match.homeTeamGoals} : ${match.awayTeamGoals}"/></a>

    <c:if test ="${match.matchProgress == 'NOT_STARTED'}">
        <form method="post" action="${pageContext.request.contextPath}/controller?command=to_place_bet_command&matchId=${match.id}">
            <button type="submit" class="btn btn-primary">
                <fmt:message key="place.bet"/>
            </button>
        </form>

        <form method="post" action="${pageContext.request.contextPath}/controller?command=to_update_match_command&matchId=${match.id}">
            <button type="submit" class="btn btn-primary" id="changeDate">
                <fmt:message key="update.match"/>
            </button>
        </form>
    </c:if>

<br>
</c:forEach>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/script.js">
</script>
</body>
</html>
