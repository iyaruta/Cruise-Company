<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="../include/header.jsp" %>

<h1>Круизы</h1>
<table class="table">
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <c:if test="${sessionScope.user.role eq 'ADMIN'}">
            <th>Update cruise</th>
            <th>Delete cruise</th>
        </c:if>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${cruises}" var="cruise">
        <tr>
            <td>${cruise.id}</td>
            <td><a href="/cruise/details?id=${cruise.id}" class="active">${cruise.name}</a></td>
            <c:if test="${sessionScope.user.role eq 'ADMIN'}">
                <td><a href="/admin/cruise/save?id=${cruise.id}" class="active">Изминить</a></td>
                <td><a href="/admin/cruise/delete?id=${cruise.id}" class="active">Удалить</a></td>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>
<c:if test="${sessionScope.user.role eq 'ADMIN'}">
    <a href="/admin/cruise/save" class="btn btn-success">Add new</a>
</c:if>

<%@include file="../include/footer.jsp" %>