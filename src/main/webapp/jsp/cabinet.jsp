<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="property/text"/>
<!DOCTYPE html>
<html lang=${sessionScope.lang}>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel=stylesheet href="${pageContext.request.contextPath}/css/cabinet.css">
    <title>Cabinet</title>
</head>
<body>
<jsp:include page="/jsp/header.jsp"/>
    <div class="container">
        <ul class="responsive-table">
            <li class="table-header">
                <div class="col col-1">Bet id</div>
                <div class="col col-2">Match</div>
                <div class="col col-3">Score</div>
                <div class="col col-4">Match time</div>
                <div class="col col-5">On result</div>
                <div class="col col-6">Amount</div>
                <div class="col col-7">Coefficient</div>
            </li>
            <c:forEach items="${sessionScope.myBets}" var="bet">
                <c:if test="${bet.betStatus == 'NOT_STARTED'}">
                    <li class="table-row" style="background: linear-gradient(0deg, rgb(222 232 226), rgb(190 204 192));">
                </c:if>
                <c:if test="${bet.betStatus == 'WON'}">
                    <li class="table-row" style="background: linear-gradient(0deg, rgb(51 128 81), rgb(146 214 160))">
                </c:if>
                <c:if test="${bet.betStatus == 'LOST'}">
                    <li class="table-row" style="background: linear-gradient(0deg, rgb(212 83 83), rgb(208 153 153));">
                </c:if>
                <div class="col col-1" data-label="Bet id">#<c:out value="${bet.id}"/></div>
                <c:forEach items="${sessionScope.myMatches}" var ="match">
                <c:if test="${match.id == bet.matchId}">
                    <div class="col col-2" data-label="Match"><c:out value="${match.homeTeam.name} - ${match.awayTeam.name}"/></div>
                    <div class="col col-3" data-label="Score"><c:out value="${match.homeTeamGoals} - ${match.awayTeamGoals}"/></div>
                    <div class="col col-4" data-label="Match time"><c:out value="${match.startTime}, ${match.startDate}"/></div>
                </c:if>
                </c:forEach>
                <div class="col col-5" data-label="On result"><c:out value="${bet.betOnResult.name}"/></div>
                <div class="col col-6" data-label="Amount"><c:out value="${bet.betAmount}"/></div>
                <div class="col col-7" data-label="Coefficient"><c:out value="${bet.betCoeff}"/></div>
            </li>
            </c:forEach>
        </ul>
    </div>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
</body>
<style>
    body {
        background-color: black;
    }
    .container {
        max-width: 90%;
        margin-left: auto;
        margin-right: auto;
        padding-left: 10px;
        padding-right: 10px;
        margin-top: 20px;
    }

    .modal-content {
        height: 210px;
    }

    .responsive-table li {
        border-radius: 3px;
        padding: 25px 30px;
        display: flex;
        justify-content: space-between;
        margin-bottom: 1px;
    }
    .responsive-table .table-header {
        background-color: black;
        text-transform: uppercase;
        letter-spacing: 0.03em;
        color: white;
        font-size: 16px;
    }
    .responsive-table .table-row {
        background-color: #ffffff;
        box-shadow: 0 0 9px 0 rgba(0, 0, 0, 0.1);
    }
    .responsive-table .col-1 {
        flex-basis: 5%;
    }
    .responsive-table .col-2 {
        flex-basis: 35%;
        text-align: center;
    }
    .responsive-table .col-3 {
        flex-basis: 10%;
    }
    .responsive-table .col-4 {
        flex-basis: 20%;
    }
    .responsive-table .col-5 {
        flex-basis: 10%;
    }
    .responsive-table .col-6 {
        flex-basis: 10%;
    }
    .responsive-table .col-7 {
        flex-basis: 10%;
    }
    @media all and (max-width: 767px) {
        .responsive-table .table-header {
            display: none;
        }
        .responsive-table li {
            display: block;
        }
        .responsive-table .col {
            flex-basis: 100%;
        }
        .responsive-table .col {
            display: flex;
            padding: 10px 0;
        }
        .responsive-table .col:before {
            color: #6C7A89;
            padding-right: 10px;
            content: attr(data-label);
            flex-basis: 50%;
            text-align: right;
        }
    }
</style>
</html>