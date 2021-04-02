<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="custom" uri="/WEB-INF/tld/custom.tld" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="property/text"/>
<c:set var="match" value="${requestScope.match}"/>
<c:set var="comments" value="${requestScope.comments}"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>${match.homeTeam}</h1>
<h1>${match.awayTeam}</h1>

<c:forEach var="comment" items="${comments}">
    <h1>${comment.comment}</h1>
</c:forEach>
</body>
</html>
