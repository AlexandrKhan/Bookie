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
<div class="updateMatch">
    <span class="close">&times;</span>
    <form id="update_match" action="${pageContext.request.contextPath}/controller?command=update_match&matchId=${requestScope.matchId}" method="post">
        <div class="form-group">
            <label for="startDate"></label><input type="date" name="startDate" class="form-control" id="startDate" >
        </div>
        <div class="form-group">
            <label for="startTime"></label><input type="time" name="startTime" id="startTime" required>
        </div>

        <button type="submit" class="btn btn-primary">
            <fmt:message key="create.match"/>
        </button>
    </form>

    <c:if test="${not empty requestScope.errorSet}">
        <label style="color: red; font-size: medium"><fmt:message
                key="match.dateError"/></label>
    </c:if>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
</body>
</html>
