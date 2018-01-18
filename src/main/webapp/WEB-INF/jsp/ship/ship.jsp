<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="../include/header.jsp" %>

<h1>Корабли</h1>
<table class="table">
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Passengers</th>
        <th>Crew</th>
        <c:if test="${sessionScope.user.role eq 'ADMIN'}">
            <th>Ticket</th>
            <th>Update ship</th>
            <th>Delete ship</th>
        </c:if>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${ships}" var="ship">
        <tr>
            <td>${ship.id}</td>
            <td>${ship.name}</td>
            <td>${ship.passengers}</td>
            <td>${ship.crew}</td>
            <c:if test="${sessionScope.user.role eq 'ADMIN'}">
                <td><a href="/admin/ticketClass?shipId=${ship.id}" class="active">Ticket</a></td>
                <td><a href="/admin/ship/save?id=${ship.id}" class="active">Изминить</a></td>
                <td><a href="/admin/ship/delete?id=${ship.id}" class="active">Удалить</a></td>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>
<c:if test="${sessionScope.user.role eq 'ADMIN'}">
    <a href="/admin/ship/save" class="btn btn-success">Add new</a>
</c:if>

<%@include file="../include/footer.jsp" %>