<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="custom" uri="/WEB-INF/tld/custom.tld" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="property/text"/>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/match.css"/>
    <title>Title</title>
</head>
<body>
<jsp:include page="/jsp/header.jsp"/>
<div class="search">
    <div class="col-md-4">
        <form method="post" action="${pageContext.request.contextPath}/controller?command=search_matches">
            <label for="team"></label>
            <input type="text" name="team" class="form-control" id="team" placeholder="Search">
        </form>
    </div>
</div>

<div class="row" id="content">
    <c:forEach items="${sessionScope.matches}" var="match">
        <div class="block">
            <div style="height:80px" class="top">
                <div class="middle">
                    <img src="${pageContext.request.contextPath}/images/EPL_teams/${match.homeTeam}.png">
                    <img src="${pageContext.request.contextPath}/images/EPL_teams/${match.awayTeam}.png">
                </div>
                <div class="l-middle">
                    <h3><c:out value="${match.startDate}"/></h3>
                    <h5><c:out value="${match.startTime}"/></h5>
                </div>
            </div>

            <div class="bottom">
                <c:if test="${match.matchProgress == 'OVER'}">
                    <h1 class="score"><c:out value="${match.homeTeamGoals} - ${match.awayTeamGoals}"/></h1>
                </c:if>
                <c:if test="${match.matchProgress == 'NOT_STARTED'}">
                    <c:if test="${sessionScope.user.role=='ADMIN'}">
                        <div>
                            <button type='button' class='button2' data-toggle='modal'
                                    data-target='#UPDATEMATCHMODAL'
                            data-match="${match.id}">
                                <fmt:message key="update.match"/>
                            </button>
                            <div class='modal fade' id='UPDATEMATCHMODAL' tabindex='-1' role='dialog'
                                 aria-labelledby='exampleModalLabel' aria-hidden='false'>
                                <div class='modal-dialog' role='document'>
                                    <div class='modal-content'>
                                        <form method="post"
                                              action="${pageContext.request.contextPath}/controller?command=update_match">
                                            <div class='modal-header'>
                                                <h5 class='modal-title'><fmt:message key='update.match'/></h5>
                                            </div>
                                            <div class="modal-body">
                                                <input type="hidden" name="matchId" id="match" value="">
                                                <label for="startDate"></label><input type="date" name="startDate"
                                                                                      class="form-control"
                                                                                      id="startDate"
                                                                                      style="margin: 10px auto 10px auto;">
                                                <label for="startTime"></label><input type="time" name="startTime"
                                                                                      id="startTime">
                                            </div>

                                            <button type='button' class='btn btn-alert' data-dismiss='modal'>
                                                <fmt:message
                                                        key='button.close'/></button>
                                            <input type='submit' value='<fmt:message key='button.save'/>'
                                                   class='btn btn-primary'/>

                                            <c:if test="${not empty requestScope.errorSet}">
                                                <label style="color: red; font-size: medium"><fmt:message
                                                        key="match.dateError"/></label>
                                            </c:if>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:if>

                        <button type='button'
                                class='button2'
                                id="plcBt"
                                data-toggle='modal'
                                data-target='#PLACEBETMODAL'
                                data-matchid="${match.id}"
                                data-home="${match.homeCoeff}"
                                data-draw="${match.drawCoeff}"
                                data-away="${match.awayCoeff}">
                            <fmt:message key="place.bet"/>
                        </button>
                        <div class='modal fade' id='PLACEBETMODAL' tabindex='-1' role='dialog'
                             aria-labelledby='exampleModalLabel' aria-hidden='false'>
                            <div class='modal-dialog' role='document'>
                                <div class='modal-content'>
                                    <form method="post" action="${pageContext.request.contextPath}/controller?command=place_bet">
                                        <div class='modal-header'>
                                            <h5 class='modal-title'><fmt:message key='place.bet'/></h5>
                                        </div>
                                        <div class="modal-body">
                                            <input type="hidden" name="matchId" id="matchId" value="">
                                            <label for="betAmount"></label><input type="number" name="betAmount"
                                                                                  class="form-control"
                                                                                  id="betAmount" step="0.01" min="5"
                                                                                  value="5">
                                            <div class="radio-group">
                                                <input type="radio" id="home" name="betOnResult" value="HOME"><label
                                                    for="home"><fmt:message key="result.home"/><p id="homeCoeff"></p></label>
                                                <input type="radio" id="draw" name="betOnResult" value="DRAW"><label
                                                    for="draw"><fmt:message key="result.draw"/></label>
                                                <input type="radio" id="away" name="betOnResult" value="AWAY"><p id="drawCoeff"></p><label
                                                    for="away"><fmt:message key="result.away"/><p id="awayCoeff"></p></label>
                                            </div>
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
                </c:if>
            </div>
        </div>
        <br>
    </c:forEach>
</div>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript">
    $('#UPDATEMATCHMODAL').on('show.bs.modal', function (event) {
        let matchUpdateId = $(event.relatedTarget).data('match');
        $(this).find('.modal-body #match').val(matchUpdateId);
    })
</script>
<script type="text/javascript">
    $('#PLACEBETMODAL').on('show.bs.modal', function (event) {
        let matchId = $(event.relatedTarget).data('matchid');
        let home = $(event.relatedTarget).data('home');
        let draw = $(event.relatedTarget).data('draw');
        let away = $(event.relatedTarget).data('away');
        $(this).find('.modal-body #matchId').val(matchId);
        $(this).find('.modal-body #homeCoeff').val(home);
        $(this).find('.modal-body #drawCoeff').val(draw);
        $(this).find('.modal-body #awayCoeff').val(away)
    })
</script>
</body>
</html>

