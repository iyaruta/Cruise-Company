<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../include/header.jsp" %>

<h1>Добавить/изменить стоимость</h1>
<form class="" action="/admin/ticket/save" method="post">
    <input type="hidden" name="ticketClassId" value="${ticketClassId}">
    <input type="hidden" name="cruiseId" value="${cruiseId}">

    <div class="form-group">
        <label for="price">Цена:</label>
        <input type="number" id="price" name="price">
    </div>
    <div class="form-group">
        <input type="submit">
    </div>
</form>

<%@include file="../include/footer.jsp" %>