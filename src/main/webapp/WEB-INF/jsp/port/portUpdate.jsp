<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../include/header.jsp" %>

<h1>Добавить/изменить порт</h1>
<form class="" action="/port/save" method="post">
    <c:if test="${not empty port.id}">
        <input type="hidden" name="id" value="${port.id}">
    </c:if>
    <div class="form-group">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="${port.name}">
    </div>
    <div class="form-group">
        <input type="submit">
    </div>
</form>

<%@include file="../include/footer.jsp" %>