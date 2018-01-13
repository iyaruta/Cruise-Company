<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../include/header.jsp" %>

<h1>Добавить/изменить точку</h1>
<form class="" action="/waypoint/save" method="post">
    <input type="hidden" name="cruiseId" value="${cruiseId}">
    <c:if test="${not empty waypoint.id}">
        <input type="hidden" name="id" value="${waypoint.id}">
    </c:if>
    <div class="form-group">
        <label for="select_id">Port:</label>
        <select id="select_id" name="portId">
            <option>Не выбрано</option>
            <c:forEach items="${ports}" var="port">
                <c:set var="selected"/>
                <c:if test="${port.id eq waypoint.portId}">
                    <c:set var="selected" value="selected"/>
                </c:if>
                <option value="${port.id}" ${selected}>${port.name}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group">
        <label for="arrival">Arrival:</label>
        <input type="datetime-local" id="arrival" name="arrival" value="${waypoint.arrival}">
    </div>
    <div class="form-group">
        <label for="departure">Departure:</label>
        <input type="datetime-local" id="departure" name="departure" value="${waypoint.departure}">
    </div>
    <div class="form-group">
        <input type="submit">
    </div>
</form>

<%@include file="../include/footer.jsp" %>