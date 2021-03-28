<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="custom" uri="/WEB-INF/tld/custom.tld" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="property/text"/>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel=stylesheet href="${pageContext.request.contextPath}/css/messages.css">
    <title>Title</title>
</head>
<body>
<jsp:include page="/jsp/header.jsp"/>
<div class="container">
    <c:forEach items="${sessionScope.messages}" var="message">
    <c:if test="${message.theme == 'WON'}">
        <div class="message-green">
            <p class="message-content"><c:out value="${message.message}"/></p>
            <div class="message-timestamp-right"><c:out value="${message.date}, ${message.time}"/></div>
        </div>
    </c:if>
    <c:if test="${message.theme == 'DELAY'}">
        <div class="message-orange">
            <p class="message-content"><c:out value="${message.message}"/></p>
            <div class="message-timestamp-right"><c:out value="${message.date}, ${message.time}"/></div>
        </div>
    </c:if>
        <c:if test="${message.theme == 'BAN'}">
            <div class="message-red">
                <p class="message-content"><c:out value="${message.message}"/></p>
                <div class="message-timestamp-right"><c:out value="${message.date}, ${message.time}"/></div>
            </div>
        </c:if>
        <c:if test="${message.theme == 'UNBAN'}">
            <div class="message-blue">
                <p class="message-content"><c:out value="${message.message}"/></p>
                <div class="message-timestamp-right"><c:out value="${message.date}, ${message.time}"/></div>
            </div>
        </c:if>
    </c:forEach>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
</body>
</html>
