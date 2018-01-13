<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../include/header.jsp" %>

<h1>Добавить/изменить корабль</h1>
<form class="" action="/ship/save" method="post">
    <c:if test="${not empty ship.id}">
        <input type="hidden" name="id" value="${ship.id}">
    </c:if>
    <div class="form-group">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="${ship.name}">
    </div>
    <div class="form-group">
        <label for="passengers">Passengers:</label>
        <input type="text" id="passengers" name="passengers" value="${ship.passengers}">
    </div>
    <div class="form-group">
        <label for="crew">Crew:</label>
        <input type="text" id="crew" name="crew" value="${ship.crew}">
    </div>
    <div class="form-group">
        <input type="submit">
    </div>
</form>

<%@include file="../include/footer.jsp" %>