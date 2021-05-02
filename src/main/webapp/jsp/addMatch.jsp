<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="property/text"/>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <title>Title</title>
</head>
<body>
<jsp:include page="/jsp/header.jsp"/>
<div>
    <form id="add_match" action="${pageContext.request.contextPath}/controller?command=add_match" method="post">
        <div class="form-group">
        <label>
            <select name="homeTeam">
                <c:forEach items="${sessionScope.teams}" var="team">
                    <option>${team.name}</option>
                </c:forEach>
            </select>
        </label>
        </div>
        <div class="form-group">
        <label>
            <select name="awayTeam">
                <c:forEach items="${sessionScope.teams}" var="team">
                    <option>${team.name}</option>
                </c:forEach>
            </select>
        </label>
        </div>
        <div class="form-group">
            <label for="startDate"></label><input type="date" name="startDate" class="form-control" id="startDate" required>
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
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

