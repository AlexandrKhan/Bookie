<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="property/text"/>

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
