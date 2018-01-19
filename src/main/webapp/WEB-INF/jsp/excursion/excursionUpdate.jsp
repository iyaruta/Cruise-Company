<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../include/header.jsp" %>

<h1>Добавить/изменить точку</h1>
<form class="" action="/admin/excursion/save" method="post">
    <c:if test="${not empty excursion.id}">
        <input type="hidden" name="id" value="${excursion.id}">
    </c:if>
    <input type="hidden" name="portId" value="${portId}">
    <div class="form-group">
        <label for="name">Excursion:</label>
        <input type="text" id="name" name="name" value="${excursion.name}">
    </div>
    <div class="form-group">
        <label for="details">details:</label>
        <input type="text" id="details" name="details" value="${excursion.details}">
    </div>
    <div class="form-group">
        <label for="price">pruce:</label>
        <input type="number" id="price" name="price" value="${excursion.price}">
    </div>
    <div class="form-group">
        <input type="submit">
    </div>
</form>

<%@include file="../include/footer.jsp" %>