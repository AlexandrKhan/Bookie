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
            <label for="homeCoeff"></label><input type="number" name="homeCoeff" id="homeCoeff" step="0.01" min="0.01" required>
        </div>
        <div class="form-group">
            <label for="drawCoeff"></label><input type="number" name="drawCoeff" id="drawCoeff" step="0.01" min="0.01" required>
        </div>
        <div class="form-group">
            <label for="awayCoeff"></label><input type="number" name="awayCoeff" id="awayCoeff" step="0.01" min="0.01" required>
        </div>

        <button type="submit" class="btn btn-primary">
            <fmt:message key="create.match"/>
        </button>
    </form>

    <c:if test="${not empty requestScope.errorSet}">
        <label style="color: red; font-size: medium"><fmt:message
                key="same.team"/></label>
    </c:if>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
</body>
</html>

