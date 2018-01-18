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
        <c:if test="${sessionScope.user.role eq 'ADMIN'}">
            <th>Update ship</th>
            <th>Delete ship</th>
        </c:if>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${ticketClasses}" var="ticketClass">
        <tr>
            <td>${ticketClass.id}</td>
            <td>${ticketClass.type}</td>
            <td>${ticketClass.count}</td>
            <td>${ticketClass.bonus}</td>
            <c:if test="${sessionScope.user.role eq 'ADMIN'}">
                <td><a href="/admin/ticketClass/save?id=${ticketClass.id}&shipId=${shipId}" class="active">Изминить</a>
                </td>
                <td><a href="/admin/ticketClass/delete?id=${ticketClass.id}" class="active">Удалить</a></td>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>
<c:if test="${sessionScope.user.role eq 'ADMIN'}">
    <a href="/admin/ticketClass/save?shipId=${shipId}" class="btn btn-success">Add new</a>
</c:if>

<%@include file="../include/footer.jsp" %>
