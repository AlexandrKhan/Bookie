<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="custom" uri="/WEB-INF/tld/custom.tld" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="property/text"/>
<c:set var="users" value="${sessionScope.users}"/>

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

<form action="${pageContext.request.contextPath}/controller?command=${param.command}">
    <input type="hidden" name="command" value="${param.command}"/>
    <c:if test="${not empty param.text}">
        <input type="hidden" name="text" value="${param.text}"/>"/>
    </c:if>
    <button type="submit" name="filter" value="banned">Banned</button>
</form>

<form action="${pageContext.request.contextPath}/controller?command=${param.command}">
    <input type="hidden" name="command" value="${param.command}"/>
    <c:if test="${not empty param.text}">
        <input type="hidden" name="text" value="${param.text}"/>"/>
    </c:if>
    <button type="submit" name="filter" value="not_verified">Not verified</button>
</form>

<div class="container">
    <ul class="responsive-table">
        <li class="table-header">
            <div class="col col-1">User id</div>
            <div class="col col-2">Username</div>
            <div class="col col-3">Name</div>
            <div class="col col-4">Email</div>
            <div class="col col-5">Date of birth</div>
            <div class="col col-6">Passport</div>
            <div class="col col-7">Status</div>
            <div class="col col-8">Action</div>
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
                <img src="${pageContext.request.contextPath}/uploads/${user.passportScan}" width="10%" height="10%"
                   alt="${user.firstName} ${user.lastName}" class="img-thumbnail">
                <div id="myModal" class="modal-scan">
                    <span class="close">&times;</span>
                    <img class="modal-content-scan" id="img01"
                         src="${pageContext.request.contextPath}/uploads/${user.passportScan}">
                    <div id="caption"></div>
                </div></div>
            <div class="col col-7" data-label="Status"><c:out value="${user.statusType}"/></div>
            <div class="col col-8" data-label="Action">
                <c:if test="${user.statusType == 'VERIFIED'}">
                    <div>
                        <button type='button' class='btn btn-primary' data-toggle='modal'
                                data-target='#BLOCKUSERMODAL'
                                data-id="${user.id}">
                            <fmt:message key="block.user"/>
                        </button>
                        <div class='modal fade' id='BLOCKUSERMODAL' tabindex='-1' role='dialog'
                             aria-labelledby='exampleModalLabel' aria-hidden='false'>
                            <div class='modal-dialog' role='document'>
                                <div class='modal-content' style="height: 250px">
                                    <form method="post" action="${pageContext.request.contextPath}/controller?command=block_user">
                                        <div class='modal-header'>
                                            <h5 class='modal-title'><fmt:message key='block.user'/></h5>
                                        </div>
                                        <div class="modal-body">
                                            <input type="hidden" name="id" id="id" value="">
                                            <label for="message"></label><input type="text" name="message"
                                                                                class="form-control"
                                                                                id="message"
                                                                                placeholder="<fmt:message key="ban.reason"/>"
                                                                                style="margin: 10px auto 10px auto;"
                                                                                required>
                                            <label for="days"></label><input type="number" name="days" id="days" step="1" min="1"
                                                                             value="1" required>
                                        </div>
                                        <button type='button' class='btn btn-secondary' data-dismiss='modal'>
                                            <fmt:message key='button.close'/></button>
                                        <input type='submit' value='<fmt:message key='button.save' />' class='btn btn-primary'/>

                                        <c:if test="${not empty requestScope.errorSet}">
                                            <label style="color: red; font-size: medium"><fmt:message
                                                    key="match.dateError"/></label>
                                        </c:if>
                                    </form>
                                </div>
                            </div>
                        </div>
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
<script type="text/javascript">
    $('#BLOCKUSERMODAL').on('show.bs.modal', function (event) {
        let userId = $(event.relatedTarget).data('id');
        $(this).find('.modal-body #id').val(userId);
    })
</script>
<script type="text/javascript">
    let modal = document.getElementById('myModal');
    let images = document.querySelectorAll(".img-thumbnail");
    let modalImg = document.getElementById("img01");
    let captionText = document.getElementById("caption");

    for (let i = 0; i < images.length; i++) {
        images[i].onclick = function () {
            modal.style.display = "block";
            modalImg.src = this.src;
            captionText.innerHTML = this.alt;
        }
    }

    let span = document.getElementsByClassName("close")[0];

    span.onclick = function () {
        modal.style.display = "none";
    }
</script>
</body>
<style>
    .img-thumbnail {
        border-radius: 5px;
        cursor: pointer;
        transition: 0.3s;
    }

    .img-thumbnail:hover {
        opacity: 0.7;
    }

    .modal-scan {
        display: none; /* Hidden by default */
        position: fixed; /* Stay in place */
        /*z-index: 1; !* Sit on top *!*/
        padding-top: 100px; /* Location of the box */
        left: 0;
        top: 0;
        width: 100%; /* Full width */
        height: 100%; /* Full height */
        overflow: auto; /* Enable scroll if needed */
        background-color: rgb(0, 0, 0); /* Fallback color */
        background-color: rgba(0, 0, 0, 0.9); /* Black w/ opacity */
    }

    .modal-content-scan {
        margin: auto;
        display: block;
        width: 80%;
        max-width: 700px;
    }

    .modal-backdrop {
        z-index: 0;
    }

    #caption {
        margin: auto;
        display: block;
        width: 80%;
        max-width: 700px;
        text-align: center;
        color: #ccc;
        padding: 10px 0;
        height: 150px;
    }

    .modal-content-scan, #caption {
        animation-name: zoom;
        animation-duration: 0.6s;
    }

    @keyframes zoom {
        from {
            transform: scale(0)
        }
        to {
            transform: scale(1)
        }
    }

    .close {
        position: absolute;
        top: 15px;
        right: 35px;
        color: #f1f1f1;
        font-size: 40px;
        font-weight: bold;
        transition: 0.3s;
    }

    .close:hover,
    .close:focus {
        color: #bbb;
        text-decoration: none;
        cursor: pointer;
    }

    @media only screen and (max-width: 700px) {
        .modal-content {
            width: 100%;
        }
    }
</style>
</html>
