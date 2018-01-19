<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="../include/header.jsp" %>

<h1>Excursion</h1>
<table class="table">
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Details</th>
        <th>Price</th>
        <c:if test="${sessionScope.user.role eq 'USER'}">
            <th>Buy</th>
        </c:if>
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
            <td>${excursion.price}</td>
            <c:if test="${sessionScope.user.role eq 'USER'}">
                <td><a href="buyExcursion?excursionId=${excursion.id}&portId=${excursion.portId}" class="active">Buy</a>
                </td>
            </c:if>
            <c:if test="${sessionScope.user.role eq 'ADMIN'}">
                <td><a href="/admin/excursion/save?id=${excursion.id}&portId=${excursion.portId}" class="active">Изминить</a>
                </td>
                <td><a href="/admin/excursion/delete?id=${excursion.id}&portId=${excursion.portId}" class="active">Удалить</a>
                </td>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>
<c:if test="${sessionScope.user.role eq 'ADMIN'}">
    <a href="/admin/excursion/save?portId=${portId}" class="btn btn-success">Add new</a>
</c:if>

<%@include file="../include/footer.jsp" %>