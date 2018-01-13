<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="../include/header.jsp" %>

<h1>Круизы</h1>
<table class="table">
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Update cruise</th>
        <th>Delete cruise</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${cruises}" var="cruise">
        <tr>
            <td>${cruise.id}</td>
            <td><a href="/cruise/details?id=${cruise.id}" class="active">${cruise.name}</a></td>
            <td><a href="/cruise/save?id=${cruise.id}" class="active">Изминить</a></td>
            <td><a href="/cruise/delete?id=${cruise.id}" class="active">Удалить</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="/cruise/save" class="btn btn-success">Add new</a>

<%@include file="../include/footer.jsp" %>