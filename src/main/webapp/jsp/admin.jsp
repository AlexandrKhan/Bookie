<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="custom" uri="/WEB-INF/tld/custom.tld" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="property/text"/>
<c:set var="users" value="${sessionScope.users}"/>

<html>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
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
<h1>List of users</h1>

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


<c:forEach items="${users}" var="user">
    <h1><c:out value="${user.firstName} ${user.lastName}"/></h1>
    <h1><c:out value="${user.dateOfBirth}"/></h1>
    <img src="${pageContext.request.contextPath}/uploads/${user.passportScan}" width="100" height="100"
         alt="${user.firstName} ${user.lastName}" class="img-thumbnail">
    <div id="myModal" class="modal-scan">
        <span class="close">&times;</span>
        <img class="modal-content-scan" id="img01"
             src="${pageContext.request.contextPath}/uploads/${user.passportScan}">
        <div id="caption"></div>
    </div>
    <form method="post" action="${pageContext.request.contextPath}/controller?command=verify_account&id=${user.id}">
        <button type="submit" class="btn btn-primary">
            <fmt:message key="verify.account"/>
        </button>
    </form>

    <div>
        <button type='button' class='btn btn-primary' data-toggle='modal'
                data-target='#BLOCKUSERMODAL'
                data-id="${user.id}">
            <fmt:message key="block.user"/>
        </button>
        <div class='modal fade' id='BLOCKUSERMODAL' tabindex='-1' role='dialog'
             aria-labelledby='exampleModalLabel' aria-hidden='false'>
            <div class='modal-dialog' role='document'>
                <div class='modal-content'>
                    <form method="post" action="${pageContext.request.contextPath}/controller?command=block_user">
                        <div class='modal-header'>
                            <h5 class='modal-title'><fmt:message key='block.user'/></h5>
                        </div>
                        <div class="modal-body">
                            <input type="hidden" name="id" id="id" value="">
                            <label for="message"></label><input type="text" name="message"
                                                                class="form-control"
                                                                id="message"
                                                                style="margin: 10px auto 10px auto;">
                            <label for="days"></label><input type="number" name="days" id="days" step="1" min="1"
                                                             value="1">
                        </div>
                        <button type='button' class='btn btn-alert' data-dismiss='modal'>
                            <fmt:message key='button.close'/></button>
                        <input type='submit' value='<fmt:message key='button.save' />' class='btn btn-primary'/>

                        <button type='button' class='btn btn-alert' data-dismiss='modal'>
                            <fmt:message
                                    key='button.close'/></button>
                        <input type='submit' value='<fmt:message key='button.save'/>'
                               class='btn btn-primary'/>

                        <c:if test="${not empty requestScope.errorSet}">
                            <label style="color: red; font-size: medium"><fmt:message
                                    key="match.dateError"/></label>
                        </c:if>
                    </form>
                </div>
            </div>
        </div>
    </div>


    <form method="post" action="${pageContext.request.contextPath}/controller?command=unblock_user&id=${user.id}">
        <button type="submit" class="btn btn-primary">
            <fmt:message key="unblock.user"/>
        </button>
    </form>

</c:forEach>
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
