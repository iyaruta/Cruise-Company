<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="../include/header.jsp" %>

<h1>Ticket class</h1>
<table class="table">
    <thead>
    <tr>
        <th>Id</th>
        <th>Type</th>
        <th>Count</th>
        <th>Bonus</th>
        <th>Update ship</th>
        <th>Delete ship</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${ticketClasses}" var="ticketClass">
        <tr>
            <td>${ticketClass.id}</td>
            <td>${ticketClass.type}</td>
            <td>${ticketClass.count}</td>
            <td>${ticketClass.bonus}</td>
            <td><a href="/ticketClass/save?id=${ticketClass.id}&shipId=${shipId}" class="active">Изминить</a></td>
            <td><a href="/ticketClass/delete?id=${ticketClass.id}" class="active">Удалить</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="/ticketClass/save?shipId=${shipId}" class="btn btn-success">Add new</a>

<%@include file="../include/footer.jsp" %>
