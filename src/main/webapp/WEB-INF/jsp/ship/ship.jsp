<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="../include/header.jsp" %>

<h1>Корабли</h1>
<table class="table">
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Ticket</th>
        <th>Passengers</th>
        <th>Crew</th>
        <th>Update ship</th>
        <th>Delete ship</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${ships}" var="ship">
        <tr>
            <td>${ship.id}</td>
            <td>${ship.name}</td>
            <td><a href="/ticketClass?shipId=${ship.id}" class="active">Ticket</a></td>
            <td>${ship.passengers}</td>
            <td>${ship.crew}</td>
            <td><a href="/ship/save?id=${ship.id}" class="active">Изминить</a></td>
            <td><a href="/ship/delete?id=${ship.id}" class="active">Удалить</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="/ship/save" class="btn btn-success">Add new</a>

<%@include file="../include/footer.jsp" %>