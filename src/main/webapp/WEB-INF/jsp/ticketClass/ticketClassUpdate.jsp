<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../include/header.jsp" %>

<h1>Добавить/изменить Билета</h1>
<form class="" action="/admin/ticketClass/save" method="post">
    <c:if test="${not empty ticketClass.id}">
        <input type="hidden" name="id" value="${ticketClass.id}">
    </c:if>
    <input type="hidden" name="shipId" value="${shipId}">
    <div class="form-group">
        <label for="type">Type:</label>
        <input type="text" id="type" name="type" value="${ticketClass.type}">
    </div>
    <div class="form-group">
        <label for="count">Count:</label>
        <input type="number" id="count" name="count" value="${ticketClass.count}">
    </div>
    <div class="form-group">
        <label for="bonus">Bonus:</label>
        <textarea type="text" id="bonus" name="bonus">${ticketClass.bonus}</textarea>
    </div>
    <div class="form-group">
        <input type="submit">
    </div>
</form>

<%@include file="../include/footer.jsp" %>