<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="custom" uri="/WEB-INF/tld/custom.tld" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="property/text"/>
<c:set var="users" value="${sessionScope.users}"/>
<c:set var="url" value="${sessionScope.url}"/>

<html>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<link rel=stylesheet href="${pageContext.request.contextPath}/css/admin.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
<title>Title</title>

<c:if test="${not empty param.filter}">
    <c:choose>
        <c:when test="${param.filter == 'banned'}">
            <c:set var="users" value="${custom:filterBannedUsers(users)}"/>
        </c:when>
        <c:when test="${param.filter == 'not_verified'}">
            <c:set var="users" value="${custom:filterNotVerifiedUsers(users)}"/>
        </c:when>
    </c:choose>
</c:if>
<body>
<jsp:include page="/jsp/header.jsp"/>
<jsp:include page="/jsp/modal/blockUser.jsp"/>

<%--<form action="${pageContext.request.contextPath}/controller?command=${param.command}">--%>
<%--    <input type="hidden" name="command" value="${param.command}"/>--%>
<%--    <c:if test="${not empty param.text}">--%>
<%--        <input type="hidden" name="text" value="${param.text}"/>"/>--%>
<%--    </c:if>--%>
<%--    <button type="submit" name="filter" value="banned">Banned</button>--%>
<%--</form>--%>

<%--<form action="${pageContext.request.contextPath}/controller?command=${param.command}">--%>
<%--    <input type="hidden" name="command" value="${param.command}"/>--%>
<%--    <c:if test="${not empty param.text}">--%>
<%--        <input type="hidden" name="text" value="${param.text}"/>"/>--%>
<%--    </c:if>--%>
<%--    <button type="submit" name="filter" value="not_verified">Not verified</button>--%>
<%--</form>--%>

<div class="container">
    <ul class="responsive-table">
        <li class="table-header">
            <div class="col col-1">Id</div>
            <div class="col col-2">Username</div>
            <div class="col col-3">Name</div>
            <div class="col col-4">Email</div>
            <div class="col col-5">Date of birth</div>
            <div class="col col-6">Passport</div>
            <div class="col col-7">Action</div>
        </li>
        <c:forEach items="${users}" var="user">
            <c:if test="${user.statusType == 'VERIFIED'}">
                <li class="table-row" style="background: linear-gradient(0deg, rgb(97 177 129), rgb(162 206 168));">
            </c:if>
            <c:if test="${user.statusType == 'NOT_ACTIVATED'}">
                <li class="table-row" style="background: linear-gradient(0deg, rgb(155 156 152), rgb(189 191 171))">
            </c:if>
            <c:if test="${user.statusType == 'ACTIVATED'}">
                <li class="table-row" style="background: linear-gradient(0deg, rgb(218 239 138), rgb(212 218 161));">
            </c:if>
            <c:if test="${user.statusType == 'BLOCKED'}">
                <li class="table-row" style="background: linear-gradient(0deg, rgb(210 50 63), rgb(191 95 95));">
            </c:if>

            <div class="col col-1" data-label="User id">#<c:out value="${user.id}"/></div>
            <div class="col col-2" data-label="Username"><c:out value="${user.username}"/></div>
            <div class="col col-3" data-label="Name"><c:out value="${user.firstName} ${user.lastName}"/></div>
            <div class="col col-4" data-label="Email"><c:out value="${user.email}"/></div>
            <div class="col col-5" data-label="Date of birth"><c:out value="${user.dateOfBirth}"/></div>
            <div class="col col-6" data-label="Passport">
<%--                <c:if test="${user.passportScan != 'passport.jpg'}">--%>
                <img src="${pageContext.request.contextPath}/showImage/${user.passportScan}" width="10%" height="10%"
                   alt="${user.firstName} ${user.lastName}" class="img-thumbnail">
                <div id="myModal" class="modal-scan">
                    <span class="close">&times;</span>
                    <img class="modal-content-scan" id="img01"
                         src="${pageContext.request.contextPath}/showImage/${user.passportScan}" alt="img">
                    <div id="caption"></div>
                </div>
<%--                </c:if>--%>
            </div>
            <div class="col col-7" data-label="Action">
                <c:if test="${user.statusType == 'VERIFIED'}">
                    <div>
                        <button type='button' class='btn btn-primary' data-toggle='modal'
                                data-target='#BLOCKUSERMODAL'
                                data-id="${user.id}">
                            <fmt:message key="block.user"/>
                        </button>
                    </div>
                </c:if>
                <c:if test="${user.statusType == 'BLOCKED'}">
                    <form method="post" action="${pageContext.request.contextPath}/controller?command=unblock_user&id=${user.id}">
                        <button type="submit" class="btn btn-primary">
                            <fmt:message key="unblock.user"/>
                        </button>
                    </form>
                </c:if>
                <c:if test="${user.statusType == 'ACTIVATED'}">
                    <form method="post" action="${pageContext.request.contextPath}/controller?command=verify_account&id=${user.id}">
                        <button type="submit" class="btn btn-primary">
                            <fmt:message key="verify.account"/>
                        </button>
                    </form>
                </c:if>
            </div>
            </li>
        </c:forEach>
    </ul>
</div>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/js/admin.js" type="text/javascript"></script>
</body>
</html>
