<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../include/header.jsp" %>

<h1>Добавить/изменить круиз</h1>
<form class="" action="/admin/cruise/save" method="post">
    <c:if test="${not empty cruise.id}">
        <input type="hidden" name="id" value="${cruise.id}">
    </c:if>
    <div class="form-group">
        <label for="select_id">Корабль:</label>
        <select id="select_id" name="shipId">
            <option>Не выбрано</option>
            <c:forEach items="${ships}" var="ship">
                <c:set var="selected"/>
                <c:if test="${ship.id eq cruise.shipId}">
                    <c:set var="selected" value="selected"/>
                </c:if>
                <option value="${ship.id}" ${selected}>${ship.name}</option>
            </c:forEach>
        </select>
        <%--<input type="text" id="name" name="name" value="${cruise.name}">--%>
    </div>
    <div class="form-group">
        <label for="name">Название:</label>
        <input type="text" id="name" name="name" value="${cruise.name}">
    </div>
    <c:if test="${empty cruise.id}">


        <div class="form-group">
            <label for="departure">Отправление:</label>
            <input type="datetime-local" id="departure" name="departure">
        </div>
        <div class="form-group">
            <label for="departure_port">Порт отправления:</label>
            <select id="departure_port" name="departurePortId">
                <option>Не выбрано</option>
                <c:forEach items="${ports}" var="port">
                    <option value="${port.id}">${port.name}</option>
                </c:forEach>
            </select>
        </div>


        <div class="form-group">
            <label for="arrival">Прибытие:</label>
            <input type="datetime-local" id="arrival" name="arrival">
        </div>
        <div class="form-group">
            <label for="arrival_port">Порт прибытия:</label>
            <select id="arrival_port" name="arrivalPortId">
                <option>Не выбрано</option>
                <c:forEach items="${ports}" var="port">
                    <option value="${port.id}">${port.name}</option>
                </c:forEach>
            </select>
        </div>

    </c:if>
    <div class="form-group">
        <input type="submit">
    </div>
</form>

<%@include file="../include/footer.jsp" %>