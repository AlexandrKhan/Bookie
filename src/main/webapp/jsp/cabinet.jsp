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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Cabinet</title>
</head>
<body>
<jsp:include page="/jsp/header.jsp"/>
<div id="cashIn">
    <button type='button' class='button2' data-toggle='modal'
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
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
</body>
</html>