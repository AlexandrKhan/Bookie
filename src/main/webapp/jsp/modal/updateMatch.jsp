<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="property/text"/>

<div class='modal fade' id='UPDATEMATCHMODAL' tabindex='-1' role='dialog'
     aria-labelledby='exampleModalLabel' aria-hidden='false'>
    <div class='modal-dialog' role='document'>
        <div class='modal-content' style="height: 240px">
            <form method="post"
                  action="${pageContext.request.contextPath}/controller?command=update_match">
                <div class='modal-header'>
                    <h5 class='modal-title'><fmt:message key='update.match'/></h5>
                </div>
                <div class="modal-body">
                    <input type="hidden" name="matchId" id="match" value="">
                    <label for="startDate"></label><input type="date" name="startDate"
                                                          class="form-control"
                                                          id="startDate"
                                                          style="margin: 10px auto 10px auto;">
                    <label for="startTime"></label><input type="time" name="startTime"
                                                          id="startTime">
                </div>

                <button type='button' class='btn btn-secondary' data-dismiss='modal'>
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
