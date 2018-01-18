<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="../include/header.jsp" %>

<h1>Excursion</h1>
<table class="table">
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Details</th>
        <c:if test="${sessionScope.user.role eq 'ADMIN'}">
            <th>Update excursion</th>
            <th>Delete excursion</th>
        </c:if>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${excursions}" var="excursion">
        <tr>
            <td>${excursion.id}</td>
            <td>${excursion.name}</td>
            <td>${excursion.details}</td>
            <c:if test="${sessionScope.user.role eq 'ADMIN'}">
                <td><a href="/admin/excursion/save?id=${excursion.id}" class="active">Изминить</a></td>
                <td><a href="/admin/excursion/delete?id=${excursion.id}" class="active">Удалить</a></td>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>
<c:if test="${sessionScope.user.role eq 'ADMIN'}">
    <a href="/admin/excursion/save" class="btn btn-success">Add new</a>
</c:if>

<%@include file="../include/footer.jsp" %>