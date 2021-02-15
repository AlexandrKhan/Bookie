<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>List of users</h1>
<c:forEach items="${requestScope.users}" var="user">
   <h1><c:out value="${user.username}"/></h1>
</c:forEach>
</body>
</html>
