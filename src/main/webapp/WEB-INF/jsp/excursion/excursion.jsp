<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="../include/header.jsp" %>

<h1>Excursion</h1>
<table class="table">
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Details</th>
        <th>Update excursion</th>
        <th>Delete excursion</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${excursions}" var="excursion">
        <tr>
            <td>${excursion.id}</td>
            <td>${excursion.name}</td>
            <td>${excursion.details}</td>
            <td><a href="/excursion/save?id=${excursion.id}" class="active">Изминить</a></td>
            <td><a href="/excursion/delete?id=${excursion.id}" class="active">Удалить</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="/excursion/save" class="btn btn-success">Add new</a>

<%@include file="../include/footer.jsp" %>