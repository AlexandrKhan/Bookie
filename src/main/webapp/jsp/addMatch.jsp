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
<div>
    <form id="add_match" action="${pageContext.request.contextPath}/controller?command=add_match" method="post">
        <div class="form-group">
        <label>
            <select name="homeTeam">
                <c:forEach items="${requestScope.teams}" var="team">
                    <option>${team.name}</option>
                </c:forEach>
            </select>
        </label>
        </div>
        <div class="form-group">
        <label>
            <select name="awayTeam">
                <c:forEach items="${requestScope.teams}" var="team">
                    <option>${team.name}</option>
                </c:forEach>
            </select>
        </label>
        </div>
        <div class="form-group">
            <label for="startDate"></label><input type="date" name="startDate" class="form-control" id="startDate" >
        </div>
        <div class="form-group">
        <label for="startTime"></label><input type="time" name="startTime" id="startTime" required>
        </div>
        <div class="form-group">
            <label for="homeCoeff"></label><input type="number" name="homeCoeff" id="homeCoeff" required>
        </div>
        <div class="form-group">
            <label for="drawCoeff"></label><input type="number" name="drawCoeff" id="drawCoeff" required>
        </div>
        <div class="form-group">
            <label for="awayCoeff"></label><input type="number" name="awayCoeff" id="awayCoeff" required>
        </div>

        <button type="submit" class="btn btn-primary">
            <fmt:message key="create.match"/>
        </button>
    </form>

    <%--Error--%>
    <c:if test="${requestScope.errorMessage}">
        <div id="error" class="p-3 mb-2 bg-danger text-white">
            <fmt:message key="login.errorMessage"/>
        </div>
    </c:if>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
</body>
</html>

<%--<button class="btn btn-light btn-sm dropdown-toggle " type="button"
            data-bs-toggle="dropdown" aria-expanded="false">
        <fmt:message key="choose.team"/>
    </button>
    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
        <c:forEach items="${requestScope.teams}" var="team">
            <a class="dropdown-item">${team}</a>
        </c:forEach>
    </div>

    <button class="btn btn-light btn-sm dropdown-toggle " type="button"
            data-bs-toggle="dropdown" aria-expanded="false">
        <fmt:message key="choose.team"/>
    </button>
    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
        <c:forEach items="${requestScope.teams}" var="team">
            <a class="dropdown-item">${team}</a>
        </c:forEach>
    </div>--%>

