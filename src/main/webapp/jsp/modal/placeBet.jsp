<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="property/text"/>

<div class='modal fade' id='PLACEBETMODAL' tabindex='-1' role='dialog'
     aria-labelledby='exampleModalLabel' aria-hidden='false'>
    <div class='modal-dialog' role='document'>
        <div class='modal-content' style="height: 280px">
            <form method="post"
                  action="${pageContext.request.contextPath}/controller?command=place_bet">
                <div class='modal-header'>
                    <h5 class='modal-title'><fmt:message key='place.bet'/></h5>
                </div>
                <div class="modal-body">
                    <input type="hidden" name="matchId" id="matchId" value="">
                    <label for="betAmount"></label><input type="number" name="betAmount"
                                                          class="form-control"
                                                          id="betAmount" step="0.01" min="5"
                                                          value="5" required>
                    <div class="radio-group">
                        <input type="radio" id="home" name="betOnResult" value="HOME" required><label
                            id="homeCoeff"
                            for="home">Home</label>
                        <input type="radio" id="draw" name="betOnResult" value="DRAW"><label
                            id="drawCoeff"
                            for="draw">Draw</label>
                        <input type="radio" id="away" name="betOnResult" value="AWAY"><label
                            id="awayCoeff"
                            for="away">Away</label>
                    </div>
                </div>

                <button type='button' class='btn btn-secondary' data-dismiss='modal'>
                    <fmt:message
                            key='button.close'/></button>
                <input type='submit' value='<fmt:message key='button.save'/>'
                       class='btn btn-primary'/>
            </form>
        </div>
    </div>
</div>
