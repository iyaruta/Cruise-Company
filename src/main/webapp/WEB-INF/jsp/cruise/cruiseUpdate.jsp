<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../include/header.jsp" %>

<h1>Добавить/изменить круиз</h1>
<form class="" action="/cruise/save" method="post">
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
    <div class="form-group">
        <input type="submit">
    </div>
</form>

<%@include file="../include/footer.jsp" %>