<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="../include/header.jsp" %>

<h1>Порт</h1>
<table class="table">
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <c:if test="${sessionScope.user.role eq 'ADMIN'}">
            <th>Update port</th>
            <th>Delete port</th>
        </c:if>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${port}" var="port">
        <tr>
            <td>${port.id}</td>
            <td>${port.name}</td>
            <c:if test="${sessionScope.user.role eq 'ADMIN'}">
                <td><a href="/admin/port/save?id=${port.id}" class="active">Изминить</a></td>
                <td><a href="/admin/port/delete?id=${port.id}" class="active">Удалить</a></td>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>
<c:if test="${sessionScope.user.role eq 'ADMIN'}">
    <a href="/admin/port/save" class="btn btn-success">Add new</a>
</c:if>

<%@include file="../include/footer.jsp" %>