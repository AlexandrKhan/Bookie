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
<jsp:include page="/jsp/header.jsp"/>
<h1>List of users</h1>
<c:forEach items="${sessionScope.users}" var="user">
   <h1><c:out value="${user.username}"/></h1>
    <form method="post" action="${pageContext.request.contextPath}/controller?command=block_user&id=${user.id}">
        <button type="submit" class="btn btn-primary">
            <fmt:message key="block.user"/>
        </button>
    </form>
    <form method="post" action="${pageContext.request.contextPath}/controller?command=unblock_user&id=${user.id}">
        <button type="submit" class="btn btn-primary">
            <fmt:message key="unblock.user"/>
        </button>
    </form>
</c:forEach>
</body>
</html>
