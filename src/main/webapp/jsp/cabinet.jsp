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
<div id="cashIn">
    <button type='button' class='btn-cashin' data-toggle='modal'
            data-target='#CASHINMODAL'>
        <fmt:message key="cash.in"/>
    </button>

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
                    <li class="table-row">
                </c:if>
                <c:if test="${bet.betStatus == 'WON'}">
                    <li class="table-row" style="background-color: #c5e0aa;">
                </c:if>
                <c:if test="${bet.betStatus == 'LOST'}">
                    <li class="table-row" style="background-color: #e8c3c3;">
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
    <%--<c:forEach items="${sessionScope.myBets}" var="bet">--%>
        <%--<a><c:out value="${bet.id}"/></a>--%>
        <%--<c:forEach items="${sessionScope.myMatches}" var ="match">--%>
            <%--<c:if test="${match.id == bet.matchId}">--%>
                <%--<a><c:out value="${match.homeTeam.name}"/></a>--%>
                <%--<a><c:out value="${match.awayTeam.name}"/></a>--%>
                <%--<a><c:out value="${match.homeTeamGoals} - ${match.awayTeamGoals}"/></a>--%>
                <%--<a><c:out value="${match.startDate}"/></a>--%>
                <%--<a><c:out value="${match.startTime}"/></a>--%>
            <%--</c:if>--%>
        <%--</c:forEach>--%>
        <%--<a><c:out value="${bet.betOnResult.name}"/></a>--%>
        <%--<a><c:out value="${bet.betAmount}"/></a>--%>
        <%--<a><c:out value="${bet.betCoeff}"/></a>--%>
    <%--<br/>--%>
    <%--</c:forEach>--%>
<%--</div>--%>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
</body>
<style>
    .container {
        max-width: 90%;
        margin-left: auto;
        margin-right: auto;
        padding-left: 10px;
        padding-right: 10px;
        margin-top: 20px;
    }

    .btn-cashin {
        font-family: inherit;
        font-size: 30px;
        line-height: inherit;
        border-radius: 50px;
        margin: 10px 50% 10px 5%;
        width: 90%;
        height: 80px;
        align-items: center;
        font-weight: 900;
        background: -webkit-linear-gradient(
                135deg
                , rgb(78 214 139) 27%, rgb(71 138 94) 86%);
    }

    .responsive-table li {
        border-radius: 3px;
        padding: 25px 30px;
        display: flex;
        justify-content: space-between;
        margin-bottom: 1px;
    }
    .responsive-table .table-header {
        background-color: #e9eaea;
        font-size: 14px;
        text-transform: uppercase;
        letter-spacing: 0.03em;
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