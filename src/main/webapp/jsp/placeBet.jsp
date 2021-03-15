<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="property/text"/>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

    <title>Title</title>
</head>
<body>
<jsp:include page="/jsp/header.jsp"/>
<form id="place_bet"
      action="${pageContext.request.contextPath}/controller?command=place_bet&matchId=${requestScope.matchId}"
      method="post">
    <div class="form-group">
        <label for="betAmount"></label><input type="number" name="betAmount" id="betAmount" step="0.01" min="0.01"
                                              required>
    </div>
    <div class="form-group">
        <label>
            <select name="betOnResult">
                <c:forEach items="${requestScope.result}" var="result">
                    <option>${result.name}</option>
                </c:forEach>
            </select>
        </label>
    </div>
    <div>
        <c:if test="${not empty sessionScope.errorSet}">
            <label style="color: red; font-size: medium">
                <fmt:message key="no.money"/>
            </label>
        </c:if>
    </div>
    <button type="submit" class="btn btn-primary">
        <fmt:message key="place.bet"/>
    </button>
</form>
</body>
</html>
