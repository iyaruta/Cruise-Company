<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="../include/header.jsp" %>
<h1>Круиз - ${cruise.name}</h1>
<table class="table">
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Arrival</th>
        <th>Departure</th>
        <th>Excursion</th>
        <th>Update port</th>
        <th>Delete port</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${waypoints}" var="waypoint">
        <tr>
            <td>${waypoint.id}</td>
            <td>${waypoint.port.name}</td>
            <td>
                <c:choose>
                    <c:when test="${empty waypoint.arrival}">
                        Начало круиза
                    </c:when>
                    <c:otherwise>
                        <javatime:format value="${waypoint.arrival}" pattern="dd.MM.yyyy HH:mm"/>
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <c:choose>
                    <c:when test="${empty waypoint.departure}">
                        Конец круиза
                    </c:when>
                    <c:otherwise>
                        <javatime:format value="${waypoint.departure}" pattern="dd.MM.yyyy HH:mm"/>
                    </c:otherwise>
                </c:choose>
            </td>
            <td><a href="/excursion?portId=${waypoint.port.id}" class="active">Excursion</a></td>
            <td><a href="/waypoint/save?id=${waypoint.id}&cruiseId=${cruise.id}" class="active">Изминить</a></td>
            <td><a href="/waypoint/delete?id=${waypoint.id}" class="active">Удалить</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="/waypoint/save?cruiseId=${cruise.id}" class="btn btn-success">Add new</a>

<%@include file="../include/footer.jsp" %>