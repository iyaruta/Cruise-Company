<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="../include/header.jsp" %>

<h1>Порт</h1>
<table class="table">
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Update port</th>
        <th>Delete port</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${port}" var="port">
        <tr>
            <td>${port.id}</td>
            <td>${port.name}</td>
            <td><a href="/port/save?id=${port.id}" class="active">Изминить</a></td>
            <td><a href="/port/delete?id=${port.id}" class="active">Удалить</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="/port/save" class="btn btn-success">Add new</a>

<%@include file="../include/footer.jsp" %>